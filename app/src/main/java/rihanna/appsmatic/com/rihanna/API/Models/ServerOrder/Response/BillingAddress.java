package rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 1/1/2018.
 */
public class BillingAddress {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("state_province_id")
    @Expose
    private Integer stateProvinceId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private Object address2;
    @SerializedName("zip_postal_code")
    @Expose
    private String zipPostalCode;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("fax_number")
    @Expose
    private Object faxNumber;
    @SerializedName("customer_attributes")
    @Expose
    private Object customerAttributes;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("province")
    @Expose
    private Object province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Integer getStateProvinceId() {
        return stateProvinceId;
    }

    public void setStateProvinceId(Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(Object faxNumber) {
        this.faxNumber = faxNumber;
    }

    public Object getCustomerAttributes() {
        return customerAttributes;
    }

    public void setCustomerAttributes(Object customerAttributes) {
        this.customerAttributes = customerAttributes;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public Object getProvince() {
        return province;
    }

    public void setProvince(Object province) {
        this.province = province;
    }
}
