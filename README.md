# FWA

##Веб приложение использующее Java Servlet API стек.

Приложение позволяет регистрироваться новым пользователям,
проходить аутентификацию существующим,
загружать фотографии и видеть историю логинов пользователя.
C помощью [servlets][1], [filters][2], [listeners][3], [repositories][4], [services][5].

[1]: https://github.com/vicon22/FWA/tree/main/Cinema/src/main/java/edu/school21/cinema/servlets
[2]: https://github.com/vicon22/FWA/tree/main/Cinema/src/main/java/edu/school21/cinema/filters
[3]: https://github.com/vicon22/FWA/tree/main/Cinema/src/main/java/edu/school21/cinema/listeners
[4]: https://github.com/vicon22/FWA/tree/main/Cinema/src/main/java/edu/school21/cinema/repositories
[5]: https://github.com/vicon22/FWA/tree/main/Cinema/src/main/java/edu/school21/cinema/services

---

**Servlet container**: Apache Tomcat 9.0.63\
**Model Builder**: Apache Maven\
Данные для конфигурации базы данных храняться в [**application.properties**][6]

[6]: https://github.com/vicon22/FWA/blob/main/Cinema/src/main/resources/application.properties

Для запуска приложения требуется запустить сначала класс **Main**.
Main создаст схему и две таблицы: `fwa.users` и `fwa.sessions`. После этого можно поднимать сервер и все будет работать корректно.

Для тестирования аутентификации можно воспользоваться уже зарегистрированным пользователем со следующими данными:\
**Email**: `z.isupov@gmail.com`\
**Password**: `pleaseHireMe`

---

Веб-приложение предоставляет HTML-страницы регистрации и аутентификации в ответ на URL-запросы */signIn* и */signUp*.

**/signIn** и **/signUp**:

[comment]: <> (![signIn]&#40;imagesForMD/SignUp.png&#41;)
<img src="https://github.com/vicon22/FWA/blob/main/imagesForMD/SignUp.png" width="350">
<img src="https://github.com/vicon22/FWA/blob/main/imagesForMD/SignIn.png" width="350">

[comment]: <> (![signUp]&#40;imagesForMD/SignIn.png&#41;)

Все данные передаются сервлету SignUp в запросе POST с использованием HTML-тега form.
Информация хранится в базе данных, а пароль шифруется с использованием алгоритма **BCrypt**.

Когда запрос POST отправляется сервлету SignIn с адресом электронной почты и паролем, выполняется проверка, существует ли соответствующий пользователь в базе данных,
а также правильность его пароля. В случае успешной проверки должен быть сгенерирован объект HttpSession с пользовательским атрибутом.

В случае неудачной аутентификации пользователь перенаправляется обратно на страницу входа.
Spring context доступен для всех сервлетов через [**ServletContextListener**][7].

[7]: https://github.com/vicon22/FWA/blob/main/Cinema/src/main/java/edu/school21/cinema/listeners/AppListener.java

Данные для подключения к базе доступны в **application.properties**.

---

- Доступ к странице профиля только для аутентифицированных пользователей.
- В целях безопасности создан [**Filter**][2], который может обрабатывать запросы на /profile. Этот фильтр будет проверять наличие атрибута в текущем сеансе. Если атрибут найден, должен быть предоставлен доступ к запрошенному ресурсу (*/profile* в нашем случае).
- Страницы для URL-адресов */signUp* и */signIn* могут быть получены для несанкционированных запросов. Если атрибут присутствует, пользователь будет перенаправлен на страницу */profile*.

Также в случае несанкционированного запроса страницы, требующей атрибута, вернется статус 403 (FORBIDDEN).

Реализована страница профиля в виде файла JSP. На странице отображаются следующие текущие данные пользователя:

- Имя
- Фамилия
- email

Информация о дате/времени/IP-адресе всех аутентификаций пользователей в виде списка. На странице есть функция загрузки «аватара» пользователя.
