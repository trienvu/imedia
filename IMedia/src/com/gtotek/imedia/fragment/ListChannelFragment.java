package com.gtotek.imedia.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.gtotek.imedia.R;
import com.gtotek.imedia.activity.MainActivity;
import com.gtotek.imedia.apdater.ChanelAdapter;
import com.gtotek.imedia.constant.Constant;
import com.gtotek.imedia.dialog.CustomServerDialog;
import com.gtotek.imedia.entry.Channel;
import com.gtotek.imedia.receiver.Network;
import com.gtotek.imedia.services.HTTPClientService;

public class ListChannelFragment extends Fragment {
	private Context mContext;
	private ImageView mBtnToggle;
	private ProgressDialog mPrgChanelDialog;
	private GridView mGvChanel;
	private static String mCategoryUrl;
	private ArrayList<Channel> mLstChannel = null;

	public static ListChannelFragment newInstance(int cateId) {
		ListChannelFragment f = new ListChannelFragment();
		Bundle args = new Bundle();
		args.putInt("cateId", cateId);
		f.setArguments(args);
		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("cateId", 1);
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		View rootView = inflater.inflate(R.layout.fragment_chanel, null);

		this.mBtnToggle = (ImageView) rootView
				.findViewById(R.id.btn_menu_toogle);
		this.mGvChanel = (GridView) rootView.findViewById(R.id.lvChanel);
		this.mLstChannel = new ArrayList<Channel>();

		init();
		new GetChannels().execute();
		return rootView;
	}

	private void init() {
		mCategoryUrl = Constant.CHANNEL_URL + getShownIndex();
		mBtnToggle.setOnClickListener(toggleClick());
	}

	private OnClickListener toggleClick() {
		OnClickListener click = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Show slide menu
				((MainActivity) mContext).getMenu();
			}
		};
		return click;
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */

	@SuppressLint("NewApi")
	private class GetChannels extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			if (mPrgChanelDialog != null)
				mPrgChanelDialog = null;
			mPrgChanelDialog = new ProgressDialog(getActivity());
			mPrgChanelDialog
					.setMessage(getString(R.string.dialog_load_chanel_title));
			mPrgChanelDialog.setCancelable(false);
			mPrgChanelDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			HTTPClientService handler = new HTTPClientService();
			String jsonReturn = handler.MakeServiceCall(mCategoryUrl,
					HTTPClientService.GET);

			if (jsonReturn != null) {
				try {
					JSONObject jsonObject = new JSONObject(jsonReturn);
					JSONArray channels = jsonObject
							.getJSONArray(Constant.TAG_CHANNELS);

					// Looping through all channels
					for (int i = 0; i < channels.length(); ++i) {
						JSONObject c = channels.getJSONObject(i);
						Channel channel = new Channel(
								c.getInt(Constant.TAG__CHANNEL_ID),
								c.getString(Constant.TAG_CHANNEL_NAME),
								c.getString(Constant.TAG_LOGO_URL),
								c.getString(Constant.TAG_CHANNEL_DESCRIPTION));

						// Adding contact to contactList;
						mLstChannel.add(channel);
					}

				} catch (JSONException e) {
				}
			} else {
				Log.e("Service Hander",
						"Couldn't get data from the categoryUrl");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			// Dismiss the progress dialog
			if (mPrgChanelDialog.isShowing())
				mPrgChanelDialog.dismiss();

			final ChanelAdapter chanelAdapter = new ChanelAdapter(mContext,
					mLstChannel);
			chanelAdapter.notifyDataSetChanged();
			mGvChanel.setAdapter(chanelAdapter);

			mGvChanel.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Channel chanel = mLstChannel.get(position);

					// chanelAdapter.setSelectedItemPosition(position);
					// chanelAdapter.notifyDataSetChanged();

					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
							.permitAll().build();
					StrictMode.setThreadPolicy(policy);

					Network network = new Network(mContext);
					if (network.CheckNetwork()) {
						CustomServerDialog dlgSever = new CustomServerDialog(
								mContext, chanel);
						dlgSever.show();
					} else {
						Toast.makeText(mContext,
								"Vui lòng kiểm tra lại mạng để xem tiếp",
								Toast.LENGTH_LONG).show();
					}

				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

}
