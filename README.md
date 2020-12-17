# lifehacker-test-project

## Описание проекта
* Процесс разработки и основные этапы и время выполнения можно увидеть во вкладке закрытых задач [Issues](https://github.com/realist-pessimist/lifehacker-test-project/issues?q=is%3Aissue+is%3Aclosed) данного репозитория  
* В данном проекте реализован паттерн проектирования *MVVM*
* Сетевая инфраструктура построена на основе библиотек *Retrofit* и *Okhttp3*, построение асинхронных запросов осуществляется по средствам библиотек *Coroutines* и *Moshi* 
* Для загрузки картинок используется библиотека *Glide*
* Для минимизации количества кода в приложении используются библиотека *Dagger 2*
* Список постов расположен в Recycler View и при нажатии на элемент передает Id поста, который передается во второе окно, \n после этого формируется запрос на получение более детальной информации

## Структура проекта
Проект разделен на слои по принципу Clean Architecture.
* `base` - модуль содержит базовые компоненты.
* `data_layer` - модуль содержит классы для работы с данными 
* `app` - модуль содержит презентационный слой приложения

## Возможная доработка
* Для более чистой обработки ответа сервера можно реализовать библиотеку *Jackson for Kotlin*, которая позволяет десериализовать JSON-ответ [Jackson Library](https://www.baeldung.com/jackson-kotlin)
* В связи с тем, что оснавная функция приложения - просмотр постов полученных от сервера, стоит реализовать кэширование данных, сохраняя их в локальную базу данных ([Room](https://developer.android.com/topic/libraries/architecture/room), [SQLite](https://developer.android.com/training/data-storage/sqlite))
* Тестирование, с возвожным изменением сетевых запросов *Coroutines* для ускорения и оптимизации получения данных
* При увеличении функционала, проект можно разделить на модули, вынесев модуль `base` и `data` в отдельные библиотеки