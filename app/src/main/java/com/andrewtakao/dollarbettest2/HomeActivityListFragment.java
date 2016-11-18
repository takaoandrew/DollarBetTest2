package com.andrewtakao.dollarbettest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
public class HomeActivityListFragment extends ListFragment {
    private ArrayList<Friend> friends;
    private FriendAdapter friendAdapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        /*
        friends = new ArrayList<Friend>();
        friends.add(new Friend("This is a new note title, and an extroardinarily long one at that, would" +
                "you not agree my fine friend","This is the body of our note This is the body of our" +
                " noteThis is the body of our noteThis is the body of our noteThis is the body of " +
                "our note",Friend.Category.PERSONAL));
        friends.add(new Friend("Testin","123",Friend.Category.QUOTE));
        friends.add(new Friend("Thi title","This is aeou,.u our note",Friend.Category.FINANCE));
        */

        DollarBetDbAdapter dbAdapter = new DollarBetDbAdapter(getActivity().getBaseContext());
        dbAdapter.open();
        friends = dbAdapter.getAllNotes();
        dbAdapter.close();

        friendAdapter = new FriendAdapter(getActivity(), friends);
        setListAdapter(friendAdapter);

        getListView().setDivider(ContextCompat.getDrawable(getActivity(),android.R.color.black));
        getListView().setDividerHeight(30);

        registerForContextMenu(getListView());
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l,v,position,id);
        launchNoteDetailActivity(HomeActivity.FragmentToLaunch.VIEW, position);
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
                launchNoteDetailActivity(HomeActivity.FragmentToLaunch.EDIT, rowPosition);
                Log.d("Menu Clicks", "We pressed edit!");
                return true;

            case R.id.delete:
                DollarBetDbAdapter dbAdapter = new DollarBetDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteNote(friend.getNoteId());

                friends.clear();
                friends.addAll(dbAdapter.getAllNotes());
                friendAdapter.notifyDataSetChanged();

                dbAdapter.close();

        }
        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(HomeActivity.FragmentToLaunch ftl, int position){
        Friend friend = (Friend) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(),FriendDetailActivity.class);
        intent.putExtra(HomeActivity.NOTE_CATEGORY_EXTRA, friend.getCategory());
        intent.putExtra(HomeActivity.NOTE_TITLE_EXTRA, friend.getTitle());
        intent.putExtra(HomeActivity.NOTE_ID_EXTRA, friend.getNoteId());
        intent.putExtra(HomeActivity.NOTE_MESSAGE_EXTRA, friend.getMessage());

        switch (ftl){
            case VIEW:
                intent.putExtra(HomeActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, HomeActivity.FragmentToLaunch.VIEW);
                break;
            case EDIT:
                intent.putExtra(HomeActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, HomeActivity.FragmentToLaunch.EDIT);
                break;
        }
        startActivity(intent);

    }


}
