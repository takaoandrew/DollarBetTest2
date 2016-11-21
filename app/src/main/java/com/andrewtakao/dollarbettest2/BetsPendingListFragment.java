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
public class BetsPendingListFragment extends ListFragment {

    private ArrayList<Bet> bets;
    private BetPendingAdapter betPendingAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bets = new ArrayList<>();
        bets.add(new Bet("The patriots are going to win the super bowl", Bet.Category.REQUESTED));
        bets.add(new Bet("Sanae and I will get married", Bet.Category.PENDING));
        betPendingAdapter = new BetPendingAdapter(getActivity(), bets);
        setListAdapter(betPendingAdapter);

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
        Intent intent = new Intent(getActivity(), BetPendingDetailActivity.class);
        intent.putExtra(BetsActivity.BET_NAME_EXTRA, bet.getBet());
        intent.putExtra(BetsActivity.BET_CATEGORY_EXTRA, bet.getCategory());
        intent.putExtra(BetsActivity.BET_ID_EXTRA, bet.getBetId());

        startActivity(intent);
    }


}
