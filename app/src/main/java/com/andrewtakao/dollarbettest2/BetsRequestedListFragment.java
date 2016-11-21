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

    static ArrayList<Bet> bets;
    private BetRequestedAdapter betRequestedAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bets = new ArrayList<>();
        betRequestedAdapter = new BetRequestedAdapter(getActivity(), bets);
        setListAdapter(betRequestedAdapter);
        addBets(bets, "Pats will win the super bowl");
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
        Intent intent = new Intent(getActivity(), BetRequestedDetailActivity.class);
        //intent.putExtra(BetsActivity.BETTER_NAME_EXTRA, getActivity().getBetterNameExtra());
        intent.putExtra(BetsActivity.BET_NAME_EXTRA, bet.getBet());
        intent.putExtra(BetsActivity.BET_CATEGORY_EXTRA, bet.getCategory());
        intent.putExtra(BetsActivity.BET_ID_EXTRA, bet.getBetId());

        startActivity(intent);
    }

    public void addBets(ArrayList<Bet> betArrayList, String bet) {
        /*betArrayList.add(new Bet(bet, Bet.Category.REQUESTED));
        BetDbAdapter dbAdapter = new BetDbAdapter(getActivity().getBaseContext());
        dbAdapter.open();

        bets.clear();
        bets.addAll(dbAdapter.getAllBets());
        betRequestedAdapter.notifyDataSetChanged();

        dbAdapter.close()*/
    }


}
