### Projeto API Banco UBS (Ler, Armazenar, Calcular dados) - Teste GFT

### Pré-requisitos
Banco de Dados MySQL, JDK 8, Git,  Postman

### Banco de Dados
Configurar o arquivo application.properties o usuario e senha do MySql

spring.datasource.username=root (ou outro usuario com permissão para criar Databases)
spring.datasource.password=********

### Git
Clone o projeto https://github.com/dreis2003/ubs-bank-api.git

### Execução
Na pasta do projeto, execute os seguintes comandos:

mvnw clean install
mvnw clean install

### Teste da API
No Postman, crie uma requisição do tipo GET, informando na URL o produto e a quantidade.

http://localhost:8080/estoque/vender/RTIX/3
	

