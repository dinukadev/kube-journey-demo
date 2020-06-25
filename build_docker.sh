#!/bin/bash

REGION=ap-southeast-2
ECR_REPO=066170451082.dkr.ecr.ap-southeast-2.amazonaws.com
MS_NAME=ms-kubejourney
TAG=dev


$(aws ecr get-login --no-include-email --region ap-southeast-2)
aws ecr batch-delete-image --repository-name $MS_NAME --image-ids imageTag=latest-$TAG --region $REGION
docker build --force-rm=true -t $MS_NAME:latest .
docker tag $MS_NAME:latest $ECR_REPO/$MS_NAME:latest-$TAG
docker push $ECR_REPO/$MS_NAME:latest-$TAG
