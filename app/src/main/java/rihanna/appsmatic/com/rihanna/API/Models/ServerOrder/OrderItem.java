package rihanna.appsmatic.com.rihanna.API.Models.ServerOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/29/2017.
 */
public class OrderItem {

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("service_date")
    @Expose
    private String serviceDate;
    @SerializedName("service_time_from")
    @Expose
    private String serviceTimeFrom;
    @SerializedName("service_time_to")
    @Expose
    private String serviceTimeTo;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceTimeFrom() {
        return serviceTimeFrom;
    }

    public void setServiceTimeFrom(String serviceTimeFrom) {
        this.serviceTimeFrom = serviceTimeFrom;
    }

    public String getServiceTimeTo() {
        return serviceTimeTo;
    }

    public void setServiceTimeTo(String serviceTimeTo) {
        this.serviceTimeTo = serviceTimeTo;
    }
}
