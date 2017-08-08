package rihanna.appsmatic.com.rihanna.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

import rihanna.appsmatic.com.rihanna.R;

public class SliderSplash extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {



    SliderLayout sliderLayout;
    TextView text;
    List<String>strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
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


        TextSliderView textSliderView = new TextSliderView(SliderSplash.this);


        strings=new ArrayList<>();

        strings.add("الاعلان رقم 1 ");
        strings.add("نقدم افضل العروض لخدمات التجميل وخبيرات العنايه بالبشرة");
        strings.add("نdfdfgdfgdfgdfgdfg بالبشرة");
        strings.add("نقدssssفضل اdfgdfgdfgdfgلعنايه بالبشرة");
        strings.add("نقدم افضل الtttttttttttttttttttبشرة");
        strings.add("نقدم افضل العروض لخدمات التجميل وخبttyyyبشرة");



      for (int i=0;i<strings.size();i++){
          textSliderView
                  .image(R.drawable.slidertest)
                  .setScaleType(BaseSliderView.ScaleType.Fit)
                  .setOnSliderClickListener(this);


          sliderLayout.addSlider(textSliderView);

      }





        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
        sliderLayout.setCurrentPosition(View.DRAWING_CACHE_QUALITY_AUTO);





















    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {

       text.setText(strings.get(position).toString());
        Log.e("indexxx",position+"");
        if(strings.size()==position+1){
            startActivity(new Intent(SliderSplash.this,SignInScreen.class));
            SliderSplash.this.finish();
            sliderLayout.stopAutoCycle();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
