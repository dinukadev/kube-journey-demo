#!/bin/bash

POLICY_PATH=ecr_policy.json

createRepo() {
REPO_NAME=$1
ACCOUNT_ID=$2
REGION=$3
eval `aws ecr get-login --no-include-email --region $REGION`
aws ecr describe-repositories --registry-id $ACCOUNT_ID --repository-names $REPO_NAME --region=$REGION || aws ecr create-repository --repository-name $REPO_NAME --region=$REGION && aws ecr set-repository-policy --registry-id $ACCOUNT_ID --repository-name $REPO_NAME --region=$REGION --policy-text  "file://$POLICY_PATH"

}

createRepo ms-kubejourney 066170451082 ap-southeast-2


