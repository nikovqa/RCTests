# Автоматизированное тестирование сайта Redcollar

## :page_with_curl:    Содержание

➠ [Покрытый функционал](#globe_with_meridians-покрытый-функционал)

➠ [Технологический стек](#computer-технологический-стек)

➠ [Запуск тестов из терминала](#technologist-запуск-тестов-из-терминала)

➠ [Удаленный запуск тестов](#удаленный-запуск-тестов)

➠ [Сборка в Jenkins](#-главная-страница-сборки-Jenkins)

➠ [Отчет о результатах тестирования в Allure Report](#-отчет-о-результатах-тестирования-в-allure-report)

➠ [Уведомления в Telegram с использованием бота](#-уведомления-в-telegram-с-использованием-бота)

➠ [Пример запуска теста в Selenoid](#-пример-запуска-теста-в-selenoid)
## <a name="globe_with_meridians-покрытый-функционал"></a>:globe_with_meridians: Покрытый функционал

### UI

- [x] Проверка актуальной контактной информации на сайте
- [x] Проверка перехода на страницу проектов из меню сайта
- [x] Проверка доступности вакансий на сайте
- [x] Проверка формы отклика на вакансию

## :computer: Технологический стек

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

```mermaid        
    stateDiagram-v2
        State1: START
        State2: Java & IntelliJ IDEA
        State3: Selenide & JUnit5
        State4: Gradle
        State5: GitHub
        State6: Jenkins
        State7: Selenoid
        State8: Allure Report
        State9: Telegram
        State10: STOP
        State1 --> State2
        State2 --> State3
        State3 --> State4
        State4 --> State5
        State5 --> State6
        State6 --> State7
        State7 --> State8
        State8 --> State9
        State9 --> State10
        note right of State2 : Работа с кодом
        note left of State3 : Фреймворки
        note right of State4 : Сборка проекта
        note left of State5 : Система контроля версий и хостинг проекта
        note right of State6 : Параметризация и запуск сборки
        note left of State7 : Контейнеризация
        note right of State8 : Отчётность
        note left of State9 : Уведомления
```
## :man_technologist: Запуск тестов из терминала

### Локальный запуск тестов

```
gradle clean test -Denv=local
```
При необходимости можно переопределить параметры запуска
```
gradle clean test
-Denv=local
-Dbrowser=${BROWSER_NAME}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbaseUrl=${BASE_URL}
```

### Запуск тестов на удаленном браузере

```
gradle clean test -Denv=remote
```
Для запуска web-тестов в selenoid
```
gradle clean test -Denv=remoteUrl
```

При необходимости также можно переопределить параметры запуска

```
gradle clean test -Denv=remote
-Dbrowser=${BROWSER_NAME}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbaseUrl=${BASE_URL}
-DremoteUrl=${REMOTE_BROWSER_URL}
```

### Параметры сборки

> <code>BROWSER</code> – браузер, в котором будут выполняться тесты (_по умолчанию - <code>chrome</code>_).
>
> <code>BROWSER_SIZE</code> – размер окна браузера (_по умолчанию - <code>1920x1080</code>_).
> 
> <code>BASE_URL</code> – Url, по которому будет открываться тестируемое приложение.
> 
> <code>BROWSER_REMOTE_URL</code> –  адрес удаленного сервера, на котором будут запускаться тесты.
>
> <code>TASK</code> – приоретет запуска по _siverity_ (_<code>critical, normal, any</code>_). 
>


## <img width="4%" style="vertical-align:middle" title="Jenkins" src="images/logo/Jenkins.svg"> Сборка в Jenkins
<p align="center">
<img title="Jenkins Build" src="images/screenshots/Jenkins.png">
</p>

## <img width="4%" style="vertical-align:middle" title="Allure Report" src="images/logo/Allure_Report.svg"> Пример Allure-отчета
### Overview

<p align="center">
<img title="Allure Overview" src="images/screenshots/allure_overview.png">
</p>

### Результат выполнения теста

<p align="center">
<img title="Test Results in Alure" src="images/screenshots/allure_behaviors.png">
</p>

### Основной дашборд

<p align="center">
<img title="Allure Overview Dashboard" src="images/screenshots/allure_overview_dashboard.png">
</p>

### <img width="4%" style="vertical-align:middle" title="AllureTO" src="images/logo/AllureTO.svg"> Интеграция с Allure TestOps
Тестовая сборка в Jenkins интегрирована с Allure TestOps. В Allure TestOps автоматически обновляется список тест-кейсов и результаты выполнения тестов.
<p align="center">
<img width="100%" title="Allure TestOps Cases - Feature view" src="images/screenshots/allure_testops.png">
</p>

### Интеграция с Jira
Также полученные из автотестов тест-кейсы прикреплены к задаче в Jira.
<p align="center">
<img width="100%" title="Allure TestOps Cases - Feature view" src="images/screenshots/jira.png">
</p>

### <img width="4%" style="vertical-align:middle" title="Telegram" src="images/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки специальный бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне.

<p align="center">
<img width="70%" title="Telegram Notifications" src="images/screenshots/telegram_notifications.png">
</p>

### <img width="4%" style="vertical-align:middle" title="Selenoid" src="images/logo/Selenoid.svg"> Примеры видео запуска тестов в Selenoid и Browserstack

При запуске тестов в remote-окружении к каждому тесту в отчете прилагается видео.
<p>Пример видео из Selenoid</p>
<p align="center">
  <img title="Selenoid Video" src="images/gif/selenoid_video.gif">
</p>
