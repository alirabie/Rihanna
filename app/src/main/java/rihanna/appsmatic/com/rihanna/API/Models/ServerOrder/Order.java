package rihanna.appsmatic.com.rihanna.API.Models.ServerOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 12/29/2017.
 */
public class Order {
    @SerializedName("billing_address")
    @Expose
    private BillingAddress billingAddress;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("expert_id")
    @Expose
    private Integer expertId;
    @SerializedName("service_type")
    @Expose
    private String serviceType;
    @SerializedName("order_items")
    @Expose
    private List<OrderItem> orderItems = null;
    @SerializedName("payment_method_system_name")
    @Expose
    private String paymentMethodSystemName;

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPaymentMethodSystemName() {
        return paymentMethodSystemName;
    }

    public void setPaymentMethodSystemName(String paymentMethodSystemName) {
        this.paymentMethodSystemName = paymentMethodSystemName;
    }

}
