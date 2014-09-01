package com.htyuan.handlertest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{
	public static final int MSG_DOWNLOAD_FINISH=0X001;
	LooperThread mMyThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mHandler.sendEmptyMessageDelayed(1, 3000);
		mMyThread = new LooperThread("htyuan");
		mMyThread.start();
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_DOWNLOAD_FINISH:
				Log.v("htyuan", "handler所在的线程id是-->"
						+ Thread.currentThread().getName());
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		Log.v("htyuan", "onClick所在的线程id是-->"
				+ Thread.currentThread().getName());
		
		mMyThread.mHandler.sendEmptyMessage(1);
	}

}
