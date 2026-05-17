# Usa o Java 21 (ou 17 se você não tiver mudado)
FROM eclipse-temurin:21-jdk-alpine

# Cria a pasta de trabalho
WORKDIR /app

# Copia seus arquivos
COPY . .

# REMÉDIO 1: Converte as quebras de linha do Windows para o padrão Linux
RUN sed -i 's/\r$//' gradlew

# Dá permissão para rodar
RUN chmod +x ./gradlew

# REMÉDIO 2: Adiciona o "--no-daemon" para gastar menos memória do servidor gratuito
RUN ./gradlew clean build -x test --no-daemon

# Libera a porta da web
EXPOSE 8080

# Comando para ligar o site
ENTRYPOINT ["sh", "-c", "java -jar build/libs/*SNAPSHOT.jar"]