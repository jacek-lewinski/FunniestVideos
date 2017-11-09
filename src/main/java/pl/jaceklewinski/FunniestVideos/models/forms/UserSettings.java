package pl.jaceklewinski.FunniestVideos.models.forms;

public class UserSettings {
    private String password;
    private String newpassword;

    public UserSettings () {}

    public UserSettings(String password, String newpassword) {
        this.password = password;
        this.newpassword = newpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
