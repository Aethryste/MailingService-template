# MailingService (template)
## Overview
This repo contains a SMTP 'mailing service' template for springboot backends.
## Requirements
Please create 'application.properties' in the 'resources' folder and specify the following settings within:
```properties
spring.mail.host=YOUR_SMTP_HOST
spring.mail.port=YOUR_SMTP_PORT
spring.mail.username=YOUR_SMTP_USERNAME
spring.mail.password=YOUR_SMTP_PASSWORD
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.enable=true
```
## Getting Started
1. Clone the repository.
2. Install dependencies.
3. Run _Application.java_
## Usage
To send an email using this service, please send a post request (JSON) using the following format to your spring application:
```json
{
  "to": "example@domain.com",
  "subject": "EmailSubjectHere",
  "body": "The Body of your email goed here!"
}
```
## License
This project is licensed under the [MIT License](https://opensource.org/license/mit).
