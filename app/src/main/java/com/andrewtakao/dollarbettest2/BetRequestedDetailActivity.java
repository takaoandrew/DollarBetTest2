package com.andrewtakao.dollarbettest2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BetRequestedDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_requested_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Requested Bets");
        createAndAddFragment();
    }

    private void createAndAddFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BetRequestedViewFragment betRequestedViewFragment = new BetRequestedViewFragment();
        //setTitle("Bets");
        fragmentTransaction.add(R.id.content_bet_requested_detail, betRequestedViewFragment, "BET_VIEW_FRAGMENT");
        fragmentTransaction.commit();


    }
}
