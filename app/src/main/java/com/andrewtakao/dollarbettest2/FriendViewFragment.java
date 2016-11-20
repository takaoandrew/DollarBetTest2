package com.andrewtakao.dollarbettest2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendViewFragment extends Fragment {


    public FriendViewFragment() {
        // Required empty public constructor
    }

    private String[] data1 = {"Hiren", "Pratik", "Dhruv"};
    private String[] data2 = {"Kirit", "Miral", "Bhushan"};
    private String[] data3 = {"Narendra", "Piyush", "Priyank"};
    private String[] data4 = {"Jiten", "Ajay", "Kamlesh"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_friend_view, container, false);

        TextView contactName = (TextView) fragmentLayout.findViewById(R.id.contact_name_view);
        TextView message = (TextView) fragmentLayout.findViewById(R.id.view_note_message);
        ImageView icon = (ImageView) fragmentLayout.findViewById(R.id.contact_image_view);
        ListView betsRequested = (ListView) fragmentLayout.findViewById(R.id.bets_requested_list_view);
        ListView betsPending = (ListView) fragmentLayout.findViewById(R.id.bets_pending_list_view);
        ListView betsWon = (ListView) fragmentLayout.findViewById(R.id.bets_won_list_view);
        ListView betsLost = (ListView) fragmentLayout.findViewById(R.id.bets_lost_list_view);

        betsRequested.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data1));
        betsPending.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data2));
        betsWon.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data3));
        betsLost.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, data4));

        ListUtils.setDynamicHeight(betsRequested);
        ListUtils.setDynamicHeight(betsPending);
        ListUtils.setDynamicHeight(betsWon);
        ListUtils.setDynamicHeight(betsLost);

        Intent intent = getActivity().getIntent();
        contactName.setText(intent.getExtras().getString(FriendsActivity.FRIEND_NAME_EXTRA));
        message.setText(intent.getExtras().getString(FriendsActivity.FRIEND_BET_REQUESTED_EXTRA));

        Friend.Category noteCat = (Friend.Category) intent.getSerializableExtra(FriendsActivity.FRIEND_IMAGE_EXTRA);
        icon.setImageResource(Friend.categoryToDrawable(noteCat));

        return fragmentLayout;
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                    View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = height + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
            listView.requestLayout();
        }
    }

}
