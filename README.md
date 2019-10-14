Successful test on 4260 rps. Have a problem with generating more request.
todo: 
1. add in docker-swarm info module.
2. presently gps-service processes all incoming data based on producer-consumer pattern, 
    consequently we have very fast ConcurrentLinkedQueue and one thread which in while(true) poll 
    from it and insert in batch to mongodb.
Alternative idea use some kind of Subject, which will be accept a call from controller asynchronous
    endpoint and accumulate elements in batch. 
Should be significant load reduction on process (disposal while(true)).   