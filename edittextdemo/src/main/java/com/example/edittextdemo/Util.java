/**
 * 
 */
package com.example.edittextdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Util {
	
	public static void showKeyboard(EditText edittext) {
		if (edittext != null) {
			Object showing = edittext.getTag();
			if (showing != null) {
				return;
			}
		}
		try {
			InputMethodManager imm = (InputMethodManager) edittext.getContext()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(edittext, 0);
			edittext.setTag(new Object());
		} catch (Exception e) {
			Log.e("SoftInput:Showing had a wrong.", e.toString());
		}
	}

	public static void hideKeyboard(EditText edittext) {
		if (edittext == null || edittext.getTag() == null) {
			return;
		}
		try {
			InputMethodManager imm = ((InputMethodManager) edittext
					.getContext().getSystemService(
							Activity.INPUT_METHOD_SERVICE));
			imm.hideSoftInputFromWindow(edittext.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
			edittext.setTag(null);
		} catch (Exception e) {
			Log.e("SoftInput:Hiding had a wrong.", e.toString());
		}
	}

	public static boolean isBlank(String str) {
		if (str == null || str.length() <= 0) {
			return true;
		}
		return false;
	}

}
