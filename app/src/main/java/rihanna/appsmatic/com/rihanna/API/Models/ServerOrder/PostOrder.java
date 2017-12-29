package rihanna.appsmatic.com.rihanna.API.Models.ServerOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/29/2017.
 */
public class PostOrder {

    @SerializedName("order")
    @Expose
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
