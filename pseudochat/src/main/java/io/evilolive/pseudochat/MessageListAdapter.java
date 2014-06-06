package io.evilolive.pseudochat;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Objects;

/**
 * Created by Jonathan Almeida on 2014-06-06.
 */

public class MessageListAdapter extends BaseAdapter {

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

        // Do UI stuff here
    }

}
