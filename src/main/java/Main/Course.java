package Main;

import java.util.ArrayList;
import java.util.List;

public class Course implements java.io.Serializable{
    private String Cid;
    private String Cname;
    private String CcreditHours;
    private String DocID;

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCcreditHours() {
        return CcreditHours;
    }

    public void setCcreditHours(String ccreditHours) {
        CcreditHours = ccreditHours;
    }

    public String getDocID() {
        return DocID;
    }

    public void setDocID(String docID) {
        DocID = docID;
    }
}
