package com.example.sagar.moviedb1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sagar.moviedb1.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignIn extends AppCompatActivity {

    EditText password,phoneNo;
    Button buttonSignIn;
    final public String NAME="NAME";
    final public String PHONENO="PHONENO";
    final public String SIGNIN="com.example.sagar.moviedb1.Users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        password=(MaterialEditText)findViewById(R.id.password);
        phoneNo=(MaterialEditText)findViewById(R.id.phone);
        buttonSignIn =findViewById(R.id.buttonSignIn);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference= firebaseDatabase.getReference("User");

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(phoneNo.getText().toString()).exists()) {
                            Log.d("Error", "onDataChange Reached");
                            progressDialog.dismiss();
                            User user = dataSnapshot.child(phoneNo.getText().toString()).getValue(User.class);
                            assert user != null;
                            if (user.getPassword().equals(password.getText().toString())) {
                                SharedPreferences login=getSharedPreferences(CONSTANTS.Login,MODE_PRIVATE);
                                login.edit().putBoolean(CONSTANTS.ISLOGIN,true).apply();
                                Toast.makeText(SignIn.this, "Signed In Successfully", Toast.LENGTH_LONG).show();
                                SharedPreferences sharedPreferences=getSharedPreferences(SIGNIN,MODE_PRIVATE);
                                sharedPreferences.edit().putString(NAME,user.getName()).apply();
                                sharedPreferences.edit().putString(PHONENO,phoneNo.getText().toString()).apply();
                                Intent i2 = new Intent(SignIn.this, MainActivity.class);
                                i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i2);
                            } else {
                                Toast.makeText(SignIn.this, "Enter Correct Password", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(SignIn.this, "User does not exists", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent i = new Intent(SignIn.this, Login.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("error", "omCancelled Reached");
                    }
                });
            }
        });
    }
}
