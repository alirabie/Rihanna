package rihanna.appsmatic.com.rihanna.API.Models.Error;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public class Errors {
    @SerializedName("order placement")
    @Expose
    private List<String> orderPlacement = null;
    @SerializedName("Account")
    @Expose
    private String account;
    @SerializedName("rootProperty")
    @Expose
    private List<String> rootProperty = null;
    @SerializedName("shopping cart item")
    @Expose
    private List<String> shoppingCartItem = null;

    public List<String> getRootProperty() {
        return rootProperty;
    }

    public void setRootProperty(List<String> rootProperty) {
        this.rootProperty = rootProperty;
    }

    public List<String> getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(List<String> shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getOrderPlacement() {
        return orderPlacement;
    }

    public void setOrderPlacement(List<String> orderPlacement) {
        this.orderPlacement = orderPlacement;
    }

}
