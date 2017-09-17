package rihanna.appsmatic.com.rihanna.API.WebServiceTools;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rihanna.appsmatic.com.rihanna.API.Models.Countries.ResCountry;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.States.ResStates;
import rihanna.appsmatic.com.rihanna.API.Models.verifications.VerificationCode;

/**
 * Created by Eng Ali on 9/17/2017.
 */
public interface RihannaAPI {
    //Get Countries Kuwait Saudi
    @GET("api/countries?")
    Call<ResCountry> getCountries(@Query("ids") String id);

    //Get States by id
    @GET("api/states/{id}")
    Call<ResStates> getStates(@Path("id") String id);

    //Get districts
    @GET("api/districts/country/{country}/state/{state}")
    Call<Districts>getDestrics(@Path("country") String country,@Path("state") String state);

    //Login
    @POST("api/customers/login")
    Call<RegResponse> login(@Body Object item);

    //Register new Customer
    @POST("api/customers/")
    Call<RegResponse> regesterNewCustomer(@Body Object item);

    //verify phone number
    @POST("api/customers/PhoneVerification?")
    Call<VerificationCode>verifyMoblieNum(@Query("phoneno") String phone);

    //Retrieve Password
    @POST("api/customers/RetrievePassword/{email}/ ")
    Call<VerificationCode>retrivePassword(@Path("email") String email);

    //Update Customer
    @PUT("api/customers/{id}")
    Call<RegResponse> updateCustomer(@Body Object item,@Path("id")String id);



}
