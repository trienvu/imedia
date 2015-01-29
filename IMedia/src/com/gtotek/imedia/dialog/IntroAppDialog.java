package com.gtotek.imedia.dialog;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gtotek.imedia.R;
import com.gtotek.imedia.apdater.IntroAppAdapter;
import com.gtotek.imedia.entry.IntroApp;

public class IntroAppDialog extends Dialog {
	private Context mContext;
	private ListView mLvApp;
	private ArrayList<IntroApp> mArrIntroApp;

	public IntroAppDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(
				new ColorDrawable(android.R.color.transparent));
		setContentView(R.layout.custom_intro_app_dialog);

		this.mContext = context;
		this.mArrIntroApp = new ArrayList<IntroApp>();
		this.mLvApp = (ListView) this.findViewById(R.id.lvApp);
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

		mArrIntroApp.add(new IntroApp(R.drawable.ic_hcgd,
				"com.gtoteck.app.haychongiadung", "Hãy chọn giá đúng"));
		mArrIntroApp.add(new IntroApp(R.drawable.ic_dhbc, "com.gtotek.tumchu",
				"Bắt chữ - Đuổi hình bắt chữ 18+"));
		mArrIntroApp.add(new IntroApp(R.drawable.ic_wheel,
				"com.gtotek.wheeloffortune", "Chiếc nón kì diệu"));
		mArrIntroApp.add(new IntroApp(R.drawable.ic_guess_ball, "com.gtotek.footballquiz",
				"Trổ tài bóng đá"));

		IntroAppAdapter introAppAdapter = new IntroAppAdapter(mContext,
				mArrIntroApp);

		mLvApp.setAdapter(introAppAdapter);
		mLvApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				IntroApp introApp = mArrIntroApp.get(position);

				final String appPackageName = introApp.getApkName();
				mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri
						.parse("market://details?id=" + appPackageName)));
			}

		});
	}
}
