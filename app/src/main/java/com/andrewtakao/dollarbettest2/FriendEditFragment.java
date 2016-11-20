package com.andrewtakao.dollarbettest2;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendEditFragment extends Fragment {
    private ImageButton noteCatButton;
    private EditText title, message;
    private Friend.Category savedButtonCategory;
    private AlertDialog categoryDialogObject, confirmDialogObject;

    private static final String MODIFIED_CATEGORY = "Modified Category";

    private boolean newNote = false;
    private long noteId = 0;


    public FriendEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null){
            newNote = bundle.getBoolean(FriendDetailActivity.NEW_NOTE_EXTRA, false);
        }
        if (savedInstanceState!=null) {
            savedButtonCategory = (Friend.Category) savedInstanceState.get(MODIFIED_CATEGORY);
        }
        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_friend_edit, container, false);

        title = (EditText) fragmentLayout.findViewById(R.id.edit_note_title);
        message = (EditText) fragmentLayout.findViewById(R.id.edit_note_message);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.edit_note_button);
        Button savedButton = (Button) fragmentLayout.findViewById(R.id.save_note);

        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(FriendsActivity.FRIEND_NAME_EXTRA, ""));
        message.setText(intent.getExtras().getString(FriendsActivity.FRIEND_BET_REQUESTED_EXTRA, ""));
        noteId = intent.getExtras().getLong(FriendsActivity.FRIEND_ID_EXTRA, 0);

        if (savedButtonCategory != null) {
            noteCatButton.setImageResource(Friend.categoryToDrawable(savedButtonCategory));
        }
        else if (!newNote){
            Friend.Category noteCat = (Friend.Category) intent.getSerializableExtra(FriendsActivity.FRIEND_IMAGE_EXTRA);
            savedButtonCategory = noteCat;
            noteCatButton.setImageResource(Friend.categoryToDrawable(noteCat));
        }

        buildCategoryDialogue();
        buildConfirmDialog();

        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialogObject.show();
            }
        });

        savedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogObject.show();
            }
        });

        return fragmentLayout ;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(MODIFIED_CATEGORY, savedButtonCategory);
    }

    private void buildCategoryDialogue(){
        final String[] categories = new String[] {"Personal","Technical","Quote","Finance"};
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle("Choose Friend Type");
        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                categoryDialogObject.cancel();
                switch (item){
                    case 0:
                        savedButtonCategory = Friend.Category.PERSONAL;
                        noteCatButton.setImageResource(R.drawable.p);
                        break;
                    case 1:
                        savedButtonCategory = Friend.Category.TECHNICAL;
                        noteCatButton.setImageResource(R.drawable.t);
                        break;
                    case 2:
                        savedButtonCategory = Friend.Category.QUOTE;
                        noteCatButton.setImageResource(R.drawable.q);
                        break;
                    case 3:
                        savedButtonCategory = Friend.Category.FINANCE;
                        noteCatButton.setImageResource(R.drawable.f);
                        break;

                }
            }
        });
        categoryDialogObject = categoryBuilder.create();
    }
    private void buildConfirmDialog(){
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle("Are you sure?");
        confirmBuilder.setMessage("Are you sure you want to save the message?");
        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save Friend","Friend Title: " + title.getText() + "Friend Message: " + message.getText() +
                "Friend category: " + savedButtonCategory);

                FriendDbAdapter dbAdapter = new FriendDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();

                if (newNote) {
                    dbAdapter.createNote(title.getText() + "", message.getText() + "",
                            (savedButtonCategory == null)? Friend.Category.PERSONAL : savedButtonCategory);

                }
                else {
                    dbAdapter.updateFriend(noteId, title.getText() + "", message.getText() + "", savedButtonCategory);

                }

                dbAdapter.close();
                Intent intent = new Intent(getActivity(), FriendsActivity.class);
                startActivity(intent);
            }
        });
        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing
            }
        });
        confirmDialogObject = confirmBuilder.create();
    }

}
