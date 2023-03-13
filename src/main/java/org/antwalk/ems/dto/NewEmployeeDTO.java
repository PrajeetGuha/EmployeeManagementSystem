package org.antwalk.ems.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class NewEmployeeDTO {
    
    private String name;
    private String personalEmail;
    private String username;
    private String password;
    public NewEmployeeDTO() {
    }
    public NewEmployeeDTO(String name, String personalEmail, String username, String password) {
        this.name = name;
        this.personalEmail = personalEmail;
        this.username = username;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPersonalEmail() {
        return personalEmail;
    }
    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
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
    
}
