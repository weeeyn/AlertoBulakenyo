package com.activity.alertobulakenyo.ObjectClasses;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class AdminHolder implements Serializable {

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    private String id;

    private String adminCity;
    private String adminDept;
    private String adminDeptAbv;
    private String adminEmail;
    private String adminName;

    public AdminHolder() {
    }

    public AdminHolder(String adminCity, String adminDept, String adminDeptAbv, String adminEmail, String adminName) {
        this.adminCity = adminCity;
        this.adminDept = adminDept;
        this.adminDeptAbv = adminDeptAbv;
        this.adminEmail = adminEmail;
        this.adminName = adminName;
    }

    public String getAdminCity() {
        return adminCity;
    }

    public void setAdminCity(String adminCity) {
        this.adminCity = adminCity;
    }

    public String getAdminDept() {
        return adminDept;
    }

    public void setAdminDept(String adminDept) {
        this.adminDept = adminDept;
    }

    public String getAdminDeptAbv() {
        return adminDeptAbv;
    }

    public void setAdminDeptAbv(String adminDeptAbv) {
        this.adminDeptAbv = adminDeptAbv;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

}
