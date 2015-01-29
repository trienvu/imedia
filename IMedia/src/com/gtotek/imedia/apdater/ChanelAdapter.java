package com.gtotek.imedia.apdater;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtotek.imedia.R;
import com.gtotek.imedia.entry.Channel;

public class ChanelAdapter extends BaseAdapter {
	private ArrayList<Channel> mLstChannel;
	private LayoutInflater layoutInflater = null;
	//public ImageLoader imageLoader;
	AQuery mAQuery;
	public ChanelAdapter(Context context, ArrayList<Channel> lstChannel) {
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mLstChannel = lstChannel;
		this.mAQuery = new AQuery(context);
	}

	public int getCount() {
		return mLstChannel.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	// public void setSelectedItemPosition(int position) {
	// mSelectedPosition = position;
	// }

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.chanel_item,
					null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Channel chanel = mLstChannel.get(position);

		viewHolder.tvChanelName.setText(chanel.getName());
		viewHolder.tvChanelDes.setText(Html.fromHtml(chanel.getDesc()));
		
		mAQuery.id(viewHolder.imgChanelLogo).image(chanel.getIcon(),true,true);
		return convertView;
	}

	private class ViewHolder {
		TextView tvChanelName;
		TextView tvChanelDes;
		ImageView imgChanelLogo;

		public ViewHolder(View v) {
			tvChanelName = (TextView) v.findViewById(R.id.tvChanelName);
			tvChanelDes = (TextView) v.findViewById(R.id.tvChanelDesc);
			imgChanelLogo = (ImageView) v.findViewById(R.id.imgChanelLogo);
			v.setTag(this);
		}
	}
}