package com.revature.pens.models;


public class User {
    //todo reenable encryption decryption functions
    private String id;
    private String username;
    private String password;
    private String role;
    private String email;

    public User(String id, String username, String password, String role, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String encryption(String password){
        StringBuilder build = new StringBuilder();
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            //if(c < 65535){} //Probably unnecessary thanks to the regex for the input validation
            c += 10;
            build.append(c);
        }
        return build.toString();
    }

    private String decryption(String password){
        StringBuilder build = new StringBuilder();
        for(int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            //if(c < 65535){} //Probably unnecessary thanks to the regex for the input validation
            c -= 10;
            build.append(c);
        }
        return build.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
