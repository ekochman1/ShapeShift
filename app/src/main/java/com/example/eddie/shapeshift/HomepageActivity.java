package com.example.eddie.shapeshift;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eddie on 4/14/18.
 */

public class HomepageActivity extends AppCompatActivity {

    private Button healthtrack;
    private Button recipes;
    private Button workouts;
    private Button map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        healthtrack = (Button) findViewById(R.id.bn_Log);
        recipes= (Button) findViewById(R.id.bn_recipes);
        workouts= (Button) findViewById(R.id.bn_workouts);
        map= (Button) findViewById(R.id.bn_healthspots);

    }

    public void setWorkouts(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bodybuilding.com/exercises/"));
        startActivity(browserIntent);


    }
}





