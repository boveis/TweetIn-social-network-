package model.user;

public class Profile {

    public int lastSeenShow;//Everyone0follower1noOne2;
    public String name;
    public String username;
    public String email;
    public String phone;
    public String lastseen;
    public String password;
    public String bio;
    public String birthday;
    public boolean isPublic;
    public boolean isActive;
    public Profile(
                   String fullname,
                   String username,
                   String email,
                   String phone,
                   String password,
                   String bio,
                   String birthday){

        this.name = fullname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.bio = bio;
        this.birthday = birthday;
        this.lastseen="online";
        this.lastSeenShow=0;
        this.isPublic=false;
        this.isActive=true;

    }
}
