package com.example.eddie.shapeshift;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteUserFragment extends Fragment {



    public DeleteUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_user, container, false);

        final EditText TxtUserName = view.findViewById(R.id.IDtext);
        Button DeleteButton = view.findViewById(R.id.delete);

        DeleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                int name = Integer.parseInt(TxtUserName.getText().toString());
                User user = new User();
                user.setID(name);

                LogActivity.myAppDatabase.myDao().deleteUser(user);

                Toast.makeText(getActivity(), "Log successfully removed", Toast.LENGTH_SHORT).show();
                TxtUserName.setText("");

            }

        });
        return view;
    }

}

