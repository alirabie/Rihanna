package rihanna.appsmatic.com.rihanna.API.Models.verifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public class VerificationCode {

    @SerializedName("VerificationCode")
    @Expose
    private String verificationCode;

    @SerializedName("NewPassword")
    @Expose
    private String newPassword;

    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;



    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
