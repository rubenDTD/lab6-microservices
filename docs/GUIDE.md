# GUIDE

This is the repository that we will use for the six assignment of the course 2022-2023. This guide is command line oriented, but you are free to use IDE like _VS Code_, _IntelliJ IDEA_ and _Eclipse_ which have full support of the tools that we are going to use. We also assume that you have installed in your box at least [Kotlin 1.7.0](https://kotlinlang.org/docs/getting-started.html#install-kotlin).

This laboratory is not a speed competition.

## Preparation

Fork this repository.
Next you will have <https://github.com/UNIZAR-30246-WebEngineering/lab6-microservices> cloned in `https://github.com/your-github-username/lab6-microservices`.

By default, GitHub Actions is disabled for your forked repository.
Go to `https://github.com/your-github-username/lab6-microservices/actions` and enable them.

Next, go to your repository and click in `Code` on the `main` button and create a branch named `work`.

Next, clone locally the repository:

```bash
git clone https://github.com/your-github-username/lab6-microservices
cd lab6-microservices
git branch -a
```

Should show `main`, `work`, `remotes/origin/main` and `remotes/origin/work`.

Then, checkout the `work` branch:

```bash
git checkout -b work
```

Make changes to the files, commit the changes to the history and push the branch up to your forked version.

```bash
git push origin work
```

## Primary task

- Extend the query interface to support the command `max:n`, where _n_ is a number.

## Microservices infrastructure

The code is based on the post [microservices with Spring](https://spring.io/blog/2015/07/14/microservices-with-spring)
developed by [Paul Chapman](https://github.com/paulc4). The laboratory shows a simple example of setting up
a [microservices](http://martinfowler.com/articles/microservices.html) system using Spring Boot and Eureka. This project
contains three apps:

- **Service discovery** (`registration` written in Kotlin):
  It launches an open source discovery server called [Eureka](https://github.com/Netflix/eureka) that will use the port
  1111. The dashboard of the registration server is exposed in `http://localhost:1111`.

  ```bash
  ./gradlew registration:bootRun
  ```

- **Account service** (`accounts` written in Kotlin):
  It is a standalone process that provides a RESTful server to a repository of accounts that will use the port 2222.
  What it makes special is that it registers itself to Eureka with the name `ACCOUNTS-SERVICE`. After launching this
  service you can see in the dashboard of Eureka that after a few seconds (10-20 secs) the `ACCOUNTS-SERVICE` service
  appears.

  ```bash
  ./gradlew accounts:bootRun
  ```

- **Web service** (`web` written in Java):
  It is a standalone process that provides an MVC front-end to the application of accounts that will use the port 3333.
  What it makes special is that it registers itself to the Eureka with the name `WEB-SERVICE` and asks the Eureka where
  is the `ACCOUNTS-SERVICE`. Spring configures automatically an instance of `RestTemplate` for using the discovery
  service transparently!!!

  ```bash
  ./gradlew web:bootRun
  ```

## Steps required

The objective is to show that the following activities have been accomplished:

- The two services `accounts (2222)` and `web` are running and registered (two terminals, logs screenshots).
- The service registration service has these two services registered (a third terminal, dashboard screenshots)
- A second `accounts` service instance is started and will use the port 4444. This second `accounts (4444)` is also
  registered (a fourth terminal, log screenshots).
- What happens when you kill the service `accounts (2222)` and do requests to `web`?  
  Can the web service provide information about the accounts again? Why?

The above must be documented in a brief report (`REPORT.md`) with screenshots describing what happens.

## How to submit

In your master branch update your corresponding row in `README.md` with the link to your work branch, and a link to a `REPORT.md` that proof or explains how you solved this lab.

Do a pull request from your `main` branch to this repo main branch.
The only file modified in the pull request must be `README.md`
The pull request will be accepted if:

- Your `work` branch contains proofs that shows that you have fulfilled the primary tasks.
- In `README.md` you provides a link to your work branch in your repository, i.e.:

```md
[your-github-user-name](https://github.com/your-github-username/lab6-microservices/tree/work)
```

along with your NIA and a link to the `REPORT.md` document.

```md
[REPORT.md](https://github.com/your-github-username/lab6-microservices/blob/work/REPORT.md)
```
