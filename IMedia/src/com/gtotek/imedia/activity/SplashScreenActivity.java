package com.gtotek.imedia.activity;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import com.gtotek.imedia.R;
import com.gtotek.imedia.constant.Constant;
import com.gtotek.imedia.entry.Category;
import com.gtotek.imedia.services.HTTPClientService;
import com.gtotek.imedia.util.ListCategoryWrapper;

public class SplashScreenActivity extends Activity {

	private Context mContext = this;
	private ArrayList<Category> mLstCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splashscreen);
		
		mLstCategory = new ArrayList<Category>();
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Void doInBackground(Void... params) {
				HTTPClientService handler = new HTTPClientService();
				String jsonReturn = handler.MakeServiceCall(
						Constant.GROUP_CHANEL_URL, HTTPClientService.GET);

				if (jsonReturn != null) {
					try {
						JSONObject jsonObject = new JSONObject(jsonReturn);
						JSONArray categories = jsonObject
								.getJSONArray(Constant.TAG_CATE_DATA);
						for (int i = 0; i < categories.length(); ++i) {
							JSONObject c = categories.getJSONObject(i);
							Category category = new Category(
									c.getInt(Constant.TAG_CATE_ID),
									c.getString(Constant.TAG_CATE_NAME),
									c.getString(Constant.TAG_CATE_ICON));
							mLstCategory.add(category);
						}

					} catch (JSONException e) {
					}
				}

				return null;
			}

			@SuppressLint("InlinedApi")
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (mLstCategory.size() > 0) {
					Intent intent = new Intent(mContext, MainActivity.class);
					intent.putExtra("data", new ListCategoryWrapper(
							mLstCategory));
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_CLEAR_TASK
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					mContext.startActivity(intent);
				} else {
					Toast.makeText(mContext,
							"Vui lòng kiểm tra lại mạng để chạy ứng dụng",
							Toast.LENGTH_LONG).show();
					finish();
				}
			}
		}.execute();

	}
}
