FROM rodrigosaito/java:8

# TODO 01 Dockerfile

VOLUME /tmp

ADD ./target/spring-boot-live-1.0-SNAPSHOT.jar /app/spring-boot-live.jar

ENV TIMEZONE=America/Sao_Paulo \
    ENVIRONMENT=dev \
    AMQP_URL=amqp://guest:guest@localhost:5672 \
    ELASTIC_URL=http://localhost:9200 \
    REDIS_HOST=localhost \
    REDIS_PORT=6379 \
    REDIS_DB=0 \
    DB_URL='jdbc:mysql://localhost:3306/techtalk?autoReconnect=true&useSSL=false' \
    DB_USER=root \
    DB_PASS=root \
    BEAVER_KEY=logstash \
    ADMIN_ACTUADOR_URL='http://localhost:8080' \
    ADMIN_PATH='/adm'

RUN sh -c 'touch /app/spring-boot-live.jar' && \
    echo "${TIMEZONE}" > /etc/timezone

EXPOSE 8080

WORKDIR /app

ENTRYPOINT [ "java",                                 \
  "-Djava.security.egd=file:/dev/./urandom",         \
  "-Dspring.profiles.active=${ENVIRONMENT}",         \
  "-Dspring.rabbitmq.addresses=${AMQP_URL}",         \
  "-Dspring.elasticsearch.jest.uris=${ELASTIC_URL}", \
  "-Dspring.redis.host=${REDIS_HOST}",               \
  "-Dspring.redis.port=${REDIS_PORT}",               \
  "-Dspring.redis.database=${REDIS_DB}",             \
  "-Dspring.datasource.url=${DB_URL}",               \
  "-Dspring.datasource.username=${DB_USER}",         \
  "-Dspring.datasource.password=${DB_PASS}",         \
  "-Dbeaver.key=${BEAVER_KEY}",                      \
  "-Dspring.boot.admin.url=${ADMIN_ACTUADOR_URL}",   \
  "-Dspring.boot.admin.context-path=${ADMIN_PATH}",  \
  "-jar", "spring-boot-live.jar" ]