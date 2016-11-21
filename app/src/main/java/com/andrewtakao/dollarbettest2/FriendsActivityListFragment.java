package com.andrewtakao.dollarbettest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsActivityListFragment extends ListFragment {

    private ArrayList<Friend> friends;
    private FriendAdapter friendAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        FriendDbAdapter dbAdapter = new FriendDbAdapter(getActivity().getBaseContext());
        dbAdapter.open();
        friends = dbAdapter.getAllFriends();
        dbAdapter.close();

        friendAdapter = new FriendAdapter(getActivity(), friends);
        setListAdapter(friendAdapter);

        getListView().setDivider(ContextCompat.getDrawable(getActivity(),android.R.color.black));
        getListView().setDividerHeight(10);

        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        Intent intent = new Intent(getActivity(), BetsActivity.class);
        Friend friend = (Friend) getListAdapter().getItem(position);
        intent.putExtra(FriendsActivity.FRIEND_IMAGE_EXTRA, friend.getCategory());
        intent.putExtra(FriendsActivity.FRIEND_NAME_EXTRA, friend.getName());
        intent.putExtra(FriendsActivity.FRIEND_ID_EXTRA, friend.getFriendId());
        intent.putExtra(FriendsActivity.FRIEND_BET_REQUESTED_EXTRA, friend.getRequestedBet());
        startActivity(intent);

        //launchFriendDetailActivity(FriendsActivity.FragmentToLaunch.VIEW, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;
        Friend friend = (Friend) getListAdapter().getItem(rowPosition);

        switch (item.getItemId()){

            case R.id.edit:
                launchFriendDetailActivity(FriendsActivity.FragmentToLaunch.EDIT, rowPosition);
                return true;

            case R.id.delete:
                FriendDbAdapter dbAdapter = new FriendDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteNote(friend.getFriendId());

                friends.clear();
                friends.addAll(dbAdapter.getAllFriends());
                friendAdapter.notifyDataSetChanged();

                dbAdapter.close();

        }
        return super.onContextItemSelected(item);
    }

    private void launchFriendDetailActivity(FriendsActivity.FragmentToLaunch ftl, int position) {
        Friend friend = (Friend) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(),FriendDetailActivity.class);
        intent.putExtra(FriendsActivity.FRIEND_IMAGE_EXTRA, friend.getCategory());
        intent.putExtra(FriendsActivity.FRIEND_NAME_EXTRA, friend.getName());
        intent.putExtra(FriendsActivity.FRIEND_ID_EXTRA, friend.getFriendId());
        intent.putExtra(FriendsActivity.FRIEND_BET_REQUESTED_EXTRA, friend.getRequestedBet());

        switch (ftl){
            case VIEW:
                intent.putExtra(FriendsActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, FriendsActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(FriendsActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, FriendsActivity.FragmentToLaunch.EDIT);
                break;
        }
        startActivity(intent);


    }


}
