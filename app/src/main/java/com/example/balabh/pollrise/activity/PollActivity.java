package com.example.balabh.pollrise.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.balabh.pollrise.R;
import com.example.balabh.pollrise.model.Poll;
import com.example.balabh.pollrise.service.DataStoreService;

public class PollActivity extends Activity implements View.OnTouchListener, AppCompatCallback {

    private AppCompatDelegate mDelegate;
    private Toolbar mToolbar;
    private Poll poll;
    private int selection = -1;
    private View previousView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate = AppCompatDelegate.create(this, this);
        mDelegate.onCreate(savedInstanceState);
        mDelegate.setContentView(R.layout.activity_poll);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDelegate.setSupportActionBar(mToolbar);
        mDelegate.getSupportActionBar().setHomeButtonEnabled(true);
        mDelegate.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int index = getIntent().getIntExtra("selected_index", 0);
        poll = DataStoreService.getDataStore().getPoll(index);
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(poll.getQuestion());
        ViewGroup holder = (ViewGroup) findViewById(R.id.holder);
        for (int i=0; i<holder.getChildCount(); i++) {
            holder.getChildAt(i).setOnTouchListener(this);
            ((TextView)holder.getChildAt(i)).setText(poll.getOptions().get(i).getLabel());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_poll, menu);
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
            case R.id.action_submit:
                if(selection == -1) {
                    Toast.makeText(PollActivity.this, "Please select an option",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                poll.getOptions().get(selection).incrementCount();
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void setSelection(View view, boolean reveal, int startX, int startY) {
        if (view == previousView)
            return;
        if(selection!=-1) {
            previousView.setActivated(false);
            ((TextView) previousView).setTextColor(getResources().getColor(R.color.textColorDark));
        }
        switch (view.getId()) {
            case R.id.choice1:
                selection=0;
                break;
            case R.id.choice2:
                selection=1;
                break;
            case R.id.choice3:
                selection=2;
                break;
            case R.id.choice4:
                selection=3;
                break;
        }
        if(reveal)
            ViewAnimationUtils.createCircularReveal(view,
                    startX,
                    startY,
                    0,
                    view.getHeight()).start();
        previousView = view;
        ((TextView) view).setTextColor(getResources().getColor(R.color.textColorPrimary));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(!(view instanceof TextView)) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view.getBackground().setHotspot(motionEvent.getX(), motionEvent.getY());
                break;
            case MotionEvent.ACTION_UP:
                view.setActivated(true);
                setSelection(view, true, (int) motionEvent.getX(), (int) motionEvent.getY());
                break;

        }
        //setSelection(view, true, (int) motionEvent.getX(), (int) motionEvent.getY());
        /*view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        ((TextView) view).setTextColor(getResources().getColor(R.color.textColorPrimary));*/
        return false;
    }
}
