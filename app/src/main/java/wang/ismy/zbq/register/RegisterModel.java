package wang.ismy.zbq.register;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class RegisterModel extends BaseObservable {

    private String username = "";

    private String password = "";

    private String repeatPassword = "";

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyChange();
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyChange();
    }

    @Bindable
    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
        notifyChange();
    }
}
