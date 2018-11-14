package com.example.edittextdemo;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private FrontPartEditableText edittext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final View rootView = this.findViewById(R.id.rootview);
		edittext = (FrontPartEditableText) this.findViewById(R.id.edittext1);
		edittext.setFrontPartLength(11);
		edittext.setBuildTextContentListener(new FrontPartEditableText.BuildTextContentListener(){
			@Override
			public String buildTextContent(String text) {
				if(text == null){
					return "";
				}
				String arr[] = text.split("\\s+");
				String mobile = arr[0];
				String name = getName(mobile);
				return mobile + (name == null ? "" : " " + name);
			}
		});
		// 空白处点击，隐藏软键盘
		rootView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				edittext.hideSoftInput();
			}
		});
	}

	// 业务方法，联系人数据
	private Map<String, String> data;

	private String getName(String mobile) {
		if (data == null) {
			data = contactData();
		}
		return data.get(mobile);
	}

	private Map<String, String> contactData() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("15012341234", "张三");
		data.put("15112341234", "李四");
		data.put("15212341234", "王五");
		return data;
	}
}
