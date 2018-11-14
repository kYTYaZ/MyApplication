/**
 * 
 */
package com.example.edittextdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class BackDetectableEditText extends EditText {

	public BackDetectableEditText(Context context) {
		super(context);
	}

	public BackDetectableEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BackDetectableEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	private EditTextImeBackListener mOnImeBack;
	
	@Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            if (mOnImeBack != null){
            	mOnImeBack.onImeBack(this, this.getText().toString());
            	return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener listener) {
        mOnImeBack = listener;
    }
	
	public interface EditTextImeBackListener {
	    public abstract void onImeBack(BackDetectableEditText ctrl, String text);
	}

}
