package com.example.admin.andoridthreads;

import android.os.Looper;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Admin on 8/8/2017.
 */
public class TestThread extends Thread {
    android.os.Handler handler = new android.os.Handler(Looper.getMainLooper());
    TextView tvText;

    public TestThread(TextView tvText) {
        this.tvText = tvText;
    }

    int i;

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread());
        for (i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvText.setText(String.valueOf(i));
                }
            });
            EventBus.getDefault().post(new MessageEvent(String.valueOf(i)));
        }

    }
}
