package rihanna.appsmatic.com.rihanna.API.Models.Certificates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class CertificatesList {
    @SerializedName("certificates")
    @Expose
    private List<Certificate> certificates = null;

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
}
