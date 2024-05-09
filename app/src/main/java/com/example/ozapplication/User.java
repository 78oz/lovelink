package com.example.ozapplication;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String userId;
    public String name;
    public String phone;
    public String email;
    public String password;
    public ArrayList<Parameter> userParam;
    public ArrayList<Parameter> userPref;

    public User() { } //must to work with firebase

    public User(String userId, String name, String phone, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    //this function returns the mach score in  percent (num Of mach param)
    public double Match(User otherUser)
    {
        double numOfMachParam = 0;
        for (int i = 0; i<this.userPref.size(); i++) {
            Parameter userPrefParam = this.userPref.get(i);
            Parameter otherUserParam = otherUser.userParam.get(i);
            //pref is must
            if (userPrefParam.isMust() && userPrefParam.isBasicMatch(otherUserParam) == false)
                return 0;
            //pref is not must
            if (userPrefParam.isBasicMatch(otherUserParam))
                numOfMachParam++;
        }
        return numOfMachParam/this.userPref.size()*100;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
