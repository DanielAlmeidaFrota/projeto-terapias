
FROM eclipse-temurin:21-jdk-alpine

# Cria uma pasta chamada /app dentro do servidor
WORKDIR /app

# Copia todos os seus arquivos para dentro do servidor
COPY . .

# Dá permissão para o Gradle e constrói o projeto (ignorando os testes)
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Liga o aplicativo
ENTRYPOINT ["sh", "-c", "java -jar build/libs/*SNAPSHOT.jar"]