package rihanna.appsmatic.com.rihanna.API.Models.Experinces;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class Experience {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("years_of_experience")
    @Expose
    private Integer yearsOfExperience;
    @SerializedName("about_expert")
    @Expose
    private String aboutExpert;
    @SerializedName("expert_id")
    @Expose
    private Integer expertId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getAboutExpert() {
        return aboutExpert;
    }

    public void setAboutExpert(String aboutExpert) {
        this.aboutExpert = aboutExpert;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }
}
