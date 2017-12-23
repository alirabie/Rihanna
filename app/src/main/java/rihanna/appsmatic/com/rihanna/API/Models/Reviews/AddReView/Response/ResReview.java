package rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class ResReview {
    @SerializedName("ratings")
    @Expose
    private List<Rating> ratings = null;

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
