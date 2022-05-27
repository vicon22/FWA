package edu.school21.cinema.models;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Session {

    private int id;
    private String email;
    private String ip;
    private String date;
    private String time;

    public Session(String email, String ip) {
        this.id = IdGenerator.getInstance().getValue();
        this.email = email;
        if (ip.equals("0:0:0:0:0:0:0:1"))
        {
            ip = "127.0.0.1";
        }
        this.ip = ip;
        this.date = LocalDateTime.now().toLocalDate().toString();
        this.time = LocalTime.now().toString();
    }

    public Session(int id, String email, String ip, String date, String time) {
        this.id = id;
        this.email = email;
        if (ip.equals("0:0:0:0:0:0:0:1"))
        {
            ip = "127.0.0.1";
        }
        this.ip = ip;
        this.date = date;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", ip='" + ip + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

class IdGenerator{
    private static IdGenerator instance;
    private static int value;

    private IdGenerator() {
        value = 0;
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        value++;
        return instance;
    }

    public int getValue() {
        return value;
    }
}
