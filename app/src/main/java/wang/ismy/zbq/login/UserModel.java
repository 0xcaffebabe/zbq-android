package wang.ismy.zbq.login;

import java.util.Observable;

import lombok.Data;

@Data
public class UserModel extends Observable {

    private String username;

    private String password;


}
