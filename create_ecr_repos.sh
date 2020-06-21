#!/bin/bash

POLICY_PATH=ecr_policy.json
ACCOUNT_ID=066170451082
REGION=ap-southeast-2

createRepo() {
REPO_NAME=$1
eval `aws ecr get-login --no-include-email --region $REGION`
aws ecr describe-repositories --registry-id $ACCOUNT_ID --repository-names $REPO_NAME --region=$REGION || aws ecr create-repository --repository-name $REPO_NAME --region=$REGION && aws ecr set-repository-policy --registry-id $ACCOUNT_ID --repository-name $REPO_NAME --region=$REGION --policy-text  "file://$POLICY_PATH"

}

createRepo ms-kubejourney


