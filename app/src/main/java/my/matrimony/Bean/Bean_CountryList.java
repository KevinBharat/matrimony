    package my.matrimony.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Bean_CountryList {
    @SerializedName("CountryID")
    @Expose
    private Integer countryID;
    @SerializedName("CountryName")
    @Expose
    private String countryName;

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
