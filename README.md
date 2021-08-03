# README #

# STEPS TO RUN THE APPLICATION

1. install JDK 8 on the system and install maven on the system
2. download nssm,
3. create a folder on the system,
4. paste the nssm application in the folder,
5. run maven clean
6. build the project
7. copy and paste the jar file in the folder created
8. open cmd and change to the directory of the folder
9. run "nssm install (given name of the application) java -jar xxxxx-api-0.0.1-SNAPSHOT.jar(the jar file name) --spring.config.location=config/applications.properties",
10. run "nssm edit (given name of the application)" and drop the folder path in the pop up window where its written startup dir ,
11. run "nssm start (given name of the application)"


