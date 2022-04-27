package event;

import view.LoginPageController;

import java.util.EventObject;

public  class LoginFormEvent extends EventObject {
    public String username;
    public String password;

    public LoginFormEvent(Object source , String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
    }

}
