Для автоматизации тестирования написан фреймворк имеющий архитектуру с применением паттерна 'Page object', использованием принципов OOP и разбитый на логические слои для удобного поддержания и дальнейшего
расширения. Проект полностью содержит все настройки и инструменты для полноценного написания тестов, их прогона, возможности просмотра отчета и работы с сервером непрерывной интеграции.


Стеком технологий служит:

- Java (один из самых востребованных и используемых языков не только на рынке но и в автоматизации в частности, обладающий мощной реализацией OOP, обширными дополнительными библиотеками для решения любых задач
        и сообществом поддержки)

- Selenium WebDriver  (промышленный стандарт и самый популярный инструмент для автоматизации тестирования UI, обладает мощным функционалом и решает все необходимые задачи по работе с различными браузерами 
                       эмулируя поведение пользователей)

- TestNG (тестовый фреймворк, позволяющий запускать наши тесты и управлять разными вариациями этих запусков, содержит встроенный функционал по передаче параметров в тесты, разделении их на suites по видам 
          тестирования или разделенным модулям приложения, так же имеет возможность распараллеливания тестов, имеет большое количество аннотаций для всевозможного построения этапов запуска)

- Maven (библиотека для сборки нашего приложения, хранения результатов и их очистки, возможностью запуска тестов через терминал и управления всем проектом с подключением всех зависимостей и плагинов для работы
         со сторонними сервисами)

- Allure (самая востребованная на сегодняшний день библиотека для построения презентабельных и точных отчетов прохождения тестирования, способная описывать каждый шаг кейса, показывать все используемые данные
          и делать при необходимости скриншоты приложения)

- Jenkins ci (бесплатный сервер непрерывной интеграции на котором можно настроить автоматический прогон тестов по графику либо коммиту в репозиторий и отправлять уведомления об его окончании, так же позволяет 
              просматривать полноценный отчет allure) 



Архитектура фреймворка разбита на такие слои(package):

- Core (содержит базовые классы по инициализации driver и webDriverWait с необходимыми настройками с последующей передачей его в функциональный слой)

- PageObjects (пакет содержащий все классы которые по отдельности инкапсулируют в себе страницы сайта, содержат webElements (buttons, links, fields, dropdowns, text) и методы доступа к ним, также содержат 
               методы для работы с элементами)

- TestsInfo (слой содержащий все необходимые данные для всех тестов проекта, такие как urls, notifications, errors, parrols and passwords)

- Utilities (функциональные классы которые выполняют все возможные логические действия и проверки по работе с элементами и страницами)

- Test (содержит весь набор тестов)

- Target (генерируемая папка после прохождения тестирования, создаваемая инструментом сборки, содержит логи и отчеты тестирования, именно из нее берутся данные и формируется отчет)

- Pom.xml (файл maven, описывает зависимости (подключаемые библиотеки) для проекта и плагины, которые позволяют расширять функционал инструмента)

- Testng.xml (файл testNg который может включать в себя любой необходимый набор тестов, которые мы хотим прогнать например отдельно либо с индивидуальными параметрами, может параллелить тесты и передавать 
              параметры)