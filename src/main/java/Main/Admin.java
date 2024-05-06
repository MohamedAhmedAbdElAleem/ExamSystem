package Main;

public class Admin {
    private int Aid;
    private String Aname;
    private String Apassword;

    public Admin(int userId, String username) {
        this.Aid = userId;
        this.Aname = username;
    }

    public Admin() {

    }

    public int getAid() {
        return Aid;
    }

    public void setAid(int aid) {
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
