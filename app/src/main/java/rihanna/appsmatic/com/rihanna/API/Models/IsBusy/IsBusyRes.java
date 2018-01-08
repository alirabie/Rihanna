package rihanna.appsmatic.com.rihanna.API.Models.IsBusy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 1/8/2018.
 */
public class IsBusyRes {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
