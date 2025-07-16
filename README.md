ДЗ до 21.07.25 на оценку
Слайд 4-9
Новый репозиторий

ITACADEMY_HW5_WebChat
Сущность Пользователь имеет следущие поля:
Логин
Пароль
ФИО
Дата рождения
Дата регистрации
Роль (Пользователь\Админ)
Написать сервлет/api/user на который будут отправляться данные для регистрации при помощи POST запроса:
Логин\Пароль
ФИО
Дата рождения
При регистрации у пользователя роль Пользователь, зарегистрировать пользователя, значит сохранить данные о нём в приложении.

Изначально в системе должен быть зарегестрирован Администратор.

Написать сервлет /api/login на который будут отправляться данные для входа используя POST запрос:

Логин
Пароль
Если не нашли пользователя или не подошел пароль, выдать ошибку.

Вход = сохранить в текущую сессию в атрибут user пользователя под которым вошли.

Сущность Сообщение:

Дата\время отправки
От кого
Кому
Текст
Написать сервлет /api/message, где при СЕТ запросе будет отображаться список сообщений для текущего пользователя, при POST запросе отправляться сообщение:
Кому
Текст
Задача Статистика

Написать листенер, который будет считать в статистику количество активных пользователей

Активные пользователи - активные сессии в которых есть заполненный атрибут user.

Написать в статистике количество пользователей в приложении.

Написать в статистике количество сообщений отрпавленных в приложении.

Написать сервлет /api/admin/statistics, где при GET запросе будет отображаться статистика.

Задача UI + Filter

Написать JSP страницу/ui/ на которой будет главная страница.

Написать JSP страницу /ui/signUp на которой будет форма для регистрации. Данная форма должна выполнять запрос на/api/user.

Написать JSP страницу /ui/signIn на которой будет форма для входа. Данная форма должна выполнять запрос на /api/login.

Написать JSP страницу /ui/user/message где при СЕТ запросе будет отображаться форма для отправки сообщения (логин кому\текст). Отправка данных должна происходить на POST /api/message.

Написать JSP страницу /ui/user/chats на которой будут отображаться сообщения отправленные текущему пользователю.

Дата\время отправки
От кого
Текст
Написать JSP страницу /ui/admin/statistics на которой будет отображаться собранная статистика.

Подключите UserSecurityFilter и AdminSecurityFilter со следующих слайдов

Пишем фильтр

@WebFilter(urlPatterns = {"/ui/user/*", "/api/message"}) public class UserSecurityFilter implements Filter {

@Override public void doFilter(ServletRequest request, ServletResponse response, Filterchain chain) throws IOException, ServletException { HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse res = (HttpServletResponse) response; String contextPath = req.getContextPath(); HttpSession session = req.getSession(); if((session!=null) && (session.getAttribute("user11) !=null)) { chain.doFilter(request, response); } else { //редирект на логин res.sendRedirect(contextPath + "/signIn"); } } }

Пишем ещё фильтр @WebFilter(urlPatterns = {"/ui/admin/”, "/api/admin/”})

public class AdminSecurityFilter implements Filter { @Override public void doFilter(ServletRequest request, ServletResponse response,

FilterСhain chain) throws IOException, ServletException { HttpServletRequest req = (HttpServletRequest) request; HttpServletResponse res = (HttpServletResponse) response; String contextPath = req.getContextPath(); HttpSession session = req.getSession(); if((session!=null) && (session.getAttribute("user") !=null)) { /..напишите проверку, что пользователь является админов chain.doFilter(request, response); } else {

//редирект на логин res.sendRedirect(contextPath + "/");

} } }

Важно использование:

JSP
PreparedStatement
DataSource
Builder, Singleton
Слои
