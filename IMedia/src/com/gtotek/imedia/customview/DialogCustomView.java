package com.gtotek.imedia.customview;

import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gtotek.imedia.R;


public class DialogCustomView extends AlertDialog.Builder {
	private Context mContext;
	private TextView mTitle;
	private ImageView mIcon;
	private TextView mMessage;
	private DialogCustomView alertNetwork;
	private OnRefreshListener mOnRefreshListener;
	
	public DialogCustomView(Context context) {
		super(context);
		this.mContext = context;
		View customTitle = View.inflate(mContext, R.layout.custom_title_dialog,
				null);
		mTitle = (TextView) customTitle.findViewById(R.id.alertTitle);
		mIcon = (ImageView) customTitle.findViewById(R.id.icon);
		setCustomTitle(customTitle);

		View customMessage = View.inflate(mContext,
				R.layout.alert_message_dialog, null);
		mMessage = (TextView) customMessage.findViewById(R.id.message);
		setView(customMessage);
	}
	
	public void setOnRefreshListener(OnRefreshListener onRefreshListener){
		mOnRefreshListener = onRefreshListener;
	}
	
	@Override
	public DialogCustomView setTitle(int textResId) {
		mTitle.setText(textResId);
		return this;
	}

	@Override
	public DialogCustomView setTitle(CharSequence text) {
		mTitle.setText(text);
		return this;
	}

	@Override
	public DialogCustomView setMessage(int textResId) {
		mMessage.setText(textResId);
		return this;
	}

	@Override
	public DialogCustomView setMessage(CharSequence text) {
		mMessage.setText(text);
		return this;
	}

	@Override
	public DialogCustomView setIcon(int drawableResId) {
		mIcon.setImageResource(drawableResId);
		return this;
	}

	@Override
	public DialogCustomView setIcon(Drawable icon) {
		mIcon.setImageDrawable(icon);
		return this;
	}

	public void ShowDialog(String title, String message, String buttonPosi,
			String buttonNega) {
		alertNetwork = new DialogCustomView(mContext);
		alertNetwork.setTitle(title);
		alertNetwork.setMessage(message);
		alertNetwork
				.setCancelable(false)
				.setPositiveButton(buttonPosi,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ButtonPositive();
							}
						})
				.setNegativeButton(buttonNega,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ButtonNegative();
							}

						});
		alertNetwork.show();
	}

	public void ButtonPositive() {
		Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
		mContext.startActivity(intent);
		
		mOnRefreshListener.refreshData();
	}

	public void ButtonNegative() {
		mOnRefreshListener.refreshData();
	}
	
	
	public static interface OnRefreshListener{
		public void refreshData();
	}

}
