package io.evilolive.pseudochat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

/**
 * Created by Jonathan Almeida on 2014-06-06.
 */


public class MessageListAdapter extends BaseAdapter {

    private static final String MSG_LIST_ADAPTER = "MessageListAdapter";

    @Override
    public int getCount() {
        return MessageList.getInstance().size();
    }

    @Override
    public Object getItem(int i) {
        return MessageList.getInstance().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            // Change abc_list_menu_item_checkbox to abc_list_menu_item
            view = inflater.inflate(R.layout.abc_list_menu_item_checkbox, parent, false);
        }

        final Message message = MessageList.getInstance().get(index);

        Button button = (Button) view.findViewById(R.id.button_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Log.v(MSG_LIST_ADAPTER, "msg: " + message.getMsgText());
            }
        });

        return view;
    }

}
