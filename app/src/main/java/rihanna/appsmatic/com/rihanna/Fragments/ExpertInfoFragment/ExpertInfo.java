package rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.ExpertImages.GetExpertPhotos;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.AddReView.Rating;
import rihanna.appsmatic.com.rihanna.API.Models.Reviews.GetReviews.GetReviews;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.Activities.CustomerLocation;
import rihanna.appsmatic.com.rihanna.Activities.DateTimeScreen;
import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Activities.OrderScreen;
import rihanna.appsmatic.com.rihanna.Adabtors.CommentsAdb;
import rihanna.appsmatic.com.rihanna.Adabtors.CustomFragmentPagerAdapter;
import rihanna.appsmatic.com.rihanna.Dilaogs.FireDialog;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments.AboutExpertFrag;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments.LatestOffersFrag;
import rihanna.appsmatic.com.rihanna.Fragments.ExpertInfoFragment.ExpertInfo3Fragments.ServicesFrag;
import rihanna.appsmatic.com.rihanna.Fragments.OrderInfo;
import rihanna.appsmatic.com.rihanna.Fragments.RatingandComments;
import rihanna.appsmatic.com.rihanna.Fragments.Services;
import rihanna.appsmatic.com.rihanna.OffLineOrder.offAddress;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;


public class ExpertInfo extends Fragment {


    LatestOffersFrag latestOffersFrag;
    AboutExpertFrag aboutExpertFrag;
    ServicesFrag servicesFrag;
    ViewPager p;
    CustomFragmentPagerAdapter adapter;
    PagerSlidingTabStrip tabsStrip;
    public static String expertId ="";
    public static String expertAddress="";
    public static String expertcert="";
    public static String expertClass="";
    public static boolean expertserviceIndoor =false;
    private ImageView goToRatesComments;
    private TextView name;
    private TextView bookingBtn;
    private ImageView backbtn;
    private BannerSlider bannerSlider;
    private List<Banner> banners;
    private RatingBar ratingBar;
    private TextView reviewsNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_expert_info, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Receive Expert Id from adaptor
        expertId=getArguments().get("expertId").toString();
        expertAddress=getArguments().getString("expaddrss");
        expertcert=getArguments().getString("expcert");
        expertserviceIndoor=getArguments().getBoolean("expisindoorserv");
        expertClass=getArguments().getString("expertclass");


        goToRatesComments =(ImageView)view.findViewById(R.id.expert_details_goto_customercomments);
        name=(TextView)view.findViewById(R.id.expert_details_name_tv);
        bookingBtn=(TextView)view.findViewById(R.id.booking_btn);
        backbtn=(ImageView)view.findViewById(R.id.expert_details_back);
        ratingBar=(RatingBar)view.findViewById(R.id.expert_details_ratingBar);
        bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);
        reviewsNum=(TextView)view.findViewById(R.id.expert_details_customerrates);
        bannerSlider.setVisibility(View.INVISIBLE);



        //Setup Photos Slider if images On from settings
        if(SaveSharedPreference.getImgLoadingSatatus(getContext())) {
            Generator.createService(RihannaAPI.class).getExpertPhotos(getArguments().get("expertId").toString()).enqueue(new Callback<GetExpertPhotos>() {
                @Override
                public void onResponse(Call<GetExpertPhotos> call, Response<GetExpertPhotos> response) {
                    if (response.isSuccessful()) {

                        if (response.body().getCustomers() != null) {
                            if (response.body().getCustomers().get(0).getImages().isEmpty()) {
                                bannerSlider.setVisibility(View.INVISIBLE);
                            } else {
                                banners = new ArrayList<>();
                                bannerSlider.setVisibility(View.VISIBLE);
                                for (int i = 0; i < response.body().getCustomers().get(0).getImages().size(); i++) {
                                    banners.add(new RemoteBanner(response.body().getCustomers().get(0).getImages().get(i).getSrc()));
                                }
                                bannerSlider.setBanners(banners);
                                bannerSlider.setLoopSlides(true);
                                bannerSlider.setInterval(3000);
                            }

                        } else {
                            Toast.makeText(getContext(), "Null From Get Expert Photos Api", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        try {
                            Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetExpertPhotos> call, Throwable t) {

                    Toast.makeText(getContext(), "Connection Error From Get Expert Photos Api " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        //add expert Info to offline order
        Home.offOrderModel.setExpertId(expertId);
        Home.offOrderModel.setExpertName(getArguments().getString("name"));


        servicesFrag=new ServicesFrag();
        aboutExpertFrag=new AboutExpertFrag();
        latestOffersFrag=new LatestOffersFrag();
        //Tab Layout With 3 fragments
        p=(ViewPager)view.findViewById(R.id.expert_info_viewpager_master);
        tabsStrip = (PagerSlidingTabStrip)view.findViewById(R.id.expert_tabs_master);
        adapter = new CustomFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(latestOffersFrag,getResources().getString(R.string.tablatestoffers));
        adapter.addFragment(servicesFrag, getResources().getString(R.string.tabexpertservices));
        adapter.addFragment(aboutExpertFrag, getResources().getString(R.string.tababoutexpert));
        p.setAdapter(adapter);
        tabsStrip.setViewPager(p);
        adapter.notifyDataSetChanged();

        name.setText(getArguments().getString("name"));
        ratingBar.setRating(getArguments().getInt("rate"));
        goToRatesComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                goToRatesComments.clearAnimation();
                goToRatesComments.setAnimation(anim);
                FireDialog.CommentsDialog(getContext(), goToRatesComments, expertId,getArguments().getInt("rate"),name.getText().toString());
                //FireDialog.experrReviewDailog(getContext(),goToRatesComments,expertId,SaveSharedPreference.getCustomerId(getContext()),name.getText().toString());
            }
        });


        //back Button
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                backbtn.clearAnimation();
                backbtn.setAnimation(anim);
                Services services = new Services();
                Bundle bundle = new Bundle();
                bundle.putString("sourceflag","filter");
                bundle.putString("category",Home.selectedCategory);
                bundle.putString("state","");
                bundle.putString("email","");
                bundle.putString("rate", "");
                bundle.putString("district", "");
                bundle.putString("keyword","");
                bundle.putString("country","");
                services.setArguments(bundle);
                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentcontener, services);
                fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                fragmentTransaction.commit();
            }
        });



        //Get review counts
        Generator.createService(RihannaAPI.class).getReviews(expertId).enqueue(new Callback<GetReviews>() {
            @Override
            public void onResponse(Call<GetReviews> call, Response<GetReviews> response) {
                if (response.isSuccessful()) {

                    if (response.body().getRatings() != null) {
                        reviewsNum.setText(getResources().getString(R.string.rviewsnum) + " " + response.body().getRatings().size());

                    } else {
                        Toast.makeText(getContext(), "Null from get reviews API", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Toast.makeText(getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetReviews> call, Throwable t) {

                Toast.makeText(getContext(), "Connection error from get reviews API " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





        //booking btn
        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                bookingBtn.clearAnimation();
                bookingBtn.setAnimation(anim);
                if (!Home.orderItems.isEmpty()) {
                    if (expertserviceIndoor) {
                        //Initialize Done Dialog
                        final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(getContext());
                        dialogBuildercard
                                .withDuration(700)//def
                                .withEffect(Effectstype.Fall)
                                .withDialogColor(getResources().getColor(R.color.colorPrimary))
                                .withTitleColor(Color.BLACK)
                                .withMessage(getResources().getString(R.string.servicetypeask))
                                .withTitle(getResources().getString(R.string.app_name))
                                .isCancelableOnTouchOutside(false)
                                .withButton1Text(getResources().getString(R.string.indoor))
                                .withButton2Text(getResources().getString(R.string.outdoor))
                                .setButton1Click(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //Set service type to offline order indoor
                                        Home.offOrderModel.setServiceType("in");
                                        //set address to offline order
                                        offAddress offAddress=new offAddress();
                                        offAddress.setStateId(Integer.parseInt(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getStateProvinceId().toString()));
                                        offAddress.setDistrictId(Integer.parseInt(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getStateProvinceId().toString()));
                                        offAddress.setStateName(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getProvince().toString() + "");
                                        offAddress.setDistrictName(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getCity().toString() + "");
                                        offAddress.setAddr(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getAddress1().toString() + "");
                                        offAddress.setPhoneNum(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getPhoneNumber().toString()+"");
                                        offAddress.setLat(0.0);
                                        offAddress.setLng(0.0);
                                        Home.offOrderModel.setOffAddress(offAddress);
                                        startActivity(new Intent(getContext(), OrderScreen.class));
                                        dialogBuildercard.dismiss();
                                    }
                                })
                                .setButton2Click(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //Set service type to offline order outdoor
                                        Home.offOrderModel.setServiceType("out");
                                        startActivity(new Intent(getContext(), CustomerLocation.class).putExtra("expId", expertId));
                                        dialogBuildercard.dismiss();
                                    }
                                }).show();
                    } else {

                        Home.offOrderModel.setServiceType("out");
                        startActivity(new Intent(getContext(), CustomerLocation.class).putExtra("expId", expertId));
                    }

                }else {
                    Toast.makeText(getContext(),getResources().getString(R.string.picfirst),Toast.LENGTH_SHORT).show();
                }
            }
        });





    }


    @Override
    public void onDetach() {
        super.onDetach();
        getFragmentManager().beginTransaction().remove(this).commit();
        getFragmentManager().popBackStack();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener
                    Services services = new Services();
                    Bundle bundle = new Bundle();
                    bundle.putString("sourceflag","filter");
                    bundle.putString("category",Home.selectedCategory);
                    bundle.putString("state","");
                    bundle.putString("email","");
                    bundle.putString("rate","");
                    bundle.putString("district", "");
                    bundle.putString("keyword","");
                    bundle.putString("country","");
                    services.setArguments(bundle);
                    android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentcontener, services);
                    fragmentTransaction.setCustomAnimations(R.anim.fadein, R.anim.fadeout);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });




    }

}

