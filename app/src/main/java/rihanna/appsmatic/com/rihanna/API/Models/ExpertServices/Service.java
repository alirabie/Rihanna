package rihanna.appsmatic.com.rihanna.API.Models.ExpertServices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/11/2017.
 */
public class Service {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("expert_id")
    @Expose
    private Integer expertId;
    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("discount_amount")
    @Expose
    private Double discountAmount;
    @SerializedName("discount_percentage")
    @Expose
    private Double discountPercentage;
    @SerializedName("LastUpdate")
    @Expose
    private String lastUpdate;
    @SerializedName("service_name")
    @Expose
    private String serviceName;
    @SerializedName("description")
    @Expose
    private String description;

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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
