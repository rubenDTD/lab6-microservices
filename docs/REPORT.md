# LAB 6 - MICROSERVICES REPORT

### Eureka server

Booting the Eureka server. It's server for microservices registration, location, load balancing and fault tolerence. Eureka register the different instances of existing microservices in the system.

```bash
./gradlew registration:bootRun
```

![eureka_server](resources/eureka_server.png)

It's exposed at `http:localhost:1111`

![eureka_end](resources/eureka_end.png)

### Account service

The account service uses Spring Data to implement de repository and sprint Rest to provide a RESTful interface to account information.

It registers itself with the `discovery-server` at start-up. In this example the name it's `accounts-service`.

Booting the Account service
```bash
./gradlew accounts:bootRun
```

![account_service1](resources/account_service1.png)
![account_service2](resources/account_service2.png)

It's exposed at `http:localhost:2222`

![account_end](resources/account_end.png)

### Web service

The web controller is a typical Spring MVC view-based controller returning HTML. 

It also registers itself with the `discovery-server`. In this example the name it's `web-service`.

Booting the Web service
```bash
./gradlew web:bootRun
```

![web_service1](resources/web_service1.png)
![web_service2](resources/web_service2.png)

It's exposed at `http:localhost:3333`

![web_end](resources/web_end.png)

### Extra account service

We're adding another accounts services to the service changing the port in the `application.yaml` file where it's configurated. 
![extra_account_service_yaml](resources/extra_account_service_yaml.png)

Then we boot again the extra account services
```bash
./gradlew accounts:bootRun
```
![extra_account_service1](resources/extra_account_service1.png)
![extra_account_service2](resources/extra_account_service2.png)

It's exposed at `http:localhost:4444`

### Using the application
We can see that in the Eureka registration server the new service it's registered with the correct port.
![extra_account_end](resources/extra_account_end.png)

![web_request](resources/web_request.png)
![extra_account_response](resources/extra_account_response.png)

### Killing server 2222

When we kill the accounts server `2222`

![kill_2222](resources/kill_2222.png)

There is not application with port `2222` in Eureka server
![kill_2222_eureka](resources/kill_2222_eureka.png)

The request is still being sent to the client.
![kill_2222_response](resources/kill_2222_response.png)

### Why is it working?
It's working because Eureka search if there is one application available to do the task, it doesn't search for the exact server.
