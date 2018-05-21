## Java-RMI-client-server-with-concurrency
This is a completed test task for Dell EMC in the ECS department

### Description
#### Server application
Server implements a remote method call "remoteRandomNumber", which should simply return a random number.
#### Client application
- The client application runs for 100 seconds then ends
- In the process of working it uses N threads, each repeatedly invokes the remote method "nextRandomNumber" until the application terminates.
- Application uses the command line arguments as the number of threads to execute queries (N).
- When finished displays the average number of completed requests per second.
### Description of work performed
#### Results
 В результате работы был получен следующий график
___
 ![](/doc/pictures/result.png)
 