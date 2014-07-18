package io.evilolive.pseudochat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.*;
import java.util.Timer;

public class MainActivity extends ActionBarActivity {
    private static final String MAIN_ACTIVITY = "MainActivity";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */

    private LocationHandler myLocation;
    private Timer timer;
    private ListView messageListView;
    private MessageListAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLocation = new LocationHandler(this);
        timer = new Timer();
        timer.scheduleAtFixedRate(new LocationUpdater(myLocation), 0, 60000);
        messageListView = (ListView) findViewById(R.id.listView_responses);

        String[] monthsArray = { "JAN", "FEB", "MAR", "APR", "MAY", "JUNE", "JULY",
                "AUG", "SEPT", "OCT", "NOV", "DEC" };

        arrayAdapter = new MessageListAdapter(this, android.R.layout.simple_list_item_1, MessageList.getInstance());

        messageListView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        myLocation.removeUpdates();
        super.onPause();
    }

    @Override
    protected void onResume() {
        myLocation.resumeUpdates();
        super.onResume();
    }

    public void sendMessage(View view) {
        Log.v(MAIN_ACTIVITY, "button pressed.");
        // FOR TESTING ONLY
        {
            EditText msg = (EditText) findViewById(R.id.editText_msg);
            EditText nick = (EditText) findViewById(R.id.editText_nick);
            MessageHandler something = new MessageHandler();
            if(myLocation == null) {
                Log.v(MAIN_ACTIVITY, "handler is null.");
                return;
            }
            Message my_message = new Message(
                    msg.getText().toString(),
                    nick.getText().toString(),
                    myLocation.getLocation().getLatitude(),
                    myLocation.getLocation().getLongitude(),
                    ResponseHandler.getInstance().getLastTimestamp()
            );

            something.send(my_message);
        }
    }
}
