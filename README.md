# SMS Sender Application

The SMS Sender Application is a Spring Boot-based application designed to send SMS messages using the Twilio API. It demonstrates the integration of third-party messaging services within a Java backend environment.

## Features

- Send SMS messages through Twilio's robust messaging API.
- Easy configuration via application properties.
- Simple and clear REST endpoints for sending SMS.
- Detailed logging for debugging and monitoring the application behavior.

## Prerequisites

Before you can run this project, you need to have the following installed:

- Java JDK 17 or later
- Maven 3.6 or higher
- An active Twilio account with a verified phone number and available SMS capabilities

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/unknown1fsh/sms-sender.git
   cd sms-sender
   ```

2. **Configure Twilio Credentials:**

   Open `src/main/resources/application.properties` and replace the following placeholders with your actual Twilio account details:

   ```properties
   twilio.account.sid=YOUR_TWILIO_ACCOUNT_SID
   twilio.auth.token=YOUR_TWILIO_AUTH_TOKEN
   twilio.phone.number=YOUR_TWILIO_PHONE_NUMBER
   ```

3. **Build the Application:**

   Use Maven to build and package the application:

   ```bash
   mvn clean install
   ```

## Running the Application

To run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

This command starts the application on `http://localhost:8080`. You can interact with the application via REST API endpoints using tools like Postman or Curl.

## API Usage

### Send SMS

- **Endpoint:** `POST /api/sms/send`
- **Payload:**

  ```json
  {
    "to": "+1234567890",
    "message": "Hello from Twilio!"
  }
  ```

- **Curl Example:**

  ```bash
  curl -X POST http://localhost:8080/api/sms/send \
  -H 'Content-Type: application/json' \
  -d '{"to": "+1234567890", "message": "Hello from Twilio!"}'
  ```

## Contributing

Contributions are what make the open-source community such a fantastic place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Contact

Selim Sercan ÇINAR - [@ssercanc]([https://twitter.com/your_twitter](https://www.linkedin.com/in/ssercanc/))

Project Link: [https://github.com/unknown1fsh/sms-sender](https://github.com/unknown1fsh/sms-sender)
