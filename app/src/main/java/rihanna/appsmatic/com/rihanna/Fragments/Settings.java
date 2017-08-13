package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.ArrayList;
import java.util.List;

import rihanna.appsmatic.com.rihanna.Activities.Home;
import rihanna.appsmatic.com.rihanna.Activities.Splash;
import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;


public class Settings extends Fragment {

    private BetterSpinner langSpinner;
    List<String> languas=new ArrayList<>();
    private int langFlag;
    private boolean loadImagesFlag=false;
    private RadioButton yes,no;
    private TextView acceptBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        languas.add(0, "عربي");
        languas.add(1, "English");
        langSpinner=(BetterSpinner)view.findViewById(R.id.lang_spinner);
        yes=(RadioButton)view.findViewById(R.id.yes_check);
        no=(RadioButton)view.findViewById(R.id.no_check);
        yes.setTypeface(Home.face);
        no.setTypeface(Home.face);
        langSpinner.setTypeface(Home.face);
        acceptBtn=(TextView)view.findViewById(R.id.accept_btn);
        acceptBtn.setTypeface(Home.face);



        //Lang selection
        ArrayAdapter<String> langListdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_list_custome, languas);
        langSpinner.setAdapter(langListdapter);
        langSpinner.setHint(getResources().getString(R.string.chose));
        langSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Set Lang Flag
                switch (position) {
                    case 0:
                        langFlag = 1;
                        break;
                    case 1:
                        langFlag = 2;
                        break;

                }

            }
        });




        //set image check button status
        if(SaveSharedPreference.getImgLoadingSatatus(getContext())){
            yes.setChecked(true);
        }else {
            no.setChecked(true);
        }







        //Accept button action
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);
                acceptBtn.clearAnimation();
                acceptBtn.setAnimation(anim);


                    final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getContext());
                    dialogBuilder
                            .withTitle(getResources().getString(R.string.app_name))
                            .withDialogColor(R.color.colorPrimary)
                            .withTitleColor(getResources().getColor(R.color.colorPrimary))
                                    // .withIcon(getResources().getDrawable(R.drawable.icon))
                            .withDuration(700)                                          //def
                            .withEffect(Effectstype.RotateBottom)
                            .withMessage(getResources().getString(R.string.restart))
                            .withButton1Text(getResources().getString(R.string.yes))
                            .withButton2Text(getResources().getString(R.string.no))
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (yes.isChecked()) {
                                        loadImagesFlag = true;
                                    } else {
                                        loadImagesFlag = false;
                                    }

                                    if (no.isChecked()) {
                                        loadImagesFlag = false;
                                    } else {
                                        loadImagesFlag = true;
                                    }


                                    //Save Lang Selection depended on lang flag
                                    switch (langFlag) {
                                        case 0:

                                            break;
                                        case 1:
                                            SaveSharedPreference.setLangId(getActivity().getApplicationContext(), "ar");
                                            break;
                                        case 2:
                                            SaveSharedPreference.setLangId(getActivity().getApplicationContext(), "en");
                                            break;
                                    }


                                    //Control Images Loading
                                    SaveSharedPreference.setImgLoadStatus(getContext(), loadImagesFlag);


                                    getActivity().finish();
                                    getContext().startActivity(new Intent(getContext(), Splash.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

                                    dialogBuilder.dismiss();

                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    dialogBuilder.dismiss();
                                }
                            })
                            .show();


                }

        });








    }





}
