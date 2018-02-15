package rihanna.appsmatic.com.rihanna.API.WebServiceTools;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rihanna.appsmatic.com.rihanna.API.Models.Advartisments.Responseadv;
import rihanna.appsmatic.com.rihanna.API.Models.Categories.ResCategory;
import rihanna.appsmatic.com.rihanna.API.Models.Certificates.CertificatesList;
import rihanna.appsmatic.com.rihanna.API.Models.ContactUs.MessegeSentRes;
import rihanna.appsmatic.com.rihanna.API.Models.Countries.ResCountry;
import rihanna.appsmatic.com.rihanna.API.Models.Customers.RegResponse;
import rihanna.appsmatic.com.rihanna.API.Models.District.Districts;
import rihanna.appsmatic.com.rihanna.API.Models.Experinces.GetExperinces;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertImages.GetExpertPhotos;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertServices.ResExpertServices;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertTimes.SchdulesResponse;
import rihanna.appsmatic.com.rihanna.API.Models.Experts.ExpertsResponse;
import rihanna.appsmatic.com.rihanna.API.Models.IsBusy.IsBusyRes;
import rihanna.appsmatic.com.rihanna.API.Models.LangResponse.LangRes;
import rihanna.appsmatic.com.rihanna.API.Models.OutdoorLocations.ResAddress;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Response.ResReview;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.GetReviews.GetReviews;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.Order;
import rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response.ResOrderCreation;
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
    @GET("api/categories?fields=name,id,description,image")
    Call<ResCategory> getCategories();

    //Get Expert Services
    @GET("api/expert/services?")
    Call<ResExpertServices> getExpertServices(@Query("ExpertId") String id);

    //Get All Experts
    @GET("api/experts?limit=200")
    Call<ExpertsResponse> getAllExperts();

    //Get Experts by service category or email or state or all
    @GET("api/experts?")
    Call<ExpertsResponse> getExpertsByFilterComp(
            @Query("service_category")String serviceCategory,
            @Query("email")String email,
            @Query("state")String state,
            @Query("rating")String rate,
            @Query("district")String district,
            @Query("keyword")String keyWord,
            @Query("country")String country);


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
    @GET("api/vendors/schedule?&IsClientApp=true")
    Call<SchdulesResponse>getExpertSchadules(@Query("VendorId")String id);

    //Get outdoor address
    @GET("api/expert/addresses?")
    Call<List<ResAddress>>getOutdoorAddress(@Query("ExpertId")String expId);

    //Create order
    @POST("api/orders")
    Call<ResOrderCreation>createOrder(@Body Object obj);


    //Get Customer Orders By Id
    @GET("api/orders?")
    Call<ResOrderCreation>getCustomerOrdersById(@Query("customerid")String expId);


    //Check If Busy
    @POST("api/expert/schedule/isbusy?")
    Call<IsBusyRes>IsBuSYtime(@Query("expertid")String expId,
                              @Query("servicedate")String date,
                              @Query("timefrom")String timeFrom,
                              @Query("timeto")String timeTo);



    //adv
    @GET("api/ads")
    Call<Responseadv>getAds();

    //Change Language on server
    @POST("api/languages?")
    Call<LangRes>changeLang(@Query("languageid")String langId,@Query("customerid")String customerId);


    //send message
    @POST("api/contactus")
    Call<MessegeSentRes>contactUs(@Body Object obj);



}
