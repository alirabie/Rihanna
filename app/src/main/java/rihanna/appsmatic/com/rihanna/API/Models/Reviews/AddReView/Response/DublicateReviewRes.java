package rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 2/27/2018.
 */

public class DublicateReviewRes {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
