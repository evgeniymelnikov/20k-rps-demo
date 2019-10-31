Successful test on 21800 rps (2 desktops and 1 external service for load testing). Have a problem with generating more request.

To start it:
1. need package gps-service and info-service with profile 'dev-docker-compose'
2. in terminal (when the working directory matches project directory) exec 'docker-compose up'
3. after that you can get access to info module with predefined data (they are in form placeholder) by localhost:8086 
(for login username: admin, password: adminPass)
4. there is at the end of docker-compose.yml a commented out section for calling load testing script (with docker and wrk util).

todo: Improve the front, now it's scary...
