package com.qst.chapter04.activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;




public class ListVewSimpleDemoActivity extends ListActivity {
	//数据源列表
	private String[] mListStr = { "姓名：张三", "性别：男", "年龄：25", "居住地：青岛",
			"邮箱：itshixun@gmail.com" };
	ListView mListView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mListView =getListView();
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListStr));
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				Toast.makeText(ListVewSimpleDemoActivity.this,
						"您选择了" + mListStr[position], Toast.LENGTH_LONG).show();
			}
		});
		//设置ListView作为显示
		super.onCreate(savedInstanceState);
	}
}
