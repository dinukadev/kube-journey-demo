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
 - Postman (Optional. Only required if you intend on running some tests locally to test the Spring boot applciation)
 - eksctl
 
Make sure the AWS CLI is configured with a user that has admin access so that we do not run into any privilege issues when we run certain commands down the line.
 
 
## AWS configuration

- Create an IAM user with the following 
```
aws iam create-user --user-name automator
``` 

Update the account id, region, ECR repo URL according to what is applicable to you on the following files (Do not run these scripts yet)
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

- build_docker.sh
```
REGION=ap-souheast-2
ECR_REPO=xxx.dkr.ecr.ap-southeast-2.amazonaws.com
```

 
- Create a new [key/pair](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-key-pairs.html#having-ec2-create-your-key-pair) which we will then use to create the AWS EKS cluster
- Extract the public key from that created key/pair using the following command replacing the `<kubepairfile>` with the name of the key pair you created and downloaded;
```
ssh-keygen -y -f <keypairfile>.pem  >> keypair.pub
```
- Creat the EKS cluster using the following command. Note that this takes roughly around 10-15 minutes to complete.
```
eksctl create cluster --name kube-journey-demo-cluster --version 1.16 --region ap-southeast-2 --nodegroup-name kube-journey-demo-node-group --node-type t2.micro --nodes 2 --nodes-min 1 --nodes-max 2 --ssh-access --ssh-public-key keypair.pub --managed
```

This will create the AWS EKS cluster with a node group consisting of two t2.micro EC2 instances 
 

## Changes to the Kubernetes deployment yaml
- Change the ECR repository URL on the file `ms-kubejourney-deployment.yaml` with the ECR URL of your environment under the following
```
image: 066170451082.dkr.ecr.ap-southeast-2.amazonaws.com:latest-dev
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

- Run the script first to create the AWS ECR repository
```
./create_ecr.repo.sh
```

Run the script file `build_docker.sh` which will build the docker image locally and then push it to the AWS ECR repository

```
./build_docker.sh
```



## Postman Scripts

- If you want to test the running application locally, you can install Postman and import the file `kube-demo.postman_collection.json` into it.



## Tear down

- Run the following to tear down everything done
```$xslt
kubectl delete namespace ns-demo
eksctl delete cluster --name kube-journey-demo-cluster
```
