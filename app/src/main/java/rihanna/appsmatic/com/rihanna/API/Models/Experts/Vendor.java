package rihanna.appsmatic.com.rihanna.API.Models.Experts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/16/2017.
 */
public class Vendor {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("addressid")
    @Expose
    private Integer addressid;
    @SerializedName("certifications")
    @Expose
    private Integer certifications;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("indoor")
    @Expose
    private Boolean indoor;
    @SerializedName("customer_role_name")
    @Expose
    private String customerRoleName;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAddressid() {
        return addressid;
    }

    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    public Integer getCertifications() {
        return certifications;
    }

    public void setCertifications(Integer certifications) {
        this.certifications = certifications;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIndoor() {
        return indoor;
    }

    public void setIndoor(Boolean indoor) {
        this.indoor = indoor;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCustomerRoleName() {
        return customerRoleName;
    }

    public void setCustomerRoleName(String customerRoleName) {
        this.customerRoleName = customerRoleName;
    }
}
