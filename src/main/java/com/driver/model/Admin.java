package com.driver.model;

import javax.persistence.*;

@Entity
public class Admin{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;
   @Column(unique = true,nullable = false)
   private String username;
   private String password;

    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public Admin() {

    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = password;
    }
}