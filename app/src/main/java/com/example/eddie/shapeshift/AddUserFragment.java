package com.example.eddie.shapeshift;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddUserFragment extends Fragment {
    private EditText UserID, UserName, UserBmi, UserBmr, UserWhtr, UserTdee;
    private Button BnSave;

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        UserName = view.findViewById(R.id.txt_user_id);
        UserBmi = view.findViewById(R.id.txt_name);
        UserBmr = view.findViewById(R.id.txt_email2);
        UserWhtr = view.findViewById(R.id.txt_email);
        UserTdee = view.findViewById(R.id.txt_email3);
        BnSave = view.findViewById(R.id.bn_save_user);

        BnSave.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          //gets from interface


                                          int date = Integer.parseInt(UserName.getText().toString());
                                          int bmi = Integer.parseInt(UserBmi.getText().toString());
                                          int bmr = Integer.parseInt(UserBmr.getText().toString());
                                          int Whtr = Integer.parseInt(UserWhtr.getText().toString());
                                          int Tdee = Integer.parseInt(UserTdee.getText().toString());

                                          User user = new User();
                                          user.setDate(date);
                                          user.setBMI(bmi);
                                          user.setBMR(bmr);
                                          user.setWhtr(Whtr);
                                          user.setTdee(Tdee);

                                          LogActivity.myAppDatabase.myDao().addUser(user);
                                          Toast.makeText(getActivity(), "Log added successfully", Toast.LENGTH_SHORT).show();

                                          UserName.setText("");
                                          UserBmi.setText("");
                                          UserBmr.setText("");
                                          UserWhtr.setText("");
                                          UserTdee.setText("");



                                      }
                                  }
        );
        return view;

    }


}
