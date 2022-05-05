package Cyber_Community.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Enzo Cotter on 02/05/2022.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CodeMsg {
    private int code;
    private String msg;

    //Common error
    public static CodeMsg SUCCESS = new CodeMsg(200, "Success");
    public static CodeMsg USER_NOT_LOGIN = new CodeMsg(500200, "user not logged");
    public static CodeMsg TOKEN_INVALID = new CodeMsg(500201, "token invalid");
    public static CodeMsg TOKEN_EXPIRED = new CodeMsg(500201, "token expired");
    public static CodeMsg USERNAME_NOT_EXIST = new CodeMsg(500202, "username not exist");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500203, "password error");
    public static CodeMsg INVALID_CREDENTIAL = new CodeMsg(500204, "invalid credential");


    /*public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }*/

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
