# Introduction

This repository contains the sample application used in tandem with the [A Journey Through Kubernetes PPT](https://docs.google.com/presentation/d/19U_xBSLqnwbQYQUq-zu0pAJQkpjpYpBhhcWM81smJhM/edit?usp=sharing)


It is a Spring boot application which is built as a docker image and pushed to AWS ECR after which it is deployed to an AWS EKS cluster.

The sample application exposes an Employee CRUD which saves data in an in-memory cache so as to maintain the brevity of the presentation.

## Pre-requisites
 - JDK 12
 - Maven 3
 - Docker
 - AWS CLI
 - kubectl
 
Make sure the AWS CLI is configured with a user that has admin access so that we do not run into any privilege issues when we run certain commands down the line.
 
 
## AWS

- Create an IAM user with the following 
```
aws iam create-user --user-name automator
``` 

Update the account id and the region according to what is application to you on the following files
- create_ecr_repo.sh 
```
ACCOUNT_ID=xxx
REGION=ap-southeast-2
```
- ecr_policy.json
```
"Principal": {
        "AWS": "arn:aws:iam::066170451082:user/automator"
}
```

 
 
## Importing the code on IntelliJ/Eclipse

- To load the project on Eclipse, run the following command and then import it as a usual project
```
mvn eclipse:eclipse
```

- To load the project on IntelliJ, run the following command and then import it as a usual project
```
mvn idea:idea
```
 
## Build (Local)

The application can be built with the following command.
```
mvn clean install
```

To run the application, run the following command. Make sure you do not have any other application running on port 8080 as that is the port utilised by the application.
```
java -jar target/ms-kube*.jar
```

## Build (Docker)

Use the following command to build the docker image and tag it as `ms-kubejourney:latest` locally

```
docker build --force-rm=true -t ms-kubejourney:latest .
```




