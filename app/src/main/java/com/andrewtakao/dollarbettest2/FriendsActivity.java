package com.andrewtakao.dollarbettest2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class FriendsActivity extends AppCompatActivity {

    public static final String FRIEND_ID_EXTRA = "com.andrewtakao.dollarbettest2.Identifier";
    public static final String FRIEND_NAME_EXTRA = "com.andrewtakao.dollarbettest2.Title";
    public static final String FRIEND_BET_REQUESTED_EXTRA = "com.andrewtakao.dollarbettest2.Message";
    public static final String FRIEND_IMAGE_EXTRA = "com.andrewtakao.dollarbettest2.Category";
    public static final String NOTE_FRAGMENT_TO_LOAD_EXTRA =
            "com.andrewtakao.dollarbettest2.Fragment_To_Load";
    public enum FragmentToLaunch{ VIEW, EDIT, CREATE}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FriendDetailActivity.class);
                intent.putExtra(FriendsActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, FragmentToLaunch.CREATE);
                startActivity(intent);
            }
        });
        loadPreferences();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, AppPreferences.class);
            startActivity(intent);
            return true;
        }else if (id==R.id.action_add_note){
            Intent intent = new Intent(this, FriendDetailActivity.class);
            intent.putExtra(FriendsActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, FragmentToLaunch.CREATE);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean("background_color", false);
        if (isBackgroundDark){
            LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_activity_layout);
            mainLayout.setBackgroundColor(Color.parseColor("#3c3f41"));
        }

        String notebookTitle = sharedPreferences.getString("Title", "Dollar Bet");
        setTitle(notebookTitle);
    }
}
