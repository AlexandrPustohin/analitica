# analitica
Исходное задание: <br>
Необходимо реализовать сервис для анализа покупок клиентов.<br><br><br>

1	Сервис должен представлять из себя WEB приложение, написанное на Spring Framework + MySql.<br> 
2	В веб приложении должна работать фоновая задача на загрузку чеков клиентов.<br>
Фоновая задача проверяет наличие новых файлов в определенной папке (должна задаваться через application.properties).<br> 
Если в данной папке есть XML файл с чеками, то фоновая задача начинает загрузку чеков в базу данных.<br>
3.	В файлах перечислен список чеков. По каждому чеку содержится следующая информация:<br>
    а.	Номер карты покупателя<br>
    б.	Дата чека (в формате UNIX time)<br>
    в.	Покупка (артикул товара,название товар, количество товара, Стоимость единицы товара)<br>
4.	Фоновая задача запускается один раз в 10 минут.<br>
5.	В Web приложении можно посмотреть статистику по загруженным чекам.<br>
  a.	Общую сумму покупок за день – есть возможность задать дату в поле формы и увидеть сумму покупок за этот день.<br>
  b.	Топ 3 товара для заданного номера КПП – есть возможность задать номер в поле формы и увидеть 3 самых покупаемых товара для переданного номера.<br>
Пример файла (все в одну строку):" <?xml version="1.0" ?><SALES><SALE><CARD_NUMBER>78483</CARD_NUMBER><DATE>1528205653605</DATE>
<PRODUCTS><PRODUCT><PRODUCT_CODE>15</PRODUCT_CODE><NAME>Чипсы</NAME><PRICE>47,20</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>3</PRODUCT_CODE><NAME>Вода</NAME><PRICE>25,99</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>1</PRODUCT_CODE><NAME>Молоко</NAME><PRICE>19,50</PRICE><COUNT>3</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>7</PRODUCT_CODE><NAME>Макароны 'Сытый боярин'</NAME><PRICE>28,00</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>13</PRODUCT_CODE><NAME>Котлеты</NAME><PRICE>137,80</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>5</PRODUCT_CODE><NAME>Печенье Юбилейное</NAME><PRICE>30,00</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>3</PRODUCT_CODE><NAME>Вода</NAME><PRICE>25,99</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>6</PRODUCT_CODE><NAME>Тушенка 'Сытый боярин'</NAME><PRICE>90,80</PRICE><COUNT>1</COUNT></PRODUCT>
<PRODUCT><PRODUCT_CODE>8</PRODUCT_CODE><NAME>Сгущенное молоко 'Сытый боярин'</NAME><PRICE>27,40</PRICE><COUNT>1</COUNT></PRODUCT></PRODUCTS></SALE>...</SALE>" <br>

Седлано:<br>
Класс работы с файлами - запускается по расписанию (Аннотация @Scheduled(fixedRateString = "${FixedRate}") на метод - getListFileFromDir() класса <br>
Класс парсинга файла ParseXML - делает разбор файла согласно структуре и сохраняет в БД через сервисный слой (SaleServiceImpl) <br>
Сервисный слой работает с БД (MySQL) через интерфейс JpaRepository.<br>
Созданы следующие класса: <br>
1. Sale - продажа (номер карты ПП, дата продажи, список продуктов).<br>
2. Product - клас продукта(товара) (артикул, наименованиеб цена, кол-во)<br>
3. Классы DTO для Sale и Product - для работы представлением<br>
4. Классы SaleFactory и  ProductFactory - для конвертации из DTO и обратно<br>
5. Классы репозитории для Sale и Product<br>
Добвалены обработчики исключений, выявленых по ходу работы (проверки). <br>
Создан контролер для работы с фрон-энд стороной - SaleController<br>
Разработаны Html представления (страницы с использованием Bootstsrap)<br>
1. index.html - главная страница<br>
2. sale.html - список продаж<br>
3. products.html - список продуктов (информация по конклетной продаже)<br>
4. bestproducts.html - топ трех продуктов проданных по картепокупателя<br>
в файле application.properties указаны настройки для подключения к БД<br>
переменная mypath - указывается папка для файлов загрузки продаж <br>
FixedRate  - период через который запускается обработка файлов <br>

Проект написан с использованием фреймфорка Spring Boot. Повсеместно использовалиь лямбды. В репозитории применены запросы с параметрами<br>
Для представления (страниц) - шаблонизатор thymeleaf.








