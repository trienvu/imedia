package com.gtotek.imedia.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gtotek.imedia.R;

public class ServerAdapter extends BaseAdapter {
	private String[] mArrServerName;
	private static LayoutInflater layoutInflater = null;

	public ServerAdapter(Context context, String[] arrServerName) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mArrServerName = arrServerName;
	}

	public int getCount() {
		return mArrServerName.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.server_item,
					null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvServer.setText(mArrServerName[position]);
		return convertView;
	}

	private class ViewHolder {
		TextView tvServer;

		public ViewHolder(View v) {
			tvServer = (TextView) v.findViewById(R.id.tvServer);
			v.setTag(this);
		}
	}
}