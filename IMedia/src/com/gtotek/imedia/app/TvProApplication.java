package com.gtotek.imedia.app;

import android.app.Application;
import android.media.MediaPlayer;

public class TvProApplication extends Application {

	public static final String TAG = TvProApplication.class.getSimpleName();
	public static MediaPlayer mPlayer = null;

	private static TvProApplication mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized TvProApplication getInstance() {
		return mInstance;
	}

}
