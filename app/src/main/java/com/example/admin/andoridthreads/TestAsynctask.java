package com.example.admin.andoridthreads;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Admin on 8/8/2017.
 */

public class TestAsynctask extends AsyncTask<String, Integer,String> {
    private static final String TAG = "AsyncTask";
    TextView tvText2;

    public TestAsynctask(TextView tvText2) {
        this.tvText2 = tvText2;
    }
    String result;
    int i;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread());
    }
    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + strings[0] +  Thread.currentThread());
        result = "Backgound progress: ";
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i);
            }catch(Exception ex){}
            publishProgress(i);

        }
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tvText2.setText(result+values[i]);
        EventBus.getDefault().post(new MessageEvent(String.valueOf(result+values[i])));
        Log.d(TAG, "onProgressUpdate: "+ values[i] + Thread.currentThread());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.d(TAG, "onPostExecute: "+ s + Thread.currentThread());
    }


}
