/**
 * 
 */
package com.example.edittextdemo;

import android.content.Context;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * @author jiashuai.xujs@alibaba-inc.com 2014-2-25 下午5:06:14
 * 
 */
public class FrontPartEditableText extends LinearLayout {

	private Button button;
	private int frontPartLength;
	private BackDetectableEditText edittext;
	private BuildTextContentListener listener;

	public FrontPartEditableText(Context context) {
		super(context);
		init();
	}

	public FrontPartEditableText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FrontPartEditableText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		final LayoutInflater mLayoutInflater = LayoutInflater.from(getContext());
		View v = mLayoutInflater.inflate(R.layout.front_part_editable_text, null,false);
		addView(v);
		
		edittext = (BackDetectableEditText) v.findViewById(R.id.part_editable_text);
		// 限定只能输入数字
		edittext.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		// 可以获取焦点
		button = (Button) v.findViewById(R.id.part_editable_text_dummy);
		button.setFocusable(true);
		button.setFocusableInTouchMode(true);
		// http://stackoverflow.com/questions/3425932/detecting-when-user-has-dismissed-the-soft-keyboard
		// 默认情况下，按返回会隐藏软键盘，拦截这个事件手动隐藏
		edittext.setOnEditTextImeBackListener(new BackDetectableEditText.EditTextImeBackListener() {
			@Override
			public void onImeBack(BackDetectableEditText ctrl, String text) {
				hideSoftInput();
			}
		});

		// 一旦获取焦点，设置光标位置
		edittext.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					String mobile = getFrontPart(edittext.getText().toString());
					setCursorPosition(mobile.length());
				}
			}
		});

		// 返回true，手动处理touch事件，即使edittext获取了焦点，也不会自动弹出软键盘，要手动弹出
		// http://stackoverflow.com/questions/10263384/android-how-to-get-text-position-from-touch-event
		edittext.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					Layout layout = ((EditText) v).getLayout();
					float x = event.getX() + edittext.getScrollX();
					int offset = layout.getOffsetForHorizontal(0, x);
					if (offset >= 0 && offset < frontPartLength) {
						edittext.setSelection(offset);
					} else if (offset >= frontPartLength) {
						edittext.setSelection(frontPartLength);
					}
					showSoftInput();
				}
				return true;
			}
		});

		edittext.addTextChangedListener(new TextWatcher() {
			private String preText;

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if(listener == null){
					throw new RuntimeException("BuildTextContentListener can not be empty");
				}
				String nowtext = listener.buildTextContent(s.toString());
				if (nowtext.equals(preText)) {
					return;
				}
				String frontPart = getFrontPart(nowtext);
				// 计算当前的光标位置
				int offset = calCursorOffset(preText, nowtext);
				// 一定要在setTest之前设置preText，否则会StackOverflow
				preText = nowtext;
				edittext.setText(nowtext);
				// 文字发生变化，重新设置光标，否则会跑到最前面
				setCursorPosition(offset);
				if (frontPart.length() == frontPartLength) {
					hideSoftInput();
				}
			}
		});
	}
	
	public void setBuildTextContentListener(BuildTextContentListener listener){
		this.listener = listener;
	}
	
	public interface BuildTextContentListener{
		public String buildTextContent(String text);
	}
	
	public void setFrontPartLength(int frontPartLength) {
		this.frontPartLength = frontPartLength;
	}

	public void hideSoftInput() {
		edittext.requestFocus();
		Util.hideKeyboard(edittext);
		button.requestFocus();
	}

	public void showSoftInput() {
		edittext.requestFocus();
		Util.showKeyboard(edittext);
	}
	
	private String getFrontPart(String text) {
		if (text == null || text.length() <= 0) {
			return "";
		}
		String arr[] = text.split("\\s");
		String mobile = arr[0];
		return mobile;
	}

	private void setCursorPosition(int offset) {
		edittext.setSelection(offset);
	}
	
	private int calCursorOffset(String pre, String now) {
		if (Util.isBlank(pre) && Util.isBlank(now)) {
			return 0;
		} else if (!Util.isBlank(pre) && !Util.isBlank(now)) {
			for (int i = 0; i < pre.length() && i < now.length(); i++) {
				int prechar = pre.charAt(i);
				int nowchar = now.charAt(i);
				if (prechar != nowchar) {
					return i;
				}
			}
		}
		return now.length() > frontPartLength ? frontPartLength : now.length();
	}
}
