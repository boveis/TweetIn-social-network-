package listener;

import Log.Logger;
import controller.SignUp_In_Controller;
import event.SignUpFormEvent;
import exception.EmailInvalid;
import exception.PhoneInvalid;
import exception.UsernameInvalid;
import model.user.User;
import view.MainStage;
import view.MyConfig;

import java.util.Stack;

public class SignUpListener {

    SignUp_In_Controller signUpController = new SignUp_In_Controller();
    GetIdListener getIdListener = new GetIdListener();
    public SignUpListener(){
    }
    public void eventOccur(SignUpFormEvent signUpFormEvent) throws UsernameInvalid, EmailInvalid, PhoneInvalid {
       if (!signUpController.checkUsername(signUpFormEvent.username)){

           throw new UsernameInvalid();
       }
       else if(!signUpController.checkEmail(signUpFormEvent.email)){
           throw new EmailInvalid();


       }
       else if (!signUpFormEvent.phone.equals("")){
           if (!signUpController.checkPhone(signUpFormEvent.phone)){

               throw new PhoneInvalid();
           }
       }


        User user = new User(signUpFormEvent.username
                , signUpFormEvent.password
                , signUpFormEvent.name
                , signUpFormEvent.birthday
                , signUpFormEvent.email
                , signUpFormEvent.phone
                , signUpFormEvent.bio ,
                getIdListener.getUserId());
        signUpController.addUser(user);
        signUpController.loginAccount(user);
        Logger.setUser(user);
        Logger.logSignUp();
        MainStage.getInstance().pages = new Stack<>();
        MainStage.getInstance().setUser(user);
        MainStage.getInstance().setPage((MyConfig.instance.pageAddressProperties.getProperty("PersonalPage")));

    }



}
