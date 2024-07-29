# Wishlist Service

## Descrição

Este serviço gerencia a Wishlist de clientes. Ele permite adicionar, remover, consultar todos os produtos e verificar se um produto está na Wishlist de um cliente.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- MongoDB
- Docker
- Maven
- Cucumber para BDD

## Como Executar

### Pré-requisitos

- Docker e Docker Compose instalados

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/RefPina/wishlist-example.git
   cd wishlist

2. Construa e execute os containers Docker:
    ```bash
    docker-compose up --build

3. O serviço estará disponível em http://localhost:8080.

## API Endpoints
+ POST /wishlist/{customerId}/products/{productId}: Adiciona um produto à Wishlist do cliente
  
   + Exemplo de corpo da requisição:
      ```json
      {
         "productId": "123",
         "name": "Produto Exemplo",
         "price": 99.99,
         "imageUrl": "http://example.com/image.jpg"
      }

+ DELETE /wishlist/{customerId}/products/{productId}: Remove um produto da Wishlist do cliente
+ GET /wishlist/{customerId}: Consulta todos os produtos da Wishlist do cliente
+ GET /wishlist/{customerId}/products/{productId}: Verifica se um determinado produto está na Wishlist do cliente

### Swagger
O Swagger está disponivel em `localhost:8080/swagger-ui/index.html`, para testes e modelos dos endpoints.


## Testes
Os testes de comportamento são escritos em BDD usando Cucumber e estão localizados em `src/test/resources/features`.

Para executar os testes, utilize o Maven:

   ```bash
    mvn test