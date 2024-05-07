package Main;

import java.util.ArrayList;
import java.util.List;

public class Student implements java.io.Serializable{
    private String Sname;
    private String Sid;
    private String Sssn;
    private String Semail;
    private String SregistrationNumber;
    private String Spassword;


    public Student() {
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSssn() {
        return Sssn;
    }

    public void setSssn(String sssn) {
        Sssn = sssn;
    }

    public String getSemail() {
        return Semail;
    }

    public void setSemail(String semail) {
        Semail = semail;
    }

    public String getSregistrationNumber() {
        return SregistrationNumber;
    }

    public void setSregistrationNumber(String sregistrationNumber) {
        SregistrationNumber = sregistrationNumber;
    }

    public String getSpassword() {
        return Spassword;
    }

    public void setSpassword(String spassword) {
        Spassword = spassword;
    }
}
