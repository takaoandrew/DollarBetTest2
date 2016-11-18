package com.andrewtakao.dollarbettest2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//A fragment representing one step
public class NewbieFragment extends Fragment {

    //What page number?
    public static final String PAGE = "page";
    private int pageNumber;

    //Factory method to construct fragment
    public static NewbieFragment create(int pageNumber) {
        NewbieFragment fragment = new NewbieFragment();
        Bundle args = new Bundle();
        args.putInt(PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public NewbieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView;

        switch (pageNumber) {
            case 0:
                rootView = (ViewGroup) inflater
                        .inflate(R.layout.welcome, container, false);
                break;
            case 1:
                rootView = (ViewGroup) inflater
                        .inflate(R.layout.about, container, false);
                Button btn = (Button) rootView.findViewById(R.id.toast_button);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity().getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                rootView = (ViewGroup) inflater
                        .inflate(R.layout.fragment_newbie, container, false);
                ((TextView) rootView.findViewById(R.id.text4)).setText(Integer.toString(pageNumber));

        }


        return rootView;
    }



    //Returns page number

    /*public int getPageNumber() {
        return pageNumber;
    }
    */

}
