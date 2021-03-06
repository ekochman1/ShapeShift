package com.example.eddie.shapeshift;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout Profile_Section;
    private Button SignOut;
    private SignInButton SignIn;
    private Button mStartbutton;
    private TextView Name, Email;
    private ImageView Profile_Pic;
    private ImageView FrtPhoto;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Profile_Section = (LinearLayout) findViewById(R.id.profile_section);
        SignOut = (Button) findViewById(R.id.bn_logout);
        SignIn = (SignInButton) findViewById(R.id.bn_login);
        Name = (TextView) findViewById(R.id.name);
        Email = (TextView) findViewById(R.id.email);
        FrtPhoto = (ImageView) findViewById(R.id.imageView_photo);
        Profile_Pic = (ImageView) findViewById(R.id.prof_pic);
        mStartbutton = (Button) findViewById(R.id.bn_get_fit);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Profile_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        mStartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomepageActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bn_login:
                signIn();
                break;
            case R.id.bn_logout:
                signOut();
                break;

        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void signOut()

    {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    private void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            //String img_url = account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
           // Glide.with(this).load(img_url).into(Profile_Pic);
            updateUI(true);

        }
        else
        {
            updateUI(false);
        }
    }



    private void updateUI (boolean isLogin)
    {
        if (isLogin)
        {
            Profile_Section.setVisibility(View.VISIBLE);
            FrtPhoto.setVisibility(View.GONE);
            SignIn.setVisibility(View.GONE);
        }
        else
        {
            Profile_Section.setVisibility(View.GONE);
            FrtPhoto.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.VISIBLE);
        }


    }

    @Override

    protected  void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REQ_CODE)
        {
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

}
