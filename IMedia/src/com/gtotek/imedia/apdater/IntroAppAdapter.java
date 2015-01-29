package com.gtotek.imedia.apdater;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.gtotek.imedia.R;
import com.gtotek.imedia.entry.IntroApp;

public class IntroAppAdapter extends BaseAdapter {

	private ArrayList<IntroApp> mArrIntroApp;
	private static LayoutInflater layoutInflater = null;

	public IntroAppAdapter(Context context, ArrayList<IntroApp> arrIntroApp) {
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mArrIntroApp = arrIntroApp;
	}

	public int getCount() {
		return mArrIntroApp.size();
	}

	public Object getItem(int position) {
		return mArrIntroApp.get(position);
	} 

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.intro_app_item, null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		IntroApp introApp = mArrIntroApp.get(position);
		viewHolder.imvAppIcon.setImageResource(introApp.getIcon());
		viewHolder.tvAppName.setText(introApp.getName());
		return convertView;
	}

	private class ViewHolder {
		ImageView imvAppIcon;
		TextView tvAppName;

		public ViewHolder(View v) {
			tvAppName = (TextView) v.findViewById(R.id.tvAppName);
			imvAppIcon = (ImageView) v.findViewById(R.id.imvAppIcon);
			v.setTag(this);
		}
	}
}