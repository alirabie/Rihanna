package rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class Rating {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("expert_id")
    @Expose
    private Integer expertId;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("review_title")
    @Expose
    private Object reviewTitle;
    @SerializedName("review_text")
    @Expose
    private String reviewText;
    @SerializedName("reply_text")
    @Expose
    private Object replyText;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("review_was_helpful")
    @Expose
    private Integer reviewWasHelpful;
    @SerializedName("review_was_not_helpful")
    @Expose
    private Integer reviewWasNotHelpful;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Object getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(Object reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Object getReplyText() {
        return replyText;
    }

    public void setReplyText(Object replyText) {
        this.replyText = replyText;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getReviewWasHelpful() {
        return reviewWasHelpful;
    }

    public void setReviewWasHelpful(Integer reviewWasHelpful) {
        this.reviewWasHelpful = reviewWasHelpful;
    }

    public Integer getReviewWasNotHelpful() {
        return reviewWasNotHelpful;
    }

    public void setReviewWasNotHelpful(Integer reviewWasNotHelpful) {
        this.reviewWasNotHelpful = reviewWasNotHelpful;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }
}
