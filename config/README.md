##MealRestController

getAll - получение всей еды

curl --location --request GET 'http://localhost:8080/topjava_war_exploded/rest/admin/meals'
***
getMeal - получение блюда по id

curl --location --request GET 'http://localhost:8080/topjava_war_exploded/rest/admin/meals/100003'
***
delete - удаление блюда по id

curl --location --request DELETE 'http://localhost:8080/topjava_war_exploded/rest/admin/meals/100003'
***
update - редактирование еды

curl --location --request PUT 'http://localhost:8080/topjava_war_exploded/rest/admin/meals/100004'
***
createWithLocation - создание новой еды

curl --location --request POST 'http://localhost:8080/topjava_war_exploded/rest/admin/meals/'
***
filter - получение еды с фильтрацией по времени

curl --location --request GET 'http://localhost:8080/topjava_war_exploded/rest/admin/meals/filter'
***
