## Java-RMI-client-server-with-concurrency
This is a completed test task for Dell EMC in the ECS department. Position: Java Junior concurrency.


### Description
#### Server application
- Server implements a remote method call "remoteRandomNumber", which should simply return a random number.
#### Client application
- The client application runs for 100 seconds then ends
- In the process of working it uses N threads, each repeatedly invokes the remote method "nextRandomNumber" until the application terminates.
- Application uses the command line arguments as the number of threads to execute queries (N).
- When finished displays the average number of completed requests per second.
### Description of work performed
#### Build
    ~/client-server> ./gradlew buildServer
    ~/client-server> ./gradlew buildClient
#### Testing
To test the resulting work, a script was written in Python3.
It is necessary to build the project before testing.
Using the command below to run the script.

    ~/client-server/tests> ./run_test.py
    
The script works as follows:
- Run the Server application
- Run the client with [1, 2,..., 9, 10, 20, 30, ..., 100] threads.
- Save results of each iteration as CSV-file in "./results/result.csv"
- Plot the graph with results
#### Results
 As a result of testing, the following graph and tables
 
| N of Threads | Queries per second |
|:------------:|:------------------:|
|       1      |      38966.62      |
|       2      |      62688.62      |
|       3      |      80483.24      |
|       4      |      93260.73      |
|       5      |      94500.77      |
|       6      |      102844.98     |
|       7      |      116414.59     |
|       8      |      144652.4      |
|       9      |      143647.78     |
|      10      |      144223.57     |
|      20      |      140222.27     |
|      30      |      138864.81     |
|      40      |      137893.39     |
|      50      |      138452.94     |
|      60      |      141646.56     |
|      70      |      139885.9      |
|      80      |      139704.78     |
|      90      |      139921.55     |
|      100     |      140849.78     |
___
 ![](/doc/pictures/result.png)
 
 As you can see from 1 to 8 threads to 8, the number of requests grows with linear dependency.
 With the number of threads equal to 8, the number of requests reaches a maximum.
 In turn, the minimum is equal to the work of an application with one thread.
 Application startups with the number of threads from 9 to 100 have approximately the same number of requests, 
 but with some variation, however, no launch with more than 8 threads reaches the maximum query level.
 Therefore, the optimal number of threads is 8.
 The optimal number is directly related to the number of cores in the processor.
 When there are more threads than cores on the processor, a race condition for processor time begins between threads.