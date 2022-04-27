package app.setting.listener;

import Log.Logger;
import app.setting.controller.SettingController;
import app.setting.event.StringEvent;
import exception.EmailInvalid;
import exception.PhoneInvalid;
import exception.UsernameInvalid;
import view.MainStage;
import view.MyConfig;

import java.util.Stack;

public class ChangeProfileListener {
    SettingController settingController=  new SettingController();
    public void eventOccur(StringEvent stringEvent) throws UsernameInvalid, EmailInvalid, PhoneInvalid {
        if(stringEvent.field.equals("password") ||
                stringEvent.field.equals("bio") ||

                stringEvent.field.equals("name") ||
                stringEvent.field.equals("birthday") ||
                stringEvent.field.equals("privacy") ||
                stringEvent.field.equals("lastseen")) {
            settingController.updateUser(MainStage.getInstance().user);

            return;
        }
        if(stringEvent.field.equals("username")){

            if(settingController.checkUsername(stringEvent.s)){
                MainStage.getInstance().user.profile.username=stringEvent.s;
                settingController.updateUser(MainStage.getInstance().user);
                Logger.logChangeUsername( MainStage.getInstance().user.profile.username);
                return;
            }
            else{
                throw new UsernameInvalid();
            }
        }
        if(stringEvent.field.equals("email")){
            if(settingController.checkEmail(stringEvent.s)){
                MainStage.getInstance().user.profile.email=stringEvent.s;
                Logger.logChangeEmail( MainStage.getInstance().user.profile.email);
                settingController.updateUser(MainStage.getInstance().user);
                return;
            }
            else{
                throw new EmailInvalid();
            }
        }
        if(stringEvent.field.equals("phone")){
            if (stringEvent.s.equals("")){
                MainStage.getInstance().user.profile.phone=stringEvent.s;
                settingController.updateUser(MainStage.getInstance().user);
                return;
            }
            if(settingController.checkPhone(stringEvent.s)){
                MainStage.getInstance().user.profile.phone=stringEvent.s;
                Logger.logChangePhone( MainStage.getInstance().user.profile.phone);
                settingController.updateUser(MainStage.getInstance().user);
                return;
            }
            else{
                throw new PhoneInvalid();
            }
        }
        if(stringEvent.field.equals("log out")){
            settingController.logOut(MainStage.getInstance().user);
            MainStage.getInstance().user= null;
            MainStage.getInstance().pages = new Stack<>();
            Logger.logLogout();
            MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("LoginPage"));
            return;
        }
        if (stringEvent.field.equals("deactiva")){
            MainStage.getInstance().user.profile.isActive=false;
            settingController.logOut(MainStage.getInstance().user);
            MainStage.getInstance().user= null;
            MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("LoginPage"));
            return;
        }
        if (stringEvent.field.equals("delete")){
            settingController.deleteAccount(MainStage.getInstance().user);
            MainStage.getInstance().user= null;
            MainStage.getInstance().setPage(MyConfig.instance.pageAddressProperties.getProperty("LoginPage"));
            return;
        }

    }
}
