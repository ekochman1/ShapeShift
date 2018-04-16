package com.example.eddie.shapeshift;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eddie on 4/11/18.
 */

public class InputCalcActivity extends AppCompatActivity {

    //private RadioButton male;
    //private RadioButton female;
    private RadioGroup sex;
    private EditText edit_age;
    private EditText edit_weight;
    private EditText edit_height;
    private EditText edit_waist;
    private EditText edit_bfp;
    private EditText edit_hip;
    private EditText edit_neck;
    private Button calc;
    private Button skip;

    private int ageInput;
    private int heightInput;
    private double bodyfatPercent = -1;
    private double weightInput;
    private int activityLevel=0;
    private boolean male = true;
    private boolean female = false;


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radiobn_male:
                male = true;
                female = false;
                break;
            case R.id.radiobn_female:
                female = true;
                male = false;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputcalc);

    }


    public void onCalculate(View view) {
        try {
            edit_age = (EditText) findViewById(R.id.edittext_age);
            edit_height = (EditText) findViewById(R.id.edittext_ht);
            edit_bfp = (EditText) findViewById(R.id.edittext_bfp);
            edit_weight = (EditText) findViewById(R.id.edittext_wt);
            if (isEmpty(edit_age) && isEmpty(edit_height) && isEmpty(edit_weight) &&
                    !edit_weight.toString().equals(".") && !edit_bfp.toString().equals(".")) {
                ageInput = Integer.parseInt(edit_age.getText().toString());
                heightInput = Integer.parseInt(edit_height.getText().toString());
                Intent passdata_intent = new Intent(this, CalcResultsActivity.class);
                if (isEmpty(edit_bfp)) {
                    bodyfatPercent = Double.parseDouble(edit_bfp.getText().toString());
                    passdata_intent.putExtra("bodyfatPercent", bodyfatPercent);
                } else {
                    passdata_intent.putExtra("bodyfatPercent", -1);
                }
                weightInput = Double.parseDouble(edit_weight.getText().toString());
                passdata_intent.putExtra("mass", weightInput);
                passdata_intent.putExtra("activityLevel", activityLevel);
                passdata_intent.putExtra("height", heightInput);
                passdata_intent.putExtra("age", ageInput);
                if (male == true) {
                    passdata_intent.putExtra("ifMale", true);
                } else {
                    passdata_intent.putExtra("ifMale", false);
                }
                if((ageInput<=120)&&(ageInput>=0)&&(heightInput<=250)&&(bodyfatPercent<100)&&(weightInput<500))
                {
                    startActivity(passdata_intent);
                }
                else
                {
                    Toast notFinished = new Toast(getApplicationContext());
                    notFinished.makeText(getApplicationContext(), "Your Figures Seem Incorrect, Please Enter Valid Figures.", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast notFinished = new Toast(getApplicationContext());
                notFinished.makeText(getApplicationContext(), "Please Complete The Necessary Sections Correctly", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast notFinished = new Toast(getApplicationContext());
            notFinished.makeText(getApplicationContext(), "Please Complete The Necessary Sections Correctly", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() != 0;
    }
}