package com.andrewtakao.dollarbettest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendViewFragment extends Fragment {


    public FriendViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_friend_view, container, false);

        TextView title = (TextView) fragmentLayout.findViewById(R.id.view_note_title);
        TextView message = (TextView) fragmentLayout.findViewById(R.id.view_note_message);
        ImageView icon = (ImageView) fragmentLayout.findViewById(R.id.view_note_icon);

        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(HomeActivity.NOTE_TITLE_EXTRA));
        message.setText(intent.getExtras().getString(HomeActivity.NOTE_MESSAGE_EXTRA));

        Friend.Category noteCat = (Friend.Category) intent.getSerializableExtra(HomeActivity.NOTE_CATEGORY_EXTRA);
        icon.setImageResource(Friend.categoryToDrawable(noteCat));

        return fragmentLayout;
    }

}
