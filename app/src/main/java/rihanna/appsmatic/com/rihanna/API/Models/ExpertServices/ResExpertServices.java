package rihanna.appsmatic.com.rihanna.API.Models.ExpertServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/11/2017.
 */
public class ResExpertServices {
    @SerializedName("services")
    @Expose
    private List<Service> services = null;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
