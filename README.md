# Progetto TASD

This project aims to build an application for search and apply job offers, built with a microservice architecture.

# Architecture
![alt](docs/Microservice%20progetto.png)

The service API Gateway is the service responsible of dispatching web requests, authentication, authorization and filtering, and exposes application's API.  
The service Job Center is the service responsible of managing job centers, and exposes CRUD API for jobs centers.  
The service Job is the service responsible of managing job insertions, and exposes CRUD API for job insertions.  
The service Job Seeker is the service responsible of browsing job insertions, and exposes API for job search.  
The service Application is the service responsible of managing users' applications to jobs, and exposes CRUD API for managing jobs.  
The service Notification is the service responsible of notifying users of application confirmation and new job insertions, and exposes API to send emails using Google Mail API.  
The service Job Advisor is the service responsible of computing skills-based suggestions, and exposes API for provide job suggestions to the seeker.  
The service Seeker is the service responsible of managing users, and exposes CRUD API for users.  

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Authors

* **Andrea Biaggi** - 794873
* **Matteo Costantini** - 795125
* **Lorenzo Di Vito** - 793128
* **Dario Gerosa** - 793636

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

