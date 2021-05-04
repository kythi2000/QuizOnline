/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;

/**
 *
 * @author HP 840 G2
 */
public class RegistrationDTO implements Serializable{
    private String email, password, fullname, role, status;

    public RegistrationDTO(String email, String password, String fullname, String role) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public RegistrationDTO(String email, String fullname, String role) {
        this.email = email;
        this.fullname = fullname;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
