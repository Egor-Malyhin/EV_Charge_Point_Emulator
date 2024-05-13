# EV_Charging_Point_Emulator
A behavioral model simulating the operation of a real electric vehicle charging station. The model implements OCPP J protocol standards for communication with the charging station management system and ISO 15118-2 for communication with the electric vehicle charging controller.

You can use this model to develop a charging station management system or an electric vehicle charging controller or to simulate the charging process.
## How to get started
First you need to download the docker:
<https://www.docker.com/get-started/>

### Application mode
If you want to see how the application works with the standard charging algorithm, first assemble the docker containers using:\
`docker-compose -f docker-compose-application.yml build`\
Then run the containers:\
`docker-compose -f docker-compose-application.yml up -d`

### Developing CSMS mode
If you want to test the charging station management system you are developing first assemble the docker containers using:\
`docker-compose -f docker-compose-csms-dev.yml build`\
Then run the containers:\
`docker-compose -f docker-compose-csms-dev.yml up -d`

You must first run your CSMS and then this container.\
Be sure that your CSMS is running on the local host on hostname `ws://localhost:8887`. If this is not the case, change the value of the environment variable in the program sources in the `EV_Charging_Point/src/main/resources` folder `commcsms.connect.ws` to the desired csms server hostname and then rebuild the container.
### Developing EVCC mode
If you want to test the electric vehicle charging controller you are developing first assemble the docker containers using:\
`docker-compose -f docker-compose-evcc-dev.yml build`\
Then run the containers:\
`docker-compose -f docker-compose-evcc-dev.yml up -d`

### Notes
You can view logs of any model using:\
`docker-compose -f <name of the docker-compose-file used>.yml logs <name of the running model>`

Please note that after successful charging and the approval of the discount from the charging station, the model of the electric vehicle charging controller automatically completes its execution. You can restart it using:\
`docker-compose -f <name of the docker-compose-file used>.yml restart evcc_client`
