package listener;

import Log.Logger;
import controller.SignUp_In_Controller;
import event.LoginFormEvent;
import exception.PasswordInvalid;
import exception.UsernameInvalid;

import view.MainStage;
import view.MyConfig;

import java.util.Stack;

public class LoginListener {
    SignUp_In_Controller signUpController = new SignUp_In_Controller();
    public void eventOccur(LoginFormEvent loginFormEvent) throws UsernameInvalid, PasswordInvalid {
        if (!signUpController.checkUsername(loginFormEvent.username)) {
            if (signUpController.getPass(loginFormEvent.username).equals(loginFormEvent.password)){
                signUpController.loginAccount(signUpController.getUser(loginFormEvent.username));
                MainStage.getInstance().setUser(signUpController.getUser(loginFormEvent.username));
                MainStage.getInstance().pages = new Stack<>();
                MainStage.getInstance().setPage((MyConfig.instance.pageAddressProperties.getProperty("PersonalPage")));
                Logger.setUser(signUpController.getUser(loginFormEvent.username));
                Logger.logLogin();
            }else {
                throw  new PasswordInvalid();
            }
        }else{
            throw new UsernameInvalid();
        }
    }
}
