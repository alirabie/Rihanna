package rihanna.appsmatic.com.rihanna.API.Models.Experts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/16/2017.
 */
public class ExpertsResponse {

    @SerializedName("vendors")
    @Expose
    private List<Vendor> vendors = null;

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }
}
