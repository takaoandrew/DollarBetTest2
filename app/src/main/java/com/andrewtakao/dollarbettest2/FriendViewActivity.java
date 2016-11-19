package com.andrewtakao.dollarbettest2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class FriendViewActivity extends AppCompatActivity {

    public static final String BET_ID_EXTRA = "com.andrewtakao.dollarbettest2.Bet Identifier";
    public static final String BET_NAME_EXTRA = "com.andrewtakao.dollarbettest2.Bet Name";
    public static final String BET_CATEGORY_EXTRA = "com.andrewtakao.dollarbettest2.Bet Category";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
