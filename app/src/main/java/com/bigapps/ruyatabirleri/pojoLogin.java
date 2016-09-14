package com.bigapps.ruyatabirleri;

/**
 * Created by shadyfade on 14.09.2016.
 */
public class pojoLogin {
    private String tokenid;
    private String code;
    private pojoRegister user;
    private String createDate;

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public pojoRegister getUser() {
        return user;
    }

    public void setUser(pojoRegister user) {
        this.user = user;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
