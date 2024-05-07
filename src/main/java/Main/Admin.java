package Main;

import java.io.Serializable;

public class Admin implements Serializable {
    private String Aid;
    private String Aname;
    private String Apassword;

    public Admin(String userId, String username) {
        this.Aid = userId;
        this.Aname = username;
    }

    public Admin() {

    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getAname() {
        return Aname;
    }

    public void setAname(String aname) {
        Aname = aname;
    }

    public String getApassword() {
        return Apassword;
    }

    public void setApassword(String apassword) {
        Apassword = apassword;
    }
}
