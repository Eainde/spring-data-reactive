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

### Spring MVC Request/Response Flow
![](https://github.com/Eainde/spring-data-reactive/blob/main/src/main/resources/images/SpringMVCFlow.png)