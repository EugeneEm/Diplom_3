# Diplom_3
Для запуска в Firefox используется параметр -Dbrowser=firefox
~~~
mvn clean test -Dbrowser=firefox
~~~

Для запуска в Яндекс Браузере используются параметры: 
* -Dbrowser=yandex 
* -Ddriver.version=%версия_браузера% 
* -Dwebdriver.yandex.path=%ваш_путь_до_браузера%
~~~
mvn clean test -Dbrowser=yandex -Dbrowser=yandex -Ddriver.version=128.0.6613.0 -Dwebdriver.yandex.path=%путь%
~~~

Версии https://googlechromelabs.github.io/chrome-for-testing/known-good-versions.json

