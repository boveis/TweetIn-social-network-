package event;

public class SignUpFormEvent {
    public String username;
    public String password;
    public String name;
    public String email;
    public String phone;
    public String bio;
    public String birthday;

    public SignUpFormEvent(String username, String password ,String name , String email, String phone, String bio, String birthday) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
        this.birthday = birthday;
    }
}
