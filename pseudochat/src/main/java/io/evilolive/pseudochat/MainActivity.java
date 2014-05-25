package io.evilolive.pseudochat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.lang.*;

public class MainActivity extends ActionBarActivity {
    private static final String MAIN_ACTIVITY = "MainActivity";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
//    private CharSequence mTitle;

    private LocationHandler myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTitle = getTitle();

        myLocation = new LocationHandler(this);
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
                    0
            );

            something.send(my_message);
        }
    }
}
