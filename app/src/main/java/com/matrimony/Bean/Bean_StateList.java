package com.matrimony.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 5/4/2018.
 */

public class Bean_StateList {
    @SerializedName("StateID")
    @Expose
    private Integer stateID;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("CountryName")
    @Expose
    private String countryName;

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
