package rihanna.appsmatic.com.rihanna.API.Models.Customers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rihanna.appsmatic.com.rihanna.API.Models.Registration.RCustomer;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public class UpdateCustomer {
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
