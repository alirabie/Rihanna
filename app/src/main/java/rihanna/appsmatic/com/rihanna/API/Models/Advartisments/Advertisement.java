package rihanna.appsmatic.com.rihanna.API.Models.Advartisments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 1/26/2018.
 */
public class Advertisement {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("picture_id")
    @Expose
    private Integer pictureId;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("ad_place")
    @Expose
    private String adPlace;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAdPlace() {
        return adPlace;
    }

    public void setAdPlace(String adPlace) {
        this.adPlace = adPlace;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
