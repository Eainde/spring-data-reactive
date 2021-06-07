# Reactive programming using spring web flux
### Evolution of programming
| Past (10-15 years ago) | Today |
| ---------------------- | ---|
| Monolith Applications | Micro Service|
| Run in app server | Run it in Cloud
| Does not embrace Distributed Systems | Embrace Distributed Systems

### Expectations of the App
- **Scale based on the load :** What do we mean by that? Let say we have an online shopping application. If the number of users increase then the expected value, then the application should scale its resources to handle the load. Basically there should not be any downtime.
- **Use resources efficiently :** Lets say you make a call to database or to an external service to get some data and during this scenario we wait until the responses returned from the external resources. Waiting for something in general is not effective resource utilization. 
- **Latency or Response time should be faster** 

### Traditional Spring MVC Request/Response Flow
![](https://github.com/Eainde/spring-data-reactive/blob/main/src/main/resources/images/SpringMVCFlow.png)

How do we handle concurrent request in traditional restful API's? The model we have for this is the below thread per request model.

**Thread per request model**
![](https://github.com/Eainde/spring-data-reactive/blob/main/src/main/resources/images/ThreadPerModel.png)

The number of concurrent users an application can handle that depend on the size of the thread pool. The default size of thread pool is 200, but you can set it according to you need. eg in spring boot you can set it by below property.
```properties
server.tomcat.max-threads = 300
```
By increasing the size of thread pool can cause memory issue. Each thread takes some memory and the common stack size is 1MB. Higher the thread pool size, higher the memory consumption. If you have a larger thread pool that leaves you with very less memory for the application processing, which eventually means the application will perform poor with less memory available.

How it is handled today?
- Most popular approach today to handle the load is **horizontal scaling**. Here we can have multiple instance of the application. This can be done by kubernates or some other container orchestration.
  ![](https://github.com/Eainde/spring-data-reactive/blob/main/src/main/resources/images/horizontalScaling.jpg)
  
This model work perfect and will work in future too. But if you take a look spinning up more instances in cloud is going to add some kind of additional cost.
