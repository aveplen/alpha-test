# alpha-test
Тестовое задание на позицию Junior-Java разработчик в Альфа-Банке.

## Описание
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
- если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
- если ниже - отсюда https://giphy.com/search/broke

## Ссылки
- REST API курсов валют - https://docs.openexchangerates.org/
- REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide

## Must Have
- Сервис на Spring Boot 2 + Java / Kotlin
  > Java.
- Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD
  > HTTP endpoint /redirect - по запросу редиректит на embed-gif.
  > По умолчанию берется курс рубля, параметром cur можно передать другую валюту, пример: /redirect?cur=EUR. 
- Для взаимодействия с внешними сервисами используется Feign
  > Использутеся)
- Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
  > src/main/resources/application.properties.
- На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
  > MockBean.
- Для сборки должен использоваться Gradle
  > Используется.
- Результатом выполнения должен быть репо на GitHub с инструкцией по запуску
  > Инструкция ниже.

## Nice to Have
- Сборка и запуск Docker контейнера с этим сервисом

## Запуск
```shell
добавить api-токены в application.properties
docker build -t aveplen/alpha-test .
docker run --rm -p 8080:8080 aveplen/alpha-test
стучимся из браузера на localhost:8080/redirect
```
или
```shell
добавить api-токены в application.properties
./gradlew bootJar
java -jar /build/libs/alpha-0.0.1-SNAPSHOT.jar
стучимся из браузера на localhost:8080/redirect
```
