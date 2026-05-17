FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

# Converte as quebras de linha do Windows para o padrão Linux
RUN sed -i 's/\r$//' gradlew

# Dá permissão para rodar
RUN chmod +x ./gradlew

# Constrói o projeto economizando memória
RUN ./gradlew clean build -x test --no-daemon

# Libera a porta da web
EXPOSE 8080

# AJUSTE FINO: Comando direto para o arquivo jar principal gerado pelo build
ENTRYPOINT ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]