package rihanna.appsmatic.com.rihanna.API.Models.Experinces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class GetExperinces {
    @SerializedName("experiences")
    @Expose
    private List<Experience> experiences = null;

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
}
