application-test.yaml    -- это конфиг тестовой БД
Сама БД -- in memory. Запускается в момент теста, наполняется данными.
После теста БД просто исчезает

Саму БД делаем также в папке resourses: папку db testdata

application-dev.yaml   --  файл конфигов для работы в профиле через wiremock
В случае включения wiremock, он накладывается на дефолтный application.yaml и настойки, указанные в
application-dev.yaml  , будут заменять соответствующие настройки, указанные в дефолтном application.yaml
(перезаписывают данные в application.yaml  )