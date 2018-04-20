package com.example.sagar.moviedb1;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class Profile extends Fragment
{
    View view;
    Button logout;
    TextView phoneNo;
    TextView nameText;
    final public String NAME="NAME";
    final public String PHONENO="PHONENO";
    final public String SIGNIN="com.example.sagar.moviedb1.Users";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_profile, container, false);
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(),SignIn.class);
                startActivity(intent);
            }
        });
        phoneNo=view.findViewById(R.id.phone_profile);
        nameText=view.findViewById(R.id.name_profile);
        SharedPreferences sharedPreferences=view.getContext().getSharedPreferences(SIGNIN,MODE_PRIVATE);
        String name=sharedPreferences.getString(NAME,"");
        String phoneno=sharedPreferences.getString(PHONENO,"");
        phoneNo.setText(phoneno);
        nameText.setText(name);
        return view;
    }
}
