package pl.jaceklewinski.FunniestVideos.models.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

public class UserForm {

    @NotEmpty(message = "{NotEmpty.registration.username}")
    @Size(min = 4, message = "{Size.registration.username}")
    private String username;

    @NotEmpty(message = "{NotEmpty.registration.password}")
    @Size(min = 4, message = "{Size.registration.password}")
    private String password;

    @NotEmpty(message = "{NotEmpty.registration.password}")
    private String email;

    private String repassword;
    private String reemail;

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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getReemail() {
        return reemail;
    }

    public void setReemail(String reemail) {
        this.reemail = reemail;
    }
}
