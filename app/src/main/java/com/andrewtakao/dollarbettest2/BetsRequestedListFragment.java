package com.andrewtakao.dollarbettest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetsRequestedListFragment extends ListFragment {

    private ArrayList<Bet> bets;
    private BetRequestedAdapter betRequestedAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*String[] values = new String[]{ "Android", "iPhone"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);*/

        bets = new ArrayList<>();
        bets.add(new Bet("Pats will win the super bowl", Bet.Category.REQUESTED));
        bets.add(new Bet("Germany will win the world cup", Bet.Category.RESOLVED));
        betRequestedAdapter = new BetRequestedAdapter(getActivity(), bets);
        setListAdapter(betRequestedAdapter);

        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.white));
        getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchBetDetailActivity(position);
    }

    private void launchBetDetailActivity(int position) {
        Bet bet = (Bet) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), BetDetailActivity.class);
        intent.putExtra(BetsActivity.BET_NAME_EXTRA, bet.getBet());
        intent.putExtra(BetsActivity.BET_CATEGORY_EXTRA, bet.getCategory());
        intent.putExtra(BetsActivity.BET_ID_EXTRA, bet.getBetId());

        startActivity(intent);
    }


}
