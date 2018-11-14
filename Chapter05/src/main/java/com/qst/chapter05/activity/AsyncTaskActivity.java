package com.qst.chapter05.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qst.chapter05.R;

/**
 * Created by Adminstrator on 2016/11/17.
 */

public class AsyncTaskActivity extends AppCompatActivity{
	Button download;
	ProgressBar pb;
	TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_layout);
		pb=(ProgressBar)findViewById(R.id.pb);
		tv=(TextView)findViewById(R.id.tv);

		download = (Button)findViewById(R.id.download);
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DownloadTask dTask = new DownloadTask();
				dTask.execute(100);
			}
		});
	}

	private class DownloadTask extends AsyncTask<Integer, Integer, String> {

		@Override
		protected void onPreExecute() {
			//第一个执行方法
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(Integer... params) {
			//第二个执行方法,onPreExecute()执行完后执行
			for(int i=0;i<=100;i++){
				publishProgress(i);
				try {
					Thread.sleep(params[0]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return "执行完毕";
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			pb.setProgress(progress[0]);
			tv.setText(progress[0]+"%");
			super.onProgressUpdate(progress);
		}

		@Override
		protected void onPostExecute(String result) {
			setTitle(result);
			super.onPostExecute(result);
		}

	}
}
