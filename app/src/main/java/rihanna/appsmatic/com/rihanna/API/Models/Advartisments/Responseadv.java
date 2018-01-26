package rihanna.appsmatic.com.rihanna.API.Models.Advartisments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 1/26/2018.
 */
public class Responseadv {
    @SerializedName("Advertisements")
    @Expose
    private List<Advertisement> advertisements = null;

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
