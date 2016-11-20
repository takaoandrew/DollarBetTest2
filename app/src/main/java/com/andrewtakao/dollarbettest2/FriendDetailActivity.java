package com.andrewtakao.dollarbettest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FriendDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA = "New Friend";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        createAndAddFragment();
    }

    protected void createAndAddFragment(){

        Intent intent = getIntent();
        FriendsActivity.FragmentToLaunch fragmentToLaunch =
                (FriendsActivity.FragmentToLaunch) intent.getSerializableExtra(FriendsActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(fragmentToLaunch){
            case EDIT:
                FriendEditFragment friendEditFragment = new FriendEditFragment();
                setTitle(R.string.edit_fragment_title);
                fragmentTransaction.add(R.id.note_container, friendEditFragment, "NOTE_EDIT_FRAGMENT");
                break;
            case VIEW:
                FriendViewFragment friendViewFragment = new FriendViewFragment();
                setTitle(R.string.view_fragment_title);
                fragmentTransaction.add(R.id.note_container, friendViewFragment, "NOTE_VIEW_FRAGMENT");
                break;
            case CREATE:
                FriendEditFragment noteCreateFragment = new FriendEditFragment();
                setTitle(R.string.create_fragment_title);

                Bundle bundle = new Bundle();
                bundle.putBoolean(NEW_NOTE_EXTRA, true);
                noteCreateFragment.setArguments(bundle);

                fragmentTransaction.add(R.id.note_container, noteCreateFragment, "NOTE_CREATE_FRAGMENT");
                break;
        }



        fragmentTransaction.commit();
    }
}
