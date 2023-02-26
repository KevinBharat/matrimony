package my.matrimony.API;

import my.matrimony.Bean.Bean_Country;
import my.matrimony.Bean.Bean_State;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
public interface ApiInterface {



    @GET("GetCountryList/CountryList")
    Call<Bean_Country> getContryList(@Header("API_KEY") String apiKey);

    @GET("GetStateListByCountryID/{id}/StateList")
    Call<Bean_State> getStateList(@Header("API_KEY") String apiKey, @Path("id") String id);


}
