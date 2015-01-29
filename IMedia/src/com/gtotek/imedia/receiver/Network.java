package com.gtotek.imedia.receiver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network {
	private Context mContext;

	public Network(Context mContext) {
		this.mContext = mContext;
	}

	public boolean CheckNetwork() {
		boolean status = false;
		try {
			ConnectivityManager cm = (ConnectivityManager) mContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getNetworkInfo(0);
			if (netInfo != null
					&& netInfo.getState() == NetworkInfo.State.CONNECTED) {
				status = true;
			} else {
				netInfo = cm.getNetworkInfo(1);
				if (netInfo != null
						&& netInfo.getState() == NetworkInfo.State.CONNECTED)
					status = true;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
		return status;
	}

	
}
