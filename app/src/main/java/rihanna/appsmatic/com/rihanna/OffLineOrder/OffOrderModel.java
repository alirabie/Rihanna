package rihanna.appsmatic.com.rihanna.OffLineOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eng Ali on 12/26/2017.
 */
public class OffOrderModel {
    private String expertId;
    private String expertName;
    private String ServiceType;
    private String PaymentMethod;
    private List<OffOrderItem>offOrderItems;
    private offAddress offAddress;

    public String getExpertId() {

        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }

    public List<OffOrderItem> getOffOrderItems() {
        return offOrderItems;
    }

    public void setOffOrderItems(List<OffOrderItem> offOrderItems) {
        this.offOrderItems = offOrderItems;
    }

    public rihanna.appsmatic.com.rihanna.OffLineOrder.offAddress getOffAddress() {
        return offAddress;
    }

    public void setOffAddress(rihanna.appsmatic.com.rihanna.OffLineOrder.offAddress offAddress) {
        this.offAddress = offAddress;
    }

    public void reset(){
        this.expertId="";
        this.expertName="";
        this.ServiceType="";
        this.PaymentMethod="";
        this.offOrderItems=null;
        this.offAddress=null;
    }
}
