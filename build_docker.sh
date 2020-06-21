#!/bin/bash

~aws ecr batch-delete-image --repository-name $bamboo_SERVICE_NAME --image-ids imageTag=latest-$TAG_NAME --region ap-southeast-2
docker tag $bamboo_SERVICE_NAME:latest 780795068345.dkr.ecr.ap-southeast-2.amazonaws.com/$bamboo_SERVICE_NAME:latest-$TAG_NAME
docker push 780795068345.dkr.ecr.ap-southeast-2.amazonaws.com/$bamboo_SERVICE_NAME:latest-$TAG_NAME
