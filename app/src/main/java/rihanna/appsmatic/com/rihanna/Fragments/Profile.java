package rihanna.appsmatic.com.rihanna.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import rihanna.appsmatic.com.rihanna.Prefs.SaveSharedPreference;
import rihanna.appsmatic.com.rihanna.R;

public class Profile extends Fragment {


    private EditText fname,lname,email;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fname=(EditText)view.findViewById(R.id.username_input_profile);
        email=(EditText)view.findViewById(R.id.email_input_profile);



        fname.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getFirstName().toString()+" "+SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getBillingAddress().getLastName().toString());
        email.setText(SaveSharedPreference.getCustomerInfo(getContext()).getCustomers().get(0).getEmail()+"");













    }
}
