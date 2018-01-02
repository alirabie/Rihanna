package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private ImageView copyright;

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






    }
}
