package com.example.bookmytrip;

public class UserProfile {
    public String userName;
    public String userEmail;
    public String userMobile;
    public String firstName = "";
    public String lastName = "";

    public UserProfile(){

    }

    public UserProfile(String name, String email, String mobile, String firstName, String lastName){
        this.userName = name;
        this.userEmail = email;
        this.userMobile = mobile;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserProfile(String name, String email, String mobile) {
        this.userName = name;
        this.userEmail = email;
        this.userMobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
