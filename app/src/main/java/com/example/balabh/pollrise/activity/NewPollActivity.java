package com.example.balabh.pollrise.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.balabh.pollrise.R;
import com.example.balabh.pollrise.model.Poll;
import com.example.balabh.pollrise.model.PollOption;
import com.example.balabh.pollrise.service.DataStoreService;

import java.util.Date;


public class NewPollActivity extends Activity implements AppCompatCallback {

    private Toolbar mToolbar;
    private AppCompatDelegate delegate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = AppCompatDelegate.create(this,this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_new_poll);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        delegate.setSupportActionBar(mToolbar);
        delegate.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        delegate.getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_poll, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings :
                return true;
            case R.id.action_addpoll :
                Poll poll = new Poll((String)((TextView)findViewById(R.id.question)).getText(), new Date());
                LinearLayout choices = (LinearLayout) findViewById(R.id.action_choices);
                for (int i=0; i<choices.getChildCount();i++) {
                    EditText et = (EditText)choices.getChildAt(i);
                    PollOption pollOption = new PollOption(et.getText().toString());
                    poll.addOption(pollOption);
                }
                DataStoreService.getDataStore().addPole(poll);
                Toast.makeText(NewPollActivity.this, "Added new poll", Toast.LENGTH_SHORT).show();
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.action_add_choice :
                LinearLayout l = (LinearLayout)findViewById(R.id.action_choices);
                EditText e = new EditText(NewPollActivity.this);
                e.setHint("Choice");
                e.setPadding(0, 0, 0, R.dimen.activity_choice_padding);
                e.setTextSize(R.dimen.activity_choice_text_size);
                //e.setTextColor(getResources().getColor(R.color.textColorDark));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                //params.bottomMargin = 100;
                e.setLayoutParams(params);
                e.setSingleLine(true);
                l.addView(e);
                break;*/
            default:
                return;
        }
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
