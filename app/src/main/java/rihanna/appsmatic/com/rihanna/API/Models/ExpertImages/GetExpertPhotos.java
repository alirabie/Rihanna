package rihanna.appsmatic.com.rihanna.API.Models.ExpertImages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/20/2017.
 */
public class GetExpertPhotos {
    @SerializedName("customers")
    @Expose
    private List<Customer> customers = null;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
