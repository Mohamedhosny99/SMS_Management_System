# ***AWS Deployment (Amazon ECS)***

1. Build and Tag Docker Image

docker build -t sms_web_app .
docker tag sms_web_app your-dockerhub-username/sms_web_app

2. Push Image to Docker Hub

docker push your-dockerhub-username/sms_web_app

3. Create RDS (PostgreSQL)
	•	Publicly accessible
	•	Note the endpoint, port, username, and password
	•	Update your DBconnection.java with these values

4. Create ECS Cluster
	•	Use Fargate
	•	Add a Task Definition with:
	•	Container using your-dockerhub-username/sms_web_app
	•	Port mapping: 8080

5. Run ECS Service
	•	Select VPC and subnets
	•	Assign a security group that allows inbound TCP 8080
	•	Ensure RDS SG allows inbound TCP 5432

6. Access the App

  http://public-ip-or-load-balancer:8080



