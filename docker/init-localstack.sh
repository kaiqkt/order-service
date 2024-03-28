#!/bin/bash

echo "Aguardando o LocalStack iniciar..."
sleep 10

echo "Creating SQS Queues"

awslocal sqs create-queue --queue-name order-sqs
awslocal sqs create-queue --queue-name order-sqs-d

echo "Filas SQS criadas."