package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.Random;

/*
3.1 Реализовать класс UserSession, структура данных следующая:
private int sessionHandle;
private String userName;
private <дата-время> lastAccess;

Все свойства приватные,
сделать для них методы-геттеры (getSessionHandle(), getUserName(), getLastAccess()) соответствующих типов,
метод updateLastAccess() -  обновляет время доступа к сессии,

а также конструктор
public UserSession(String userName) - создать сессию пользователя.
Внутри автоматически сгенерировать sessionHanle,
для примера использовать просто случайное число через класс Random,
а так же установить текущее время доступа.

*/
public class UserSession {
    //Переменные класса
    private int sessionHandle;  //
    private String userName;
    private LocalDateTime lastAccess;

    //Конструктор класса
    public UserSession(String userName){
        this.userName = userName;
        this.sessionHandle = new Random().nextInt(100); //случайное число 0-100
        updateLastAccess();
    }


    //Метод для обновления время доступа к сессии,
    public void updateLastAccess(){
        this.lastAccess = LocalDateTime.now();
    }

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionHandle=" + sessionHandle +
                ", userName='" + userName + '\'' +
                ", lastAccess=" + lastAccess +
                '}';
    }

}
