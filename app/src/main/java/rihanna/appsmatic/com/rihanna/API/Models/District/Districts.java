package rihanna.appsmatic.com.rihanna.API.Models.District;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public class Districts {
    @SerializedName("districts")
    @Expose
    private List<DistrictModel> districts = null;

    public List<DistrictModel> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictModel> districts) {
        this.districts = districts;
    }
}
