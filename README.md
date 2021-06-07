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

### Traditional Rest API
If you can see the below example, this style of coding is called **Imperative Stryle APIs** where the execution goes top-down approach. Imperative style APIs by its nature are **synchronous** & **blocking**.   

![](https://github.com/Eainde/spring-data-reactive/blob/main/src/main/resources/images/ImperativeCode.jpeg)

Imperative style programming leads to inefficient use of resources. 

If we want to make it asynchronous & non blocking then in java currently we have two options.
- Callbacks
- Futures

#### Callbacks
Callbacks are not great options. There are few drawbacks listed below.
- Complex
- No return value
- Code is hard to read and maintain
- Leads to callback hell

#### Future
Future is a another alternative to write asynchronous code in java.
- It returns Future instance
- Hard to compose multiple asynchronous operations.

#### Completable Future
Considering the above limitations of Future, java-8 introduced **Completable Future**.
- Supports functional style API.
- Easy to compose multiple asynchronous operations.
- It is not a great fit for asynchronous methods which involves multiple items in response and error handling is not that great too. We need to do lot of plumbing to make it work right. 

Another disadvantage of imperative programming is there is no option to handle **Back Pressure**. If you see in below example if thousands of requests are triggered the there might be case that client cannot handle data and can leads to memory out of bound issue. There is no way to tell DB to slow down as client already has a lot of data to handle.
![](https://github.com/Eainde/spring-data-reactive/blob/main/src/main/resources/images/NoBackPressure.jpeg)