package wang.ismy.zbq.model;

import lombok.Data;

@Data
public class Result {
    private boolean success;

    private String msg;

    private String data;
}
