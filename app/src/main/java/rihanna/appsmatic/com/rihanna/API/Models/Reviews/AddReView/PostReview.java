package rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class PostReview {
    @SerializedName("rating")
    @Expose
    private Rating rating;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
}
