# 

## Model
www.msaez.io/#/162076709/storming/009803f6e6645b43b70d4406630eca5c

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- authormanagement
- manuscript
- aipublication
- book
- usermanagement
- subcription
- point


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- authormanagement
```
 http :8088/authors authorId="authorId"name="name"bio="bio"portfolio="portfolio"registrationStatus="registrationStatus"
```
- manuscript
```
 http :8088/manuscripts manuscriptId="manuscriptId"authorId="authorId"title="title"content="content"createdAt="createdAt"updateAt="updateAt"
```
- aipublication
```
 http :8088/publications publicationId="publicationId"manuscriptId="manuscriptId"summary="summary"postUrl="postUrl"title="title"authorId="authorId"publicAt="publicAt"content="content"bookId="bookId"
```
- book
```
 http :8088/books bookId="bookId"title="title"summary="summary"viewCount="viewCount"isBestSeller="isBestSeller"
```
- usermanagement
```
 http :8088/members userId="userId"bookId="bookId"name="name"email="email"subscribeStatus="subscribeStatus"isKtUser="isKTUser"
```
- subcription
```
 http :8088/subcriptions subscribeId="subscribeId"userId="userId"subscriptionStartedAt="subscriptionStartedAt"subscriptionExpiredAt="subscriptionExpiredAt"
```
- point
```
 http :8088/pointAccounts pointAccountId="pointAccountId"userId="userId"balance="balance"
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
