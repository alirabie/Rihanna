package rihanna.appsmatic.com.rihanna.API.Models.Certificates;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/17/2017.
 */
public class Certificate {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("expert_id")
    @Expose
    private Integer expertId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("service_category_id")
    @Expose
    private Integer serviceCategoryId;
    @SerializedName("service_category_name")
    @Expose
    private String serviceCategoryName;
    @SerializedName("authorized_by")
    @Expose
    private String authorizedBy;
    @SerializedName("year_acquired")
    @Expose
    private Integer yearAcquired;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServiceCategoryId() {
        return serviceCategoryId;
    }

    public void setServiceCategoryId(Integer serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public Integer getYearAcquired() {
        return yearAcquired;
    }

    public void setYearAcquired(Integer yearAcquired) {
        this.yearAcquired = yearAcquired;
    }

    public String getServiceCategoryName() {
        return serviceCategoryName;
    }

    public void setServiceCategoryName(String serviceCategoryName) {
        this.serviceCategoryName = serviceCategoryName;
    }
}
