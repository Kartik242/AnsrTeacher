package ansore.app.ansrkidsteacher;

public class TeacherData {

    private String email, password;

    public TeacherData() {

    }

    public TeacherData(String email, String password) {
        this.email = email;
        this.password = password;
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
}
