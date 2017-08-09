package com.example.admin.andoridthreads;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Admin on 8/8/2017.
 */
public class TestRunnable implements Runnable {
    Handler handler = new Handler(Looper.getMainLooper());
    TextView tvText;

    public TestRunnable(TextView tvText) {
        this.tvText = tvText;
    }
    int i;
    @Override
    public void run() {
        System.out.println(Thread.currentThread());
        for (i = 0;i < 10; i++){
            try{
                Thread.sleep(1000);
                System.out.println(i);
            }catch (Exception e){
                e.printStackTrace();
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvText.setText(String.valueOf(i));
                }
            },2000);
            EventBus.getDefault().post(new MessageEvent(String.valueOf(i)));
        }
    }
}
