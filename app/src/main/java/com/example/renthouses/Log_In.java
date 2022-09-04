package com.example.renthouses;

public class Log_In {
    long id;
    String email;
    String password;
    public Log_In(){

    }

    public Log_In(long id,String email, String password) {
        this.id=id;
        this.email = email;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
