package com.company.model;

public class UserModel {
    private String userId;
    private String userName;
    private String userEmail;
    private String UserMobileNumber;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserMobileNumber(String userMobileNumber) {
        UserMobileNumber = userMobileNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserMobileNumber() {
        return UserMobileNumber;
    }

    public UserModel(String userId, String userName, String userEmail, String userMobileNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        UserMobileNumber = userMobileNumber;
    }
}
