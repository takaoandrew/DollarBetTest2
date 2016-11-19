package com.andrewtakao.dollarbettest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrewtakao on 11/19/16.
 */

public class BetAdapter extends ArrayAdapter<Bet> {

    public static class ViewHolder {
        TextView betName;
    }

    public BetAdapter(Context context, ArrayList<Bet> bets) {
        super(context, 0, bets);
    }

    ViewHolder viewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bet bet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bet_list_row, parent, false);
            viewHolder = new BetAdapter.ViewHolder();
            viewHolder.betName = (TextView) convertView.findViewById(R.id.bet_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.betName.setText(bet.getBet());

        return convertView;

    }
}
