# Bank Account Service Assignment

This assignment focuses on building a banking service that can manage an unlimited number of transactions for bank accounts. It is a part of a larger collection of services modeling the inner workings of a bank.

## Technologies Used
- Spring Boot
- Lombok
- PostgreSQL
- Docker

## API Documentation
To access the API documentation, you can visit [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). This assignment utilizes Swagger for API documentation.

---

## How to Run the Application

### Prerequisites
- Make sure you have Docker installed on your system.

### Steps
1. Clone this repository to your local machine.
2. Open a terminal and navigate to the project's root directory.
3. Build the Docker image:
   docker build -t bank-account-service .

markdown
Copy code
4. Run the Docker container:
   docker run -p 8080:8080 bank-account-service

yaml
Copy code
5. Access the API documentation at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

---

## API Endpoints
Here are some of the main API endpoints available in this service:

- `/accounts`: Manage bank accounts.
- `/transactions`: Handle transactions for bank accounts.

Feel free to explore the Swagger documentation for more details on these endpoints.


---


## Kullanılan Teknolojiler
- Spring Boot
- Lombok
- PostgreSQL
- Docker

## API Dokümantasyonu
API dokümantasyonuna erişmek için [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) adresini ziyaret edebilirsiniz. Bu ödevde API dokümantasyonu için Swagger kullanılmıştır.

---

## Uygulamayı Nasıl Çalıştırılır

### Ön Koşullar
- Sisteminizde Docker'ın kurulu olduğundan emin olun.

### Adımlar
1. Bu depoyu yerel makinenize klonlayın.
2. Bir terminal açın ve projenin kök dizinine gidin.
3. Docker imajını oluşturun:
   docker build -t bank-account-service .

markdown
Copy code
4. Docker konteynerını çalıştırın:
   docker run -p 8080:8080 bank-account-service

yaml
Copy code
5. API dokümantasyonuna [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) adresinden erişebilirsiniz.

---

## API Endpoint'leri
Bu serviste bulunan başlıca API endpoint'lerinden bazıları aşağıda listelenmiştir:

- `/hesaplar`: Banka hesaplarını yönetin.
- `/işlemler`: Banka hesapları için işlemleri işleyin.

Bu endpoint'leri ve nasıl kullanılacaklarını daha fazla detay için Swagger dokümantasyonunu keşfetmekten çekinmeyin.

---