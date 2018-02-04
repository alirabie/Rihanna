package rihanna.appsmatic.com.rihanna.API.Models.ContactUs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 2/4/2018.
 */
public class MessegeSentRes {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
