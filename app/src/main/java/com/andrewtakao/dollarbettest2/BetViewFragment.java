package com.andrewtakao.dollarbettest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BetViewFragment extends Fragment {


    public BetViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_bet_view, container, false);
        // Inflate the layout for this fragment
        TextView betName = (TextView) fragmentLayout.findViewById(R.id.bet_name);

        Intent intent = getActivity().getIntent();

        betName.setText(intent.getExtras().getString(BetsActivity.BET_NAME_EXTRA));
        return inflater.inflate(R.layout.fragment_bet_view, container, false);
    }

}
