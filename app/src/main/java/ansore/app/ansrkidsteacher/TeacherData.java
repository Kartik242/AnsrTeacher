package ansore.app.ansrkidsteacher;

public class TeacherData {

    private String email, gender;

    public TeacherData() {

    }

    public TeacherData(String email, String gender) {
        this.email = email;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
