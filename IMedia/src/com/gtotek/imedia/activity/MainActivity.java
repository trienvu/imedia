package com.gtotek.imedia.activity;

import java.util.ArrayList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.gtotek.imedia.R;
import com.gtotek.imedia.apdater.CategoryAdapter;
import com.gtotek.imedia.dialog.IntroAppDialog;
import com.gtotek.imedia.entry.Category;
import com.gtotek.imedia.fragment.ListChannelFragment;
import com.gtotek.imedia.util.ListCategoryWrapper;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
	// private String TAG = MainActivity.class.getSimpleName();
	private ListView mLvCategory;
	private ArrayList<Category> mLstCategory = null;
	private Fragment mChannelFragment;
	private CategoryAdapter mCategoryAdapter;

	private Dialog mIntroAppDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* requestWindowFeature(Window.FEATURE_NO_TITLE); */

		createSliding();
		InitMenu();

		// set default content View
		if (savedInstanceState != null)
			mChannelFragment = getSupportFragmentManager().getFragment(
					savedInstanceState, "mChannelFragment");
		if (mChannelFragment == null)
			mChannelFragment = ListChannelFragment.newInstance(mLstCategory
					.get(mLstCategory.size() - 1).get_id());

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, mChannelFragment).commit();

		mIntroAppDialog = new IntroAppDialog(MainActivity.this);
		mIntroAppDialog.show();
	}

	private void createSliding() {
		this.setContentView(R.layout.main_content_frame);
		this.setBehindContentView(R.layout.main_category);
		// Configuration sliding menu
		this.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		this.getSlidingMenu().setTouchModeBehind(SlidingMenu.TOUCHMODE_MARGIN);
		this.getSlidingMenu().setShadowDrawable(R.drawable.sidemenu_shadow);
		this.getSlidingMenu().setShadowWidth(50);
		this.getSlidingMenu().setBehindOffset(
				this.getResources().getDisplayMetrics().widthPixels / 5);
		this.getSlidingMenu().setAboveOffset(0);
	}

	private void InitMenu() {
		mLvCategory = (ListView) findViewById(R.id.listMenuCatalog);
		ListCategoryWrapper dw = (ListCategoryWrapper) getIntent()
				.getSerializableExtra("data");
		mLstCategory = dw.getCategories();

		mCategoryAdapter = new CategoryAdapter(MainActivity.this, mLstCategory);
		mCategoryAdapter.notifyDataSetChanged();
		mLvCategory.setAdapter(mCategoryAdapter);

		mLvCategory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				toggle();
				for (int i = 0; i < mLstCategory.size(); i++) {
					mLstCategory.get(i).set_selected(false);

				}
				Category category = mLstCategory.get(position);
				category.set_selected(true);
				mCategoryAdapter.notifyDataSetChanged();

				mChannelFragment = ListChannelFragment.newInstance(category
						.get_id());
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content_frame, mChannelFragment).commit();

			}
		});
	}

	public void getMenu() {
		getSlidingMenu().toggle();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}
}
