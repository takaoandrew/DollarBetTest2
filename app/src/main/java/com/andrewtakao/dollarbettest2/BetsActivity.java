package com.andrewtakao.dollarbettest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class BetsActivity extends AppCompatActivity {

    public static final String BET_ID_EXTRA = "com.andrewtakao.dollarbettest2.Bet Identifier";
    public static final String BET_NAME_EXTRA = "com.andrewtakao.dollarbettest2.Bet Name";
    public static final String BET_CATEGORY_EXTRA = "com.andrewtakao.dollarbettest2.Bet Category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bets);
        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((intent.getExtras().getString(FriendsActivity.FRIEND_NAME_EXTRA)));
        setSupportActionBar(toolbar);
    }
}
