package com.andrewtakao.dollarbettest2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class BetsActivity extends AppCompatActivity {

    public static final String BET_ID_EXTRA = "com.andrewtakao.dollarbettest2.Bet Identifier";
    public static final String BET_NAME_EXTRA = "com.andrewtakao.dollarbettest2.Bet Name";
    public static final String BET_CATEGORY_EXTRA = "com.andrewtakao.dollarbettest2.Bet Category";
    public static final String BETTER_NAME_EXTRA = "com.andrewtakao.dollarbettest2.Better Name";
    private String betterNameExtra;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bets);
        Intent intent = getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        betterNameExtra = intent.getExtras().getString(FriendsActivity.FRIEND_NAME_EXTRA);
        toolbar.setTitle(betterNameExtra);
        //Intent intent1 = new Intent(this,BetsActivity.class);
        //intent1.putExtra(BETTER_NAME_EXTRA, betterNameExtra);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buildDialogue();
                dialog.show();
            }
        });
    }

    private void buildDialogue() {

        final String[] categories = new String[]{"Requested", "Pending", "Resolved"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(this);
        categoryBuilder.setTitle("Choose Bet Type");
        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface categoryBuilder, int item) {
                dialog.cancel();
                Intent intent;
                switch (item) {
                    case 0:
                        //BetsRequestedListFragment.addBets(BetsRequestedListFragment.bets,"Test");

                        //addListItem
                        break;
                    case 1:
                        //intent = new Intent(getBaseContext(), BetPendingDetailActivity.class);
                        //startActivity(intent);
                        break;
                    case 2:
                        //Intent intent = new Intent(getBaseContext(), BetsRequestedListFragment.class);
                        //startActivity(intent);
                        break;
                    default:
                        break;

                }
            }
        });
        dialog = categoryBuilder.create();
    }

    public String getBetterNameExtra() {
        return betterNameExtra;
    }
}
