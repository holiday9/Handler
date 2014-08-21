package com.htyuan.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
	public static final int MSG_DOWNLOAD_FINISH=0X001;
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		HandlerThread thread=new HandlerThread("yzy");  
		thread.start();
		
		mHandler2 = new Handler(thread.getLooper())  
	    {  
	      public void handleMessage(Message msg) {
	        switch(msg.what)  
	        {  
	          case MSG_DOWNLOAD_FINISH:  
	            Log.v("yzy", "handler所在的线程Name是-->"+Thread.currentThread().getName());  
	            break;  
	        }  
	          
	      };  
	    };  
	}
	
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_DOWNLOAD_FINISH:
				Log.v("yzy", "handler所在的线程id是-->"
						+ Thread.currentThread().getName());
				break;
			}
		}
	};

	private Handler mHandler2;
	
	
	
	public void download(View view)  
	  {  
	    new Thread()  
	    {  
	      public void run() {  
	        try  
	        {  
	          Log.v("yzy", "下载子线程的Name是--->"+Thread.currentThread().getName());  
	          //在子线程运行，模拟一个下载任务  
	          Thread.sleep(2000);  
	          //下载完成后，发送下载完成消息  
	          mHandler2.sendEmptyMessage(MSG_DOWNLOAD_FINISH);
	        } catch (InterruptedException e)  
	        {  
	          e.printStackTrace();  
	        }  
	      };  
	    }.start();  
	  } 

	@Override
	public void onClick(View v) {
		download(v);
	}

}
