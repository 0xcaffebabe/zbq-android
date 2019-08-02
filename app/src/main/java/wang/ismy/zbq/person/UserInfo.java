package wang.ismy.zbq.person;

import android.app.Notification;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.JsonAdapter;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserInfo extends BaseObservable {

    private String nickName;

    private String profile;

    private String birthday;

    private Integer penYear;

    private String region;

    private String email;

    private Integer gender;

    private String description;

    @Bindable
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
        notifyChange();
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getPenYear() {
        return penYear;
    }

    public void setPenYear(Integer penYear) {
        this.penYear = penYear;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
