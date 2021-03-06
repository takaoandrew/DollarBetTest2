package com.andrewtakao.dollarbettest2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class BetPendingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_pending_detail);
        createAndAddFragment();
    }

    private void createAndAddFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BetPendingViewFragment betPendingViewFragment = new BetPendingViewFragment();
        setTitle("Bets");
        fragmentTransaction.add(R.id.content_bet_pending_detail, betPendingViewFragment, "BET_VIEW_FRAGMENT");
        fragmentTransaction.commit();

    }
}
