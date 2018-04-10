package com.example.sagar.moviedb1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sagar.moviedb1.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText phoneNo,name,password;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        phoneNo=findViewById(R.id.upPhone);
        name=findViewById(R.id.upName);
        password=findViewById(R.id.upPassword);
        signUp=findViewById(R.id.buttonSignUp);
        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(phoneNo.getText().toString()).exists())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this,"Account already Exists",Toast.LENGTH_LONG).show();
                        }
                        else
                        {

                            progressDialog.dismiss();
                            String PhoneNo=phoneNo.getText().toString();
                            boolean flag=true;
                            for(int x=0;x<phoneNo.length();x++)
                            {
                                if(!(PhoneNo.charAt(x)=='0' || PhoneNo.charAt(x)=='1' || PhoneNo.charAt(x)=='2' || PhoneNo.charAt(x)=='3' || PhoneNo.charAt(x)=='4' || PhoneNo.charAt(x)=='5' || PhoneNo.charAt(x)=='6' || PhoneNo.charAt(x)=='7' || PhoneNo.charAt(x)=='8' ||PhoneNo.charAt(x)=='9' ))
                                {
                                    flag=false;
                                    break;
                                }
                            }
                            if(!flag)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this,"Enter Correct Phone No.",Toast.LENGTH_LONG).show();
                            }
                            else if(phoneNo.getText().toString().length()<10 || phoneNo.getText().toString().length()>10)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this,"Enter Correct Phone No.",Toast.LENGTH_LONG).show();
                            }
                            else if(name.getText().toString().length()==0 || name.getText().toString().equals(" "))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this,"Enter Correct Name",Toast.LENGTH_LONG).show();
                            }
                            else if(password.getText().toString().length()<8)
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this,"Password is too short",Toast.LENGTH_LONG).show();
                            }
                            else {
                                User user = new User(password.getText().toString(), name.getText().toString());
                                databaseReference.child(phoneNo.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "SignUp Successful", Toast.LENGTH_LONG).show();
                                databaseReference.removeEventListener(this);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(SignUp.this,"Error Ocurred,SignUp Failed",Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }
}
