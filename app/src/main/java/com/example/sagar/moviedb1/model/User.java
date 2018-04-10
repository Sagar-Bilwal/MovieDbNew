package com.example.sagar.moviedb1.model;

/**
 * Created by SAGAR on 02-04-2018.
 */

public class User
{
    private String Password;
    private String Name;

    User()
    {

    }
    public User(String Password,String Name)
    {
        this.Password = Password;
        this.Name = Name;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}