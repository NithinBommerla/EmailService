# EmailService

A simple Spring Boot application for sending emails, with support for HTML content. Integrates with Kafka for messaging.

## Features

- Send HTML emails using JavaMail
- RESTful API with Spring Boot
- Kafka integration for message-driven email sending

## Requirements

- Java 17+
- Maven 3.6+
- (Optional) Kafka broker for message-driven features

## Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/NithinBommerla/EmailService.git
   cd EmailService
   ```

2. **Configure application properties:**

   Edit `src/main/resources/application.properties` to set your server port and other settings.

3. **Add email SMTP configuration:**

   Add the following to `application.properties`:
   ```
   spring.mail.host=smtp.example.com
   spring.mail.port=587
   spring.mail.username=your_email@example.com
   spring.mail.password=your_password
   spring.mail.properties.mail.smtp.auth=true
   spring.mail.properties.mail.smtp.starttls.enable=true
   ```

4. **Build the project:**
   ```sh
   mvn clean install
   ```

5. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

## Usage

- The service exposes REST endpoints (to be documented) for sending emails.
- Kafka integration allows sending emails based on messages from a Kafka topic.

## Project Structure

- `src/main/java/dev/nithin/emailservice/` - Main source code
- `src/main/resources/application.properties` - Application configuration
- `pom.xml` - Maven dependencies

## Dependencies

- Spring Boot
- Spring Kafka
- JavaMail (javax.mail)
- Lombok (optional)


Replace SMTP and repository details as needed.