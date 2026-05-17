FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

# Alinha quebras de linha Windows -> Linux
RUN sed -i 's/\r$//' gradlew
RUN chmod +x ./gradlew

# CONFIGURAÇÃO DE DIETA: Limita o Gradle a usar no máximo 256MB de RAM para não estourar o Render
ENV GRADLE_OPTS="-Dorg.gradle.jvmargs=-Xmx256m -XX:MaxMetaspaceSize=64m"

# Executa o build com os parâmetros de economia de memória ativa
RUN ./gradlew clean build -x test --no-daemon --stacktrace

EXPOSE 8080

# Inicializa o jar principal gerado
ENTRYPOINT ["sh", "-c", "java -jar build/libs/*SNAPSHOT.jar"]