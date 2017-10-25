package pl.jaceklewinski.FunniestVideos.models.forms;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class UserForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String email;

    public UserForm () {}

    private UserForm(String username, String password, String email, Date date) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
