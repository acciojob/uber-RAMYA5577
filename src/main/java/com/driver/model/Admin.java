package com.driver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Admin{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   int adminId;
    String name;
    String passWord;

    public Admin(int adminId, String name, String passWord) {
        this.adminId = adminId;
        this.name = name;
        this.passWord = passWord;
    }

    public Admin() {
        this.adminId = adminId;
        this.name = name;
        this.passWord = passWord;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}