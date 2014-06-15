package com.example.renting.utils;

import com.example.lucky.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {

	private Context context;

	public ConnectionDetector(Context context) {
		this.context = context;
	}

	/**
	 * Checking for all possible internet providers
	 * **/
	public boolean isConnectingToInternet() {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}

	public static void testConnexion(Activity activity) {

		ConnectionDetector cd = new ConnectionDetector(
				activity.getApplicationContext());

		if (!cd.isConnectingToInternet()) {
			// Connexion Internet non dispo
			MyDialog.SimpleDialog(activity,R.string.connextion_title, R.string.connextion_body);
			return;
		}
	}

}
