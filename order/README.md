# Order microservice

##Environment variable

* ORDER_BUCKET=aws-docker-devry-order
* ORDER_QUEUE=https://sqs.us-east-1.amazonaws.com/613487155689/order-queue
* PAYMENT_QUEUE=https://sqs.us-east-1.amazonaws.com/613487155689/payment-queue
* PG_USER=postgres
* PG_PASS=devry
* PG_HOST=localhost
* PG_DATABASE=postgres
* AWS_ACCESS_KEY_ID=<access-key>
* AWS_SECRET_ACCESS_KEY=<secret-key>

# Run postgres on docker

docker run --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=devry -d postgres