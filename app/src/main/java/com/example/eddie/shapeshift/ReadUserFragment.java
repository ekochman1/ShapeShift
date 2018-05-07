package com.example.eddie.shapeshift;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    private TextView TxtInfo;


    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_user, container, false);
        TxtInfo = view.findViewById(R.id.txt_display_info);

        List<User> users = LogActivity.myAppDatabase.myDao().getUser();

        String info = "";

        for (User usr : users)
        {
            int ID = usr.getID();
            long Date = usr.getDate();
            int bmi = usr.getBMI();
            int bmr = usr.getBMR();
            int whtr= usr.getwhtr();
            int tdee= usr.getTdee();

            info = info+"\n\n"+"ID :" +ID+"\n BMI :" +bmi+"\n" +"BMR :"+bmr +"\n" +"WHtr :"+whtr +"\n" +"TDEE :"+tdee;

        }

        TxtInfo.setText(info);


        return view;
    }

}

