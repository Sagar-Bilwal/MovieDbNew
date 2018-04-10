package com.example.sagar.moviedb1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    Button signIn,signUp;
    TextView slogan;
    LoginButton loginButton;
    CallbackManager callbackManager;
    public static final String ISLOGIN ="ISLOGIN";
    public static final String Login="LOGIN";
    private static final String EMAIL = "EMAIL";
    public static boolean isLogin;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(Login, MODE_PRIVATE);
        isLogin = sharedPreferences.getBoolean(ISLOGIN, false);
        if (isLogin) {
            Intent i = new Intent(Login.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            signIn = findViewById(R.id.signIn);
            signUp = findViewById(R.id.signUp);
            slogan = findViewById(R.id.slogan);
            callbackManager = CallbackManager.Factory.create();
            loginButton = findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList(EMAIL));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    sharedPreferences.edit().putBoolean(ISLOGIN, true).apply();
                    Intent i = new Intent(Login.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    Toast.makeText(Login.this, "Error in Logging In", Toast.LENGTH_LONG).show();
                }
            });
            Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
            slogan.setTypeface(custom_font);
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, SignIn.class);
                    startActivity(intent);
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, SignUp.class);
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
