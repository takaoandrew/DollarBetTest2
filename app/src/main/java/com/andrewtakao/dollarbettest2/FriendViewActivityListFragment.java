package com.andrewtakao.dollarbettest2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendViewActivityListFragment extends ListFragment {

    private ArrayList<Bet> bets;
    private BetAdapter betAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*String[] values = new String[]{ "Android", "iPhone"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);*/

        bets = new ArrayList<>();
        bets.add(new Bet("Pats will win the super bowl", Bet.Category.REQUESTED));

        betAdapter = new BetAdapter(getActivity(), bets);
        setListAdapter(betAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }


}
