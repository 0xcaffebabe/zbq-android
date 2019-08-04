package wang.ismy.zbq.login;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UIModel extends BaseObservable {

   private boolean showUsernameIcon = false;

   private boolean showPasswordIcon = false;

    @Bindable
    public boolean getShowUsernameIcon() {
        return showUsernameIcon;
    }

    public void setShowUsernameIcon(boolean showUsernameIcon) {
        this.showUsernameIcon = showUsernameIcon;
        notifyChange();
    }

    @Bindable
    public boolean getShowPasswordIcon() {
        return showPasswordIcon;
    }

    public void setShowPasswordIcon(boolean showPasswordIcon) {
        this.showPasswordIcon = showPasswordIcon;
        notifyChange();
    }
}
