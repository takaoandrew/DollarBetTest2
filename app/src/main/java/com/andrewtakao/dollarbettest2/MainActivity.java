package com.andrewtakao.dollarbettest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for testing purposes
        Intent newActivity = new Intent(this, FriendsActivity.class);
        this.startActivity(newActivity);

    }
    Class<? extends Activity> activityClass = NewbieActivity.class;

    public void changeToggleState(View view) {
        boolean checked = ((ToggleButton)view).isChecked();
        if (checked){
            activityClass = FriendsActivity.class;
        }
        else {
            activityClass = NewbieActivity.class;
        }
    }

    public void submit(View view) {
        Intent newActivity = new Intent(this, activityClass);
        this.startActivity(newActivity);
    }
}
