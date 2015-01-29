package com.gtotek.imedia.apdater;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.gtotek.imedia.R;
import com.gtotek.imedia.entry.Category;

public class CategoryAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Category> mLstCategory;
	private AQuery mAQuery;

	public CategoryAdapter(Context context, ArrayList<Category> navDrawerItems) {
		this.mContext = context;
		this.mAQuery = new AQuery(context);
		this.mLstCategory = navDrawerItems;
	}

	@Override
	public int getCount() {
		return mLstCategory.size();
	}

	@Override
	public Object getItem(int position) {
		return mLstCategory.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.category_item, null);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Category category = mLstCategory.get(position);
		viewHolder.tvCateName.setText(category.getTitle());

		if (category.is_selected())
			// viewHolder.tvCateName.setCompoundDrawablesWithIntrinsicBounds(0,
			// 0,
			// R.drawable.line_menu_item_selected, 0);
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.ab_gradient_button_end));
		else
			convertView.setBackgroundColor(mContext.getResources().getColor(
					R.color.ab_gradient_button_start));
		// viewHolder.tvCateName.setCompoundDrawablesWithIntrinsicBounds(0, 0,
		// 0, 0);

		if (category.getIcon().equals(""))
			category.setIcon("https://www.google.com.vn/search?q=tivi+icon&espv=2&biw=1777&bih=840&tbm=isch&imgil=o5u9bIJTmRQEsM%253A%253B5vZalqtIPKYqjM%253Bhttp%25253A%25252F%25252Fwww.iconsdb.com%25252Fgreen-icons%25252Ftelevision-icon.html&source=iu&pf=m&fir=o5u9bIJTmRQEsM%253A%252C5vZalqtIPKYqjM%252C_&usg=__ugxTWqx5O0qe8d_7J-CQmvOtr3E%3D&dpr=0.9&ved=0CDQQyjc&ei=8bYbVIXOLYTl8AWH74L4BA#facrc=_&imgdii=_&imgrc=o5u9bIJTmRQEsM%253A%3B5vZalqtIPKYqjM%3Bhttp%253A%252F%252Fwww.iconsdb.com%252Ficons%252Fdownload%252Fgreen%252Ftelevision-512.png%3Bhttp%253A%252F%252Fwww.iconsdb.com%252Fgreen-icons%252Ftelevision-icon.html%3B512%3B512");
		mAQuery.id(viewHolder.imvCateIcon)
				.image(category.getIcon(), true, true);
		return convertView;
	}

	private class ViewHolder {
		TextView tvCateName;
		ImageView imvCateIcon;

		public ViewHolder(View v) {
			this.tvCateName = (TextView) v.findViewById(R.id.tvCateName);
			this.imvCateIcon = (ImageView) v.findViewById(R.id.imvCateIcon);
			v.setTag(this);
		}
	}
}
