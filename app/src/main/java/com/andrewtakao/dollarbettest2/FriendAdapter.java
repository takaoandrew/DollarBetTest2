package com.andrewtakao.dollarbettest2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andrewtakao on 10/27/16.
 */

public class FriendAdapter extends ArrayAdapter<Friend> {
    public static class ViewHolder{
        TextView title;
        TextView message;
        ImageView noteIcon;
    }
    public FriendAdapter(Context context, ArrayList<Friend> friends){
        super(context, 0, friends);
    }

    ViewHolder viewHolder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Friend friend = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_note_title);
            viewHolder.message = (TextView) convertView.findViewById(R.id.list_item_note_body);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.list_item_note_image);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(friend.getTitle());
        viewHolder.message.setText(friend.getMessage());
        viewHolder.noteIcon.setImageResource(friend.getAssociatedDrawable());

        return convertView;
    }
}
