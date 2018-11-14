package com.qst.chapter04.activity;

import com.qst.chapter04.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 上下文菜单演示Demo
 *
 */
public class ContextMenuDemoActivity extends AppCompatActivity {
	Button contextMenuBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contextmenu);
		// 显示列表
		contextMenuBtn = (Button) findViewById(R.id.contextMenuBtn);
		// 为按钮注册上下文菜单，长按按钮则弹出上下文菜单
		this.registerForContextMenu(contextMenuBtn);

	}

	// 2）生成上下文菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
									ContextMenuInfo menuInfo) {
		Log.d("ContextMenuDemoActivity", "被创建...");
		menu.setHeaderTitle("文件操作");
		// 为上下文添加菜单项
		menu.add(0, 1, Menu.NONE, "发送");
		menu.add(0, 2, Menu.NONE, "重命名");
		menu.add(0, 3, Menu.NONE, "删除");
	}

	// 3）响应上下文菜单项。
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case 1:
				Toast.makeText(this, "发送...", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(this, "重命名...", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(this, "删除...", Toast.LENGTH_SHORT).show();
				break;
			default:
				return super.onContextItemSelected(item);
		}
		return true;
	}

}
