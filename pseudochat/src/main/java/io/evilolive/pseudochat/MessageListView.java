package io.evilolive.pseudochat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Jonathan Almeida on 2014-06-09.
 */
public class MessageListView extends Activity {
    private static final String MSG_LIST_VIEW = "MessageListView";

    // Initialize the array
    String[] monthsArray = { "JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY",
            "AUG", "SEPT", "OCT", "NOV", "DEC" };

    // Declare the UI components
    private ListView monthsListView;

    private ArrayAdapter arrayAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the UI components
        monthsListView = (ListView) findViewById(R.id.listView_responses);
        // For this moment, you have ListView where you can display a list.
        // But how can we put this data set to the list?
        // This is where you need an Adapter

        // context - The current context.
        // resource - The resource ID for a layout file containing a layout
        // to use when instantiating views.
        // From the third parameter, you plugged the data set to adapter
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, monthsArray);

        // By using setAdapter method, you plugged the ListView with adapter
        monthsListView.setAdapter(arrayAdapter);
    }

}