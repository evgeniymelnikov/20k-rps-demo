package com.github.evgeniymelnikov.gps.service.service;

import com.github.evgeniymelnikov.gps.service.config.MongoConfig;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.reactivestreams.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("gpsPositionHandlerProducerConsumer")
@RequiredArgsConstructor
@Slf4j
public class GpsPositionMessageHandlerBasedOnCLQImpl implements GpsPositionMessageHandler {

    private final ConcurrentLinkedQueue<Document> queue = new ConcurrentLinkedQueue<>();
    private int bulkOperationCount;
    private final MongoCollection<Document> mongoCollection;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Value("${mongodb.insert_bulk_size}")
    private int bulkSize;

    @PostConstruct
    public void setUp() {
        executorService.submit(() -> {
            final List<WriteModel<Document>> list = new ArrayList<>(bulkSize);
            while (true) {
                Document temp = queue.poll();
                if (temp != null) {
                    list.add(new InsertOneModel<>(temp));
                }

                if (list.size() == bulkSize) {
                    ArrayList<WriteModel<Document>> copy = new ArrayList<>(list);
                    list.clear();
                    mongoCollection.bulkWrite(copy).subscribe(new BaseSubscriber<BulkWriteResult>() {
                        @Override
                        protected void hookOnComplete() {
                            log.debug("Processed: " + ++bulkOperationCount * bulkSize);
                            super.hookOnComplete();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void sendToHandler(Map<String, Object> gpsPositionJson) {
        queue.add(new Document(gpsPositionJson));
    }
}
