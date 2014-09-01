package com.htyuan.handlertest;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread {
	public Handler mHandler;
	
	public LooperThread(String name) {
		setName(name);
	}
    
    public void run() {
        Looper.prepare();
        
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.S");
            	String time = sdf.format(new Date());
            	Log.d("temp", "current Thread name : " + Thread.currentThread().getName() + ", " + time);
            }
        };
        
        Log.d("temp", "before next Looper.loop()");
        Looper.loop();
        Log.d("temp", "after next Looper.loop()");
    }
}
