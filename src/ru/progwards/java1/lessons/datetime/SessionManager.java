package ru.progwards.java1.lessons.datetime;
/*
* Реализовать класс для хранения пользовательских сессий для сервера,
* который проверяет аутентификацию пользователей.
* Менеджер работает по следующему принципу:
* при логине (считаем что проверка логин-пароль уже прошла)
* данные о сессии пользователя заносятся в список и возвращается хэндл сессии.
* Затем пользователи запрашивают информацию используя хэндл,
* авторизация идет по хендлу сессии, который валиден определенное время,
* с момента крайнего запроса. Проверка сессии по хендлу должна работать максимально быстро.
* У каждого пользователя может быть только одна сессия.

3.1 Реализовать класс UserSession, структура данных следующая:
3.2 Реализовать класс SessionManager, структура данных следующая:
private <коллекция> sessions;
private int sessionValid;
3.3 реализовать конструктор
public SessionManager(int sessionValid) -
* создает экземпляр SessionManager и сохраняет sessionValid -
* период валидности сессии в секундах.

Протестировать следующим образом:

Создать сессию по userName, для этого
- сделать find,
- убедиться что вернется null
- создать новую сессию
- добавить используя add

- Вызвать несколько раз get
- Подождать (Thread.sleep) время, большее, чем время валидности
- Проверить что сессии нет через метод get
- Создать еще одну сессию
- Подождать половину времени валидности сессии
- Создать еще одну сессию
- Подождать еще раз половину времени валидности сессии
- Вызвать deleteExpired()
- Убедиться, что одна сессия удалена, вторая нет
- Удалить оставшуюся через метод delete
- Убедиться что удаление прошло
* */
//Были проблемы с ReviewBoard. Комментарий для изменения и перезаписи
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SessionManager {
    public static void main(String[] args) throws InterruptedException {
        String userName_1 = "Minkin";
        String userName_2 = "Ivanov";
        String userName_3 = "Petrov";
        int testSessionValid = 5;           //секунд
        int testHandle_1 = 0;           //
        int testHandle_2 = 0;           //
        int testHandle_3 = 0;           //
        SessionManager sessionManager = new SessionManager(testSessionValid);
/*

        if (sessionManager.find(userName_1) == null){                     //Проверяю наличие сессии
            UserSession userSession = new UserSession(userName_1);        //Создаю новую сессию
            sessionManager.add(userSession);                            //Добавляю сессию
            testHandle_1 = userSession.getSessionHandle();              //Запоминаю Handle
            System.out.println(sessionManager.sessions.get(testHandle_1));
        }else {
            System.out.println("problem with " + userName_1);
        }
        if (sessionManager.find(userName_2) == null){                     //Проверяю наличие сессии
            UserSession userSession = new UserSession(userName_2);        //Создаю новую сессию
            sessionManager.add(userSession);                            //Добавляю сессию
            testHandle_2 = userSession.getSessionHandle();              //Запоминаю Handle
            System.out.println(sessionManager.sessions.get(testHandle_2));
        }else {
            System.out.println("problem with " + userName_2);
        }
        if (sessionManager.find(userName_3) == null){                     //Проверяю наличие сессии
            UserSession userSession = new UserSession(userName_3);        //Создаю новую сессию
            sessionManager.add(userSession);                            //Добавляю сессию
            testHandle_3 = userSession.getSessionHandle();              //Запоминаю Handle
            System.out.println(sessionManager.sessions.get(testHandle_3));
        }else {
            System.out.println("problem with " + userName_3);
        }
*/
         class NewSession{
            public void getNewSession(String userName){
                if (sessionManager.find(userName) == null){                     //Проверяю наличие сессии
                    UserSession userSession = new UserSession(userName);        //Создаю новую сессию
                    sessionManager.add(userSession);                            //Добавляю сессию
//                    test_handle = userSession.getSessionHandle();              //Запоминаю Handle
//                    System.out.println(sessionManager.sessions.get(test_handle));
                }else {
                    System.out.println("problem with " + userName_1);
                }
            }
        }

        NewSession newSession = new NewSession();
        newSession.getNewSession(userName_1);     //Создаю новую сессию
//        newSession.getNewSession(userName_2, testHandle_2);     //Создаю новую сессию
//        newSession.getNewSession(userName_3, testHandle_3);     //Создаю новую сессию

        System.out.println(sessionManager.find(userName_1));    //Тест метода find на наличие в коллекции сессии
            Thread.sleep(4000);
        //System.out.println(sessionManager.find(userName_1));    //Тест метода find на наличие в коллекции сессии
            testHandle_1 = sessionManager.find(userName_1).getSessionHandle();
        System.out.println("Жду 4 сек :" + sessionManager.get(testHandle_1));
            Thread.sleep(6000);
        System.out.println("Жду 6 сек :" + sessionManager.get(testHandle_1));
            newSession.getNewSession(userName_1);     //Создаю новую сессию
        testHandle_2 = sessionManager.find(userName_1).getSessionHandle();
        System.out.println("Новая сессия :" + sessionManager.get(testHandle_2));
        System.out.println("----------");
        System.out.println(sessionManager.sessions.values());
            sessionManager.deleteExpired();
        System.out.println(sessionManager.sessions.values());
            sessionManager.delete(testHandle_2);
        System.out.println(sessionManager.sessions.values());

    }

    //переменные класса
    private Map<Integer, UserSession> sessions = new Hashtable<>();     //Использую Hashtable, как более быстрый для поиска (согласно лекции)
    private int sessionValid;

    //Конструктор класса.
    //создает экземпляр SessionManager и сохраняет sessionValid - период валидности сессии в секундах.
    public SessionManager(int sessionValid){
        this.sessionValid = sessionValid;
    }

    //3.4 - добавляет новую сессию пользователя
    public void add(UserSession userSession){
        sessions.put(userSession.getSessionHandle(), userSession);
    }

    // 3.5 - проверяет наличие существующей сессии по userName.
    // Если срок валидности истек, или такой  сессии нет, возвращает null.
    // В противном случае возвращает сессию, обновив ее дату доступа.
    public UserSession find(String userName){
        for(UserSession userSession:sessions.values()){     //перебираю Map на предмет совпадения Имени и валидности
            if(userName.equals(userSession.getUserName()) && checkValid(userSession)){
                userSession.updateLastAccess();
                return userSession;
            }
        }
        return null;
    }

    //Метод для проверки валидности сессии
    public boolean checkValid(UserSession userSession){
        return LocalDateTime.now().isBefore(userSession.getLastAccess().plusSeconds((long) sessionValid)) ;
    }

    //3.6  - проверяет наличие существующей сессии по хендлу.
    // Если срок валидности истек, или такой  сессии нет, возвращает null.
    // В противном случае возвращает сессию, обновив ее дату доступа.
    public UserSession get(int sessionHandle){
        if(sessions.containsKey(sessionHandle) && checkValid(sessions.get(sessionHandle))){  //Проверка наличия сессии и валидности!
            sessions.get(sessionHandle).updateLastAccess();
            return sessions.get(sessionHandle);
        }
        return null;
    }

    //3.7  - удаляет указанную сессию пользователя
    public void delete(int sessionHandle){
        sessions.remove(sessionHandle);
    }


    //3.8  - удаляет все сессии с истекшим сроком годности.
    public void deleteExpired(){
        List<UserSession> list = new ArrayList<>();         //Вспомогательный список для удаления просроченных сессий
        for (UserSession userSession: sessions.values()){
            if(!checkValid(userSession)){
                list.add(userSession);
//                sessions.values().remove(userSession);    //при попытке удаления во время перебора бросается exception
            }
        }
        for (UserSession userSession:list){
            sessions.remove(userSession.getSessionHandle()); //Удаление сессий по получившемуся списку
        }
    }

}
