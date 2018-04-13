package com.example.eddie.shapeshift;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Eddie on 4/11/18.
 */

public class InputCalcActivity extends AppCompatActivity {

    private RadioButton male;
    private RadioButton female;
    private TextView gender;
    private TextView age;
    private TextView weight;
    private TextView height;
    private TextView waist;
    private TextView hip;
    private TextView neck;
    private EditText edit_age;
    private EditText edit_weight;
    private EditText edit_height;
    private EditText edit_waist;
    private EditText edit_hip;
    private EditText edit_neck;
    private Button calc;
    private Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputcalc);
        male = (RadioButton) findViewById(R.id.radiobn_male);
        female = (RadioButton) findViewById(R.id.radiobn_female);
        gender = (TextView) findViewById(R.id.textView_gender);
        age = (TextView) findViewById(R.id.textView_age);
        weight = (TextView) findViewById(R.id.textView_wt);
        height = (TextView) findViewById(R.id.textView_ht);
        waist = (TextView) findViewById(R.id.textView_waist);
        hip = (TextView) findViewById(R.id.textView_hip);
        neck = (TextView) findViewById(R.id.textView_neck);
        edit_age = (EditText) findViewById(R.id.edittext_age);
        edit_weight = (EditText) findViewById(R.id.edittext_wt);
        edit_height = (EditText) findViewById(R.id.edittext_ht);
        edit_waist = (EditText) findViewById(R.id.edittext_waist);
        edit_hip = (EditText) findViewById(R.id.edittext_ht);
        edit_neck = (EditText) findViewById(R.id.edittext_neck);
        calc = (Button) findViewById(R.id.bn_calc);
        skip = (Button) findViewById(R.id.bn_skip);

    }

    public void calculateBMI(View view) {
        String heightString = height.getText().toString();
        String weightString = weight.getText().toString();

        if (heightString != null && !"".equals(heightString)
            && weightString != null && !"".equals(weightString)) {
            float heightValue = Float.parseFloat(heightString) /100;
            float weightValue =  Float.parseFloat(weightString);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);

        }
    }

    private void displayBMI (float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0 && Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0 && Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0 && Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0 && Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0 && Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0 && Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        //result.setText(bmiLabel);

        }


    public void calculateBMR(View view) {
        String heightString = height.getText().toString();
        String weightString = weight.getText().toString();
        String ageString = age.getText().toString();

        if (heightString != null && !"".equals(heightString)
                && weightString != null && !"".equals(weightString)
                && ageString != null && !"".equals(ageString)) {
            float heightValue = Float.parseFloat(heightString) / 100;
            float weightValue = Float.parseFloat(weightString);
            float ageValue = Float.parseFloat(ageString);
//women
            float bmr1 = (float) (655 + (4.35 * weightValue) + (4.7 * heightValue) - (6.8 * ageValue));
//men
            float bmr2 = (float) (66 + (6.23 * weightValue) + (12.7 * heightValue) - (6.8 * ageValue));

            displayBMR(bmr1, bmr2);

        }
    }

    private void displayBMR (float bmr1, float bmr2) {
        String bmrLabel = "";
    }
    }


