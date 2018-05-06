package com.example.eddie.shapeshift;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * Created by Eddie on 4/14/18.
 *
 */


public class HomepageActivity extends AppCompatActivity {

    private static final String TAG = "HomepageActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private ImageButton healthtrack;
    private ImageButton recipes;
    private ImageButton workouts;
    private ImageButton map;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        healthtrack = (ImageButton) findViewById(R.id.bn_Log);
        //recipes = (ImageButton) findViewById(R.id.bn_recipes);
        workouts = (ImageButton) findViewById(R.id.bn_workouts);
        map = (ImageButton) findViewById(R.id.btnMap);
        back = (ImageButton) findViewById(R.id.bn_back);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this, MainActivity.class));

            }
        });


        if (isServicesOK()) {
            init();

        }
    }


    public void setWorkouts(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodybuilding.com/exercises/"));
        startActivity(browserIntent);
    }




    private void init(){
        ImageButton btnMap = (ImageButton) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

            ImageButton log = (ImageButton) findViewById(R.id.bn_Log);
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomepageActivity.this, InputCalcActivity.class);
                    startActivity(intent);
                }
            });
    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(HomepageActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(HomepageActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}












