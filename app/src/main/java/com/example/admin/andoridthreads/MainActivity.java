package com.example.admin.andoridthreads;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    Button btnThread,btnRunnable,btnAsyncTask;
    TextView tvText,tvText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Threads...");
        btnThread = (Button) findViewById(R.id.btnThread);
        btnRunnable = (Button) findViewById(R.id.btnRunnable);
        btnAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
        tvText = (TextView) findViewById(R.id.tvText);
        tvText2 = (TextView) findViewById(R.id.tvText2);
    }

    public void executeThreads(View view) {
        switch (view.getId()){
            case R.id.btnThread:
                TestThread testThread = new TestThread(tvText);
                testThread.start();
                break;
            case R.id.btnRunnable:
                TestRunnable testRunnable = new TestRunnable(tvText);
                Thread thread = new Thread((testRunnable));
                thread.start();
                break;
            case R.id.btnAsyncTask:
                TestAsynctask testAsynctask = new TestAsynctask(tvText2);
                testAsynctask.execute("String");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent( MessageEvent messageEvent){
        Toast.makeText(this, messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
