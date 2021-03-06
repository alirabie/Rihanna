package rihanna.appsmatic.com.rihanna.API.Models.States;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public class ResStates {
    @SerializedName("states")
    @Expose
    private List<State> states = null;

    public List<State> getStates() {
        return states;
    }
    public void setStates(List<State> states) {
        this.states = states;
    }
}
