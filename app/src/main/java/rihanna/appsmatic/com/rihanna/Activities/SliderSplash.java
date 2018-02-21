package rihanna.appsmatic.com.rihanna.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rihanna.appsmatic.com.rihanna.API.Models.Advartisments.Responseadv;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.Generator;
import rihanna.appsmatic.com.rihanna.API.WebServiceTools.RihannaAPI;
import rihanna.appsmatic.com.rihanna.OffLineOrder.offAddress;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.views.BannerSlider;

public class SliderSplash extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout;
    TextView text,skip;
    List<String>strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_splach);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Check Os Ver For Set Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }


        sliderLayout = (SliderLayout)findViewById(R.id.photo_slider);
        text=(TextView)findViewById(R.id.phototext);
        skip=(TextView)findViewById(R.id.skip);

        text.setTypeface(Home.face);

        strings=new ArrayList<>();
        Generator.createService(RihannaAPI.class).getAds().enqueue(new Callback<Responseadv>() {
            @Override
            public void onResponse(Call<Responseadv> call, Response<Responseadv> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAdvertisements() != null) {
                        if (response.body().getAdvertisements().isEmpty()) {
                            startActivity(new Intent(SliderSplash.this, Home.class));
                            SliderSplash.this.finish();
                        } else {
                            for (int i = 0; i < response.body().getAdvertisements().size(); i++) {
                                TextSliderView textSliderView = new TextSliderView(SliderSplash.this);
                                textSliderView
                                        .image(response.body().getAdvertisements().get(i).getImages().get(0).getSrc().toString())
                                        .setScaleType(BaseSliderView.ScaleType.Fit)
                                        .setOnSliderClickListener(SliderSplash.this);
                                sliderLayout.addSlider(textSliderView);
                                strings.add(response.body().getAdvertisements().get(i).getText());
                            }

                            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            sliderLayout.setCustomAnimation(new DescriptionAnimation());
                            sliderLayout.setDuration(3000);
                            sliderLayout.addOnPageChangeListener(SliderSplash.this);
                            sliderLayout.setCurrentPosition(View.DRAWING_CACHE_QUALITY_AUTO);


                        }
                    }


                } else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Responseadv> call, Throwable t) {

                //Initialize Done Dialog
                final NiftyDialogBuilder dialogBuildercard = NiftyDialogBuilder.getInstance(SliderSplash.this);
                dialogBuildercard
                        .withDuration(700)//def
                        .withEffect(Effectstype.Fall)
                        .withIcon(getResources().getDrawable(R.drawable.logo))
                        .withDialogColor(Color.BLACK)
                        .withTitleColor(Color.WHITE)
                        .withMessage(getResources().getString(R.string.connectionerr))
                        .withTitle(getResources().getString(R.string.connectionerror))
                        .isCancelableOnTouchOutside(false)
                        .withButton1Text(getResources().getString(R.string.dissmis))
                        .setButton1Click(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogBuildercard.dismiss();
                                SliderSplash.this.finish();
                            }
                        }).show();


               // Toast.makeText(getApplicationContext(), "Connection Error from Images Slider Splash " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        skip.setTypeface(Home.face);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(SliderSplash.this, R.anim.alpha);
                skip.clearAnimation();
                skip.setAnimation(anim);
                startActivity(new Intent(SliderSplash.this, Home.class));
                SliderSplash.this.finish();
            }
        });



    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {

       text.setText(strings.get(position).toString());
        Log.e("indexxx",position+"");

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
