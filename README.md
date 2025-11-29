<div align="right">
  <button onclick="switchLanguage('tr')" style="padding: 8px 16px; margin: 5px; background: #667eea; color: white; border: none; border-radius: 5px; cursor: pointer; font-weight: bold;">🇹🇷 Türkçe</button>
  <button onclick="switchLanguage('en')" style="padding: 8px 16px; margin: 5px; background: #764ba2; color: white; border: none; border-radius: 5px; cursor: pointer; font-weight: bold;">🇬🇧 English</button>
</div>

<div id="content-tr" style="display: block;">

# 📱 SMS Sender Uygulaması

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![Twilio](https://img.shields.io/badge/Twilio-API-red.svg)
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

**Twilio API kullanarak SMS göndermek için profesyonel bir Spring Boot uygulaması**

[Özellikler](#-özellikler) • [Kurulum](#-kurulum) • [Kullanım](#-kullanım) • [API Dokümantasyonu](#-api-dokümantasyonu) • [Katkıda Bulunma](#-katkıda-bulunma)

</div>

---

## ✨ Özellikler

- ✅ **Twilio Entegrasyonu** - Güvenilir ve hızlı SMS gönderimi
- ✅ **Veritabanı Loglama** - Tüm SMS gönderimleri MySQL'de kaydedilir
- ✅ **RESTful API** - Modern JSON tabanlı API endpoint'leri
- ✅ **Exception Handling** - Kapsamlı hata yönetimi ve validasyon
- ✅ **Modern Web Arayüzü** - Responsive HTML test sayfası
- ✅ **CORS Desteği** - Cross-origin isteklere açık
- ✅ **Logging** - Detaylı loglama ile izleme ve hata ayıklama

## 📋 Gereksinimler

- **Java JDK 17** veya üzeri
- **Maven 3.6** veya üzeri
- **MySQL 8.0** veya üzeri
- **Twilio Hesabı** - Aktif bir Twilio hesabı ve doğrulanmış telefon numarası

## 🚀 Kurulum

### 1. Projeyi Klonlayın

```bash
git clone https://github.com/cinargarage/sms-sender.git
cd sms-sender
```

### 2. Veritabanını Yapılandırın

MySQL veritabanını oluşturun:

```bash
# Windows PowerShell
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p12345 -e "CREATE DATABASE IF NOT EXISTS sms_sender CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
```

veya manuel olarak MySQL'de:

```sql
CREATE DATABASE IF NOT EXISTS sms_sender 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
```

### 3. Uygulama Ayarlarını Yapılandırın

`src/main/resources/application.properties` dosyasını düzenleyin:

```properties
# MySQL Veritabanı Ayarları
spring.datasource.url=jdbc:mysql://localhost:3306/sms_sender?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

# Twilio ayarları
twilio.account.sid=YOUR_TWILIO_ACCOUNT_SID
twilio.auth.token=YOUR_TWILIO_AUTH_TOKEN
twilio.phone.number=YOUR_TWILIO_PHONE_NUMBER
```

> 💡 **Not:** Twilio bilgilerinizi [Twilio Console](https://console.twilio.com/) üzerinden alabilirsiniz.

### 4. Projeyi Derleyin

```bash
mvn clean install
```

### 5. Uygulamayı Çalıştırın

```bash
mvn spring-boot:run
```

Uygulama `http://localhost:8080` adresinde çalışacaktır.

## 💻 Kullanım

### Web Arayüzü

Uygulama çalıştıktan sonra tarayıcınızda şu adresi açın:

```
http://localhost:8080/index.html
```

Modern ve kullanıcı dostu arayüzden SMS gönderebilirsiniz.

### API Kullanımı

#### SMS Gönder

**Endpoint:** `POST /api/sms/send`

**Request Body:**
```json
{
  "to": "+905551234567",
  "message": "Merhaba! Bu bir test mesajıdır."
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "SMS başarıyla gönderildi!",
  "data": null,
  "timestamp": "2024-01-15T10:30:00"
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Validation hatası",
  "data": {
    "to": "Geçerli bir telefon numarası formatı giriniz (örn: +905551234567)",
    "message": "Mesaj içeriği boş olamaz"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

**cURL Örneği:**
```bash
curl -X POST http://localhost:8080/api/sms/send \
  -H "Content-Type: application/json" \
  -d '{
    "to": "+905551234567",
    "message": "Merhaba! Bu bir test mesajıdır."
  }'
```

**JavaScript Fetch Örneği:**
```javascript
fetch('http://localhost:8080/api/sms/send', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    to: '+905551234567',
    message: 'Merhaba! Bu bir test mesajıdır.'
  })
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

## 📚 API Dokümantasyonu

### Validasyon Kuralları

- **Telefon Numarası (`to`):**
  - Boş olamaz
  - E.164 formatında olmalıdır (örn: `+905551234567`)
  - Ülke kodu ile başlamalıdır

- **Mesaj (`message`):**
  - Boş olamaz
  - Maksimum 1600 karakter
  - UTF-8 karakter desteği

### Hata Kodları

| HTTP Status | Açıklama |
|------------|----------|
| 200 OK | İstek başarılı |
| 400 Bad Request | Geçersiz istek (validasyon hatası) |
| 500 Internal Server Error | Sunucu hatası |

## 📁 Proje Yapısı

```
sms-sender/
├── src/main/java/com/cinargarage/sms_sender/
│   ├── controller/          # REST API Controller'ları
│   ├── service/             # İş mantığı servisleri
│   ├── repository/          # Veritabanı repository'leri
│   ├── model/               # Entity sınıfları
│   ├── dto/                 # Data Transfer Objects
│   └── exception/           # Exception handler'ları
├── src/main/resources/
│   ├── application.properties  # Uygulama ayarları
│   ├── schema.sql             # Veritabanı şeması
│   └── static/
│       └── index.html         # Web test sayfası
└── pom.xml                    # Maven konfigürasyonu
```

## 🗄️ Veritabanı

Uygulama, gönderilen tüm SMS'leri `sms_logs` tablosuna kaydeder:

| Sütun | Tip | Açıklama |
|-------|-----|----------|
| id | BIGINT | Birincil anahtar |
| to_phone | VARCHAR(20) | Alıcı telefon numarası |
| message | TEXT | Gönderilen mesaj |
| status | VARCHAR(20) | Durum (SENT/FAILED) |
| error_message | TEXT | Hata mesajı (varsa) |
| created_at | DATETIME | Oluşturulma tarihi |

## 🔧 Geliştirme

### Test Çalıştırma

```bash
mvn test
```

### JAR Oluşturma

```bash
mvn clean package
```

Oluşturulan JAR dosyası: `target/sms-sender-0.0.1-SNAPSHOT.jar`

### Uygulamayı JAR ile Çalıştırma

```bash
java -jar target/sms-sender-0.0.1-SNAPSHOT.jar
```

## 🐛 Sorun Giderme

### Veritabanı Bağlantı Hatası

- MySQL servisinin çalıştığından emin olun
- `application.properties` dosyasındaki veritabanı bilgilerini kontrol edin
- Veritabanının oluşturulduğunu doğrulayın

### Twilio Hatası

- Twilio bilgilerinizin doğru olduğundan emin olun
- Twilio hesabınızın aktif olduğunu kontrol edin
- Telefon numaranızın doğrulandığını doğrulayın

### Port Kullanımda Hatası

Varsayılan port 8080 kullanımda ise, `application.properties` dosyasında portu değiştirin:

```properties
server.port=8081
```

## 📝 Lisans

Bu proje Apache License 2.0 lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## 👥 Katkıda Bulunma

Katkılarınızı bekliyoruz! Lütfen şu adımları izleyin:

1. Projeyi fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'inizi push edin (`git push origin feature/AmazingFeature`)
5. Bir Pull Request oluşturun

## 📞 İletişim

github
---

<div align="center">

⭐ Projeyi beğendiyseniz yıldız vermeyi unutmayın!

Made with ❤️ by cinargarage

</div>

</div>

<div id="content-en" style="display: none;">

# 📱 SMS Sender Application

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![Twilio](https://img.shields.io/badge/Twilio-API-red.svg)
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)

**A professional Spring Boot application for sending SMS messages using Twilio API**

[Features](#-features) • [Installation](#-installation) • [Usage](#-usage) • [API Documentation](#-api-documentation) • [Contributing](#-contributing)

</div>

---

## ✨ Features

- ✅ **Twilio Integration** - Reliable and fast SMS delivery
- ✅ **Database Logging** - All SMS transactions are logged in MySQL
- ✅ **RESTful API** - Modern JSON-based API endpoints
- ✅ **Exception Handling** - Comprehensive error handling and validation
- ✅ **Modern Web Interface** - Responsive HTML test page
- ✅ **CORS Support** - Open to cross-origin requests
- ✅ **Logging** - Detailed logging for monitoring and debugging

## 📋 Prerequisites

- **Java JDK 17** or higher
- **Maven 3.6** or higher
- **MySQL 8.0** or higher
- **Twilio Account** - An active Twilio account with a verified phone number

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/cinargarage/sms-sender.git
cd sms-sender
```

### 2. Configure the Database

Create the MySQL database:

```bash
# Windows PowerShell
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p12345 -e "CREATE DATABASE IF NOT EXISTS sms_sender CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
```

Or manually in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS sms_sender 
    CHARACTER SET utf8mb4 
    COLLATE utf8mb4_unicode_ci;
```

### 3. Configure Application Settings

Edit `src/main/resources/application.properties`:

```properties
# MySQL Database Settings
spring.datasource.url=jdbc:mysql://localhost:3306/sms_sender?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

# Twilio Settings
twilio.account.sid=YOUR_TWILIO_ACCOUNT_SID
twilio.auth.token=YOUR_TWILIO_AUTH_TOKEN
twilio.phone.number=YOUR_TWILIO_PHONE_NUMBER
```

> 💡 **Note:** You can get your Twilio credentials from the [Twilio Console](https://console.twilio.com/).

### 4. Build the Project

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will run on `http://localhost:8080`.

## 💻 Usage

### Web Interface

After starting the application, open the following URL in your browser:

```
http://localhost:8080/index.html
```

You can send SMS from the modern and user-friendly interface.

### API Usage

#### Send SMS

**Endpoint:** `POST /api/sms/send`

**Request Body:**
```json
{
  "to": "+1234567890",
  "message": "Hello! This is a test message."
}
```

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "SMS başarıyla gönderildi!",
  "data": null,
  "timestamp": "2024-01-15T10:30:00"
}
```

**Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Validation error",
  "data": {
    "to": "Please enter a valid phone number format (e.g., +1234567890)",
    "message": "Message content cannot be empty"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

**cURL Example:**
```bash
curl -X POST http://localhost:8080/api/sms/send \
  -H "Content-Type: application/json" \
  -d '{
    "to": "+1234567890",
    "message": "Hello! This is a test message."
  }'
```

**JavaScript Fetch Example:**
```javascript
fetch('http://localhost:8080/api/sms/send', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    to: '+1234567890',
    message: 'Hello! This is a test message.'
  })
})
.then(response => response.json())
.then(data => console.log(data))
.catch(error => console.error('Error:', error));
```

## 📚 API Documentation

### Validation Rules

- **Phone Number (`to`):**
  - Cannot be empty
  - Must be in E.164 format (e.g., `+1234567890`)
  - Must start with country code

- **Message (`message`):**
  - Cannot be empty
  - Maximum 1600 characters
  - UTF-8 character support

### Error Codes

| HTTP Status | Description |
|------------|-------------|
| 200 OK | Request successful |
| 400 Bad Request | Invalid request (validation error) |
| 500 Internal Server Error | Server error |

## 📁 Project Structure

```
sms-sender/
├── src/main/java/com/cinargarage/sms_sender/
│   ├── controller/          # REST API Controllers
│   ├── service/             # Business logic services
│   ├── repository/          # Database repositories
│   ├── model/               # Entity classes
│   ├── dto/                 # Data Transfer Objects
│   └── exception/           # Exception handlers
├── src/main/resources/
│   ├── application.properties  # Application settings
│   ├── schema.sql             # Database schema
│   └── static/
│       └── index.html         # Web test page
└── pom.xml                    # Maven configuration
```

## 🗄️ Database

The application logs all sent SMS messages to the `sms_logs` table:

| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key |
| to_phone | VARCHAR(20) | Recipient phone number |
| message | TEXT | Sent message |
| status | VARCHAR(20) | Status (SENT/FAILED) |
| error_message | TEXT | Error message (if any) |
| created_at | DATETIME | Creation date |

## 🔧 Development

### Run Tests

```bash
mvn test
```

### Build JAR

```bash
mvn clean package
```

Generated JAR file: `target/sms-sender-0.0.1-SNAPSHOT.jar`

### Run Application with JAR

```bash
java -jar target/sms-sender-0.0.1-SNAPSHOT.jar
```

## 🐛 Troubleshooting

### Database Connection Error

- Make sure MySQL service is running
- Check database credentials in `application.properties`
- Verify that the database has been created

### Twilio Error

- Make sure your Twilio credentials are correct
- Check that your Twilio account is active
- Verify that your phone number is verified

### Port Already in Use

If the default port 8080 is in use, change the port in `application.properties`:

```properties
server.port=8081
```

## 📝 License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## 👥 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Contact

github

---

<div align="center">

⭐ If you liked this project, don't forget to give it a star!

Made with ❤️ by cinargarage

</div>

</div>

<script>
function switchLanguage(lang) {
  if (lang === 'tr') {
    document.getElementById('content-tr').style.display = 'block';
    document.getElementById('content-en').style.display = 'none';
    localStorage.setItem('preferredLanguage', 'tr');
  } else {
    document.getElementById('content-tr').style.display = 'none';
    document.getElementById('content-en').style.display = 'block';
    localStorage.setItem('preferredLanguage', 'en');
  }
}

// Sayfa yüklendiğinde tercih edilen dili yükle
document.addEventListener('DOMContentLoaded', function() {
  const preferredLang = localStorage.getItem('preferredLanguage') || 'tr';
  switchLanguage(preferredLang);
});
</script>
