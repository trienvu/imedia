/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gtotek.imedia.activity;

import com.gtotek.imedia.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnBufferingUpdateListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends Activity implements OnInfoListener,
		OnBufferingUpdateListener {

	private String mPath = "";
	private Uri mUri;
	private VideoView mVideoView;
	private ProgressBar pbStreaming;
	private TextView mDownloadRateView, mLoadRateView;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.activity_play);
		mVideoView = (VideoView) findViewById(R.id.buffer);
		pbStreaming = (ProgressBar) findViewById(R.id.probar);

		mDownloadRateView = (TextView) findViewById(R.id.download_rate);
		mLoadRateView = (TextView) findViewById(R.id.load_rate);

		mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);
		mPath = getUrl();

	}

	@Override
	protected void onResume() {
		super.onResume();
		playVideo();
	}

	private void playVideo() {
		mUri = Uri.parse(mPath);
		mVideoView.setVideoURI(mUri);
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.requestFocus();
		mVideoView.setOnInfoListener(this);
		mVideoView.setOnBufferingUpdateListener(this);

		try {
			mVideoView
					.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mediaPlayer) {
							// optional need Vitamio 4.0
							mediaPlayer.setPlaybackSpeed(1.0f);
						}
					});

		} catch (Exception e) {
			Toast.makeText(PlayActivity.this,
					"Vui lòng kiểm tra lại mạng để chạy ứng dụng",
					Toast.LENGTH_LONG).show();
			finish();

		}
	}

	@Override
	protected void onStop() {
		super.onStart();
		mVideoView.stopPlayback();
	}

	private String getUrl() {
		return getIntent().getStringExtra("url");
	}

	@Override
	public boolean onInfo(MediaPlayer mp, int what, int extra) {
		switch (what) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			if (mVideoView.isPlaying()) {
				mVideoView.pause();
				pbStreaming.setVisibility(View.VISIBLE);
				mDownloadRateView.setText("");
				mLoadRateView.setText("");
				mDownloadRateView.setVisibility(View.VISIBLE);
				mLoadRateView.setVisibility(View.VISIBLE);

			}
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			mVideoView.start();
			pbStreaming.setVisibility(View.GONE);
			mDownloadRateView.setVisibility(View.GONE);
			mLoadRateView.setVisibility(View.GONE);
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			mDownloadRateView.setText("" + extra + "kb/s" + "  ");
			break;
		}
		return true;
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		mLoadRateView.setText(percent + "%");
	}

}
