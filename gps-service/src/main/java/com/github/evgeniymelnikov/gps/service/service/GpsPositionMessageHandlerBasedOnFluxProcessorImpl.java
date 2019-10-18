package com.github.evgeniymelnikov.gps.service.service;

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
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.UnicastProcessor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("gpsPositionHandlerFlux")
@RequiredArgsConstructor
@Slf4j
public class GpsPositionMessageHandlerBasedOnFluxProcessorImpl implements GpsPositionMessageHandler {

    private final FluxProcessor<Map<String, Object>, Map<String, Object>> processor
            = UnicastProcessor.create();
    private final FluxSink<Map<String, Object>> sink = processor.sink();
    private final MongoCollection<Document> mongoCollection;

    @Value("${mongodb.insert_bulk_size}")
    private int bulkSize;

    private int bulkOperationCount;

    @PostConstruct
    public void setUp() {
        processor.buffer(bulkSize).subscribe(jsonList -> {
            List<WriteModel<Document>> prepared = new ArrayList<>(bulkSize);
            jsonList.forEach(json ->
                prepared.add(new InsertOneModel<>(new Document(json)))
            );

            mongoCollection.bulkWrite(prepared).subscribe(new BaseSubscriber<BulkWriteResult>() {
                        @Override
                        protected void hookOnComplete() {
                   log.debug("Processed: " + ++bulkOperationCount * bulkSize);
                            super.hookOnComplete();
                        }
                    });
            });
    }

    @Override
    public void sendToHandler(Map<String, Object> gpsPositionJson) {
        sink.next(gpsPositionJson);
    }
}
