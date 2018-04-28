package com.example.eddie.shapeshift;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eddie on 4/21/18.
 */

public class ListViewActivity extends AppCompatActivity {

    private static final String LOG = "ListViewActivity";

    DatabaseHelperActivity myDB;

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView = (ListView) findViewById(R.id.listView);
        //ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelperActivity(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(LOG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        //populate an ArrayList<String> from the database and then view it
        Cursor data = myDB.getListContents();
        ArrayList<String> theList = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the arraylist
            theList.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
        mListView.setAdapter(adapter);

    //set an onItemClickListener to ListView
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String bmi = adapterView.getItemAtPosition(i).toString();
            Log.d(LOG, "onItemClick: You Clicked on" + bmi);
            //get the date associated with bmi
            Cursor data = myDB.getItemDate(bmi);
            int itemDate = -1;
            while (data.moveToNext()) {
                itemDate = data.getInt(0);
            }
            if (itemDate > -1){
                Log.d(LOG, "onItemClick: The Date is: " + itemDate);
                Intent editScreenIntent = new Intent (ListViewActivity.this, CalcResultsActivity.class);
                editScreenIntent.putExtra("date", itemDate);
                editScreenIntent.putExtra("bmi", bmi);
                startActivity(editScreenIntent);
            }
            else {
                toastMessage("No date for this bmi");
            }
    }
    });
    }
    private void toastMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
