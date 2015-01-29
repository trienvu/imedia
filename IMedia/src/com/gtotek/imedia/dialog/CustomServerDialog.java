package com.gtotek.imedia.dialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtotek.imedia.R;
import com.gtotek.imedia.activity.PlayActivity;
import com.gtotek.imedia.apdater.ServerAdapter;
import com.gtotek.imedia.constant.Constant;
import com.gtotek.imedia.entry.Channel;
import com.gtotek.imedia.services.HTTPClientService;

public class CustomServerDialog extends Dialog {
	private Context mContext;
	private ListView mLvServer;
	private String[] mArrServerName;
	private String[] mArrServerUrl;
	private ImageView mImvChannelIcon;
	public TextView mTvChannelDesc;

	private ProgressDialog mPrgChanelDialog;
	private Channel mChannel;
	private AQuery mAQuery;

	public CustomServerDialog(Context context, Channel channel) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		setContentView(R.layout.custom_server_dialog);

		this.mContext = context;
		this.mAQuery = new AQuery(context);
		this.mLvServer = (ListView) this.findViewById(R.id.lvServer);
		this.mImvChannelIcon = (ImageView) this
				.findViewById(R.id.imvChannelIcon);
		this.mTvChannelDesc = (TextView) this.findViewById(R.id.tvChannelDesc);
		this.mChannel = channel;
		init();
	}

	private void init() {
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
		Window window = getWindow();
		lp.copyFrom(window.getAttributes());
		// This makes the dialog take up the full width
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		window.setAttributes(lp);

		mLvServer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				callPlayIntent(mArrServerUrl[position]);
			}

		});
	}

	@Override
	public void show() {
		super.show();
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				if (mPrgChanelDialog != null)
					mPrgChanelDialog = null;
				mPrgChanelDialog = new ProgressDialog(mContext);
				mPrgChanelDialog.setMessage(mContext.getResources().getString(
						R.string.tv_chanel_title));
				mPrgChanelDialog.setCancelable(false);
				mPrgChanelDialog.show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				HTTPClientService handler = new HTTPClientService();
				String serverUrl = Constant.CHANEL_LINK_URL + mChannel.getId();
				String jsonStr = handler.MakeServiceCall(serverUrl,
						HTTPClientService.GET);

				if (jsonStr != null) {
					try {
						JSONObject jsonObject = new JSONObject(jsonStr);
						JSONArray arrServer = jsonObject
								.getJSONArray(Constant.TAG_SERVER);

						int serverCount = arrServer.length();
						

						Log.v("xxxxxxxxxxx","Servercount: "+ serverCount);
						
						mArrServerName = new String[serverCount];
						mArrServerUrl = new String[serverCount];

						for (int i = 0; i < arrServer.length(); ++i) {
							mArrServerName[i] = "Máy chủ " + (i + 1);
							JSONObject s = arrServer.getJSONObject(i);
							mArrServerUrl[i] = s
									.getString(Constant.TAG_SERVER_LINK);
						}

					} catch (JSONException e) {
					}
				} else {
				}

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (mPrgChanelDialog.isShowing())
					mPrgChanelDialog.dismiss();

				mTvChannelDesc.setText(Html.fromHtml(mChannel.getDesc()));
				mAQuery.id(mImvChannelIcon).image(mChannel.getIcon(), true,
						true);
				
				Log.v("xxxxxxxxxxx","namecount: "+ mArrServerName.length);
				ServerAdapter serverAdapter = new ServerAdapter(mContext,
						mArrServerName);
				mLvServer.setAdapter(serverAdapter);
			}
		}.execute();

	}

	/*
	 * 
	 */
	private void callPlayIntent(final String url) {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle("Chọn phầm mềm để xem");

		builder.setPositiveButton("Chọn xem",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						try {
							Intent tostart = new Intent(Intent.ACTION_VIEW);
							tostart.setDataAndType(Uri.parse(url), "video/*");
							mContext.startActivity(tostart);
						} catch (Exception e) {
							playDirect(url);
						}
					}
				});

		builder.setNegativeButton("Xem ngay",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						playDirect(url);
					}
				});

		builder.show();
	}

	private void playDirect(String url) {
		Intent intent = new Intent(mContext, PlayActivity.class);
		intent.putExtra("url", url);
		mContext.startActivity(intent);
	}
}
