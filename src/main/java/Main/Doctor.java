package Main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Did;
    private String Dname;
    private String Dpassword;
    private String Dssn;

    public String getDid() {
        return Did;
    }

    public void setDid(String did) {
        Did = did;
    }

    public String getDname() {
        return Dname;
    }

    public void setDname(String dname) {
        Dname = dname;
    }

    public String getDpassword() {
        return Dpassword;
    }

    public void setDpassword(String dpassword) {
        Dpassword = dpassword;
    }

    public String getDssn() {
        return Dssn;
    }

    public void setDssn(String dssn) {
        Dssn = dssn;
    }
}