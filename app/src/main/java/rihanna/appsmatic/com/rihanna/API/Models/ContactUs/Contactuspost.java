package rihanna.appsmatic.com.rihanna.API.Models.ContactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 2/4/2018.
 */
public class Contactuspost {

    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("Enquiry")
    @Expose
    private String enquiry;
    @SerializedName("FullName")
    @Expose
    private String fullName;
    @SerializedName("Email")
    @Expose
    private String email;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEnquiry() {
        return enquiry;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
