package rihanna.appsmatic.com.rihanna.API.Models.Registration;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public class PostNewCustomer {
    @SerializedName("customer")
    @Expose
    private RCustomer customer;

    public RCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(RCustomer customer) {
        this.customer = customer;
    }
}
