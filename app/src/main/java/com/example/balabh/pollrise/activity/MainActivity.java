package com.example.balabh.pollrise.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.balabh.pollrise.R;
import com.example.balabh.pollrise.adapter.PollViewAdapter;
import com.example.balabh.pollrise.model.Poll;
import com.example.balabh.pollrise.model.PollOption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity implements AppCompatCallback {

    private Toolbar mToolbar;
    private AppCompatDelegate delegate;
    private ImageButton FAB;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_main);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        delegate.setSupportActionBar(mToolbar);
        FAB = (ImageButton)findViewById(R.id.addPoll);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "add new poll !!", Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(getApplicationContext(), NewPollActivity.class);
                startActivity(i);
            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.polls_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PollViewAdapter(getPolls());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /*for(int i=0;i<menu.size();i++) {
            if(menu.getItem(i).getItemId() != R.id.action_settings)
                menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }*/
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

    @Override
    protected void onResume() {
        super.onResume();
        ((PollViewAdapter)mAdapter).setClickListener(new
            PollViewAdapter.ClickListener() {
                @Override
                public void onClick(int position, View v) {
                    Toast.makeText(MainActivity.this, "item clicked", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private List<Poll> getPolls() {
        String[] questions = new String[] {"Your favourite cricket player."
                , "your favourite Cuisine.", "which is the best place to visit."};
        String[] options = new String[] {"Sachin", "Dravid", "Dhoni", "Kohli", "Andhra", "Italian"
                , "Mexican", "Chinese", "Vietnam", "United States", "India", "Japan"};
        List<Poll> polls = new ArrayList<Poll>();
        for (int i=0;i<20; i++) {
            Poll poll = new Poll(questions[(int)(Math.random()*questions.length)], new Date());
            for(int j=0;j<4;j++) {
                PollOption option = new PollOption(options[(int)(Math.random()*options.length)]);
                poll.addOption(option);
            }
            polls.add(poll);
        }
        return polls;
    }
}
