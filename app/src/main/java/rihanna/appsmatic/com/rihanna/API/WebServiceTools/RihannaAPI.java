package rihanna.appsmatic.com.rihanna.API.WebServiceTools;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rihanna.appsmatic.com.rihanna.API.Models.Categories.ResCategory;
import rihanna.appsmatic.com.rihanna.API.Models.Certificates.CertificatesList;
import rihanna.appsmatic.com.rihanna.API.Models.Countries.ResCountry;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.Experinces.GetExperinces;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertImages.GetExpertPhotos;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.ResExpertServices;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertTimes.SchdulesResponse;
import rihanna.appsmatic.com.rihanna.API.Models.Experts.ExpertsResponse;
import rihanna.appsmatic.com.rihanna.API.Models.OutdoorLocations.ResAddress;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Response.ResReview;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.GetReviews.GetReviews;
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


    //Get Categories
    @GET("api/categories?fields=name,id")
    Call<ResCategory> getCategories();

    //Get Expert Services
    @GET("api/expert/services?")
    Call<ResExpertServices> getExpertServices(@Query("ExpertId") String id);

    //Get All Experts
    @GET("api/experts")
    Call<ExpertsResponse> getAllExperts();

    //Get Experts by service category or email or state or all
    @GET("api/experts?")
    Call<ExpertsResponse> getExpertsByFilterComp(
            @Query("service_category")String serviceCategory,
            @Query("email")String email,
            @Query("state")String state,
            @Query("rating")String rate);

    //Get Experiences
    @GET("api/expert/expertise/{id}")
    Call<GetExperinces> getExperinces(@Path("id") String id);

    //Get Certificates
    @GET("api/expert/certificates/{id}")
    Call<CertificatesList> getExpertCertificates(@Path("id") String id);

    //Get Expert Photos
    @GET("api/expert/images?")
    Call<GetExpertPhotos>getExpertPhotos(@Query("ExpertId") String expId);

    //Add Expert Review
    @POST("api/expert/rating")
    Call<ResReview>AddReview(@Body Object object);

    //Get Expert Reviews
    @GET("api/expert/rating/{id}")
    Call<GetReviews>getReviews(@Path("id")String expId);


    //Get Expert Schedules
    @GET("api/vendors/schedule/{id}")
    Call<SchdulesResponse>getExpertSchadules(@Path("id")String id);

    @GET("api/expert/addresses?")
    Call<List<ResAddress>>getOutdoorAddress(@Query("ExpertId")String expId);

}
