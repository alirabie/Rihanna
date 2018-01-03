package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;


public class AboutApp extends Fragment {


    private TextView txt1,txt2,txt3;
    private Button bt1,bt2,bt3;
    private ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3;
    private ImageView copyright,twBtn,instaBtn,fbBtn,gmBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_app, container, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Social media buttons
        twBtn=(ImageView)view.findViewById(R.id.tw_btn);
        instaBtn=(ImageView)view.findViewById(R.id.insta_btn);
        fbBtn=(ImageView)view.findViewById(R.id.fb_btn);
        gmBtn=(ImageView)view.findViewById(R.id.gm_btn);




        copyright=(ImageView)view.findViewById(R.id.copyright_tv);
        bt1=(Button)view.findViewById(R.id.expandableButton1);
        bt2=(Button)view.findViewById(R.id.expandableButton2);
        bt3=(Button)view.findViewById(R.id.expandableButton3);
        expandableLayout1 = (ExpandableRelativeLayout)view.findViewById(R.id.expandableLayout1);
        expandableLayout1.collapse();
        expandableLayout2 = (ExpandableRelativeLayout)view.findViewById(R.id.expandableLayout2);
        expandableLayout2.collapse();
        expandableLayout3 = (ExpandableRelativeLayout)view.findViewById(R.id.expandableLayout3);
        expandableLayout3.collapse();


        //check language
        if(SaveSharedPreference.getLangId(getContext()).equals("ar")){
            copyright.setImageResource(R.drawable.copyright);
        }else{
            copyright.setImageResource(R.drawable.copyright_en);
        }













        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt1=(TextView)expandableLayout1.findViewById(R.id.text1);
                txt1.setText("تتألف شروط الاستخدام هذه من الشروط والأحكام الواردة أدناه بالإضافة إلى الشروط الموضحة في سياسة الخصوصية (وفقًا للتعديلات التي يتم إجراؤها من حين لآخر)" +
                        "\n"+"تطبيق ريحانة ينتمي الي شركة ريحانة ولا يسمح بمحاكاته او تقليده"+
                        "\n"+"التأكد من صحة كل بيانات الخبيرة مسؤوليتها قانونيا"+
                        "\n"+"التعامل بمهنية مع العميلات وحفظ خصوصياتهم ");
                expandableLayout1.toggle(); // toggle expand and collapse
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                txt2=(TextView)expandableLayout2.findViewById(R.id.text2);
                txt2.setText("");
                expandableLayout2.toggle(); // toggle expand and collapse

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                expandableLayout3.toggle(); // toggle expand and collapse

            }
        });




        twBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                twBtn.clearAnimation();
                twBtn.setAnimation(anim);
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/RIHANNA_APP"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/RIHANNA_APP"));
                }

                startActivity(intent);
            }
        });




        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                fbBtn.clearAnimation();
                fbBtn.setAnimation(anim);
                String facebookUrl = "https://www.facebook.com/<id_here>";
                try {
                    int versionCode =getContext().getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) {
                        Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    } else {
                        Uri uri = Uri.parse("fb://page/<id_here>");
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl)));
                }
            }
        });


        instaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                instaBtn.clearAnimation();
                instaBtn.setAnimation(anim);
                Uri uri = Uri.parse("https://www.instagram.com/rihanna.app/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/rihanna.app/")));
                }
            }
        });


        gmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                gmBtn.clearAnimation();
                gmBtn.setAnimation(anim);
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setClassName("com.google.android.apps.plus",
                            "com.google.android.apps.plus.phone.UrlGatewayActivity");
                    intent.putExtra("customAppUri","test");
                    startActivity(intent);
                } catch(ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/test/posts")));
                }
            }
        });


    }
}
