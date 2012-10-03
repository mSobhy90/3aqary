package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

public class AaqarySplashScreen extends Activity {
	private int mProgressStatus = 0;
	private ProgressBar pg;
	private AaqarySplashScreen th;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		pg = (ProgressBar) findViewById(R.id.progressBar1);
		th = this;
		new Thread(myThread).start();
	}

	private Runnable myThread = new Runnable() {
		@Override
		public void run() {
			while (mProgressStatus < 100) {
				try {
					myHandle.sendMessage(myHandle.obtainMessage());
					Thread.sleep(1000);
				} catch (Throwable t) {
				}
			}
			if (mProgressStatus >= 100) {
				th.finish();
				startActivity(new Intent(th, AaqaryActivity.class));
			}
		}

		Handler myHandle = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				mProgressStatus += 15;
				pg.setProgress(mProgressStatus);
			}
		};
	};
}
