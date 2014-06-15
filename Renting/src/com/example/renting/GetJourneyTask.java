package com.example.renting;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import com.cuubonandroid.sugaredlistanimations.SpeedScrollListener;
import com.example.lucky.R;
import com.example.renting.entity.LesTrajets;
import com.example.renting.utils.HttpHelper;
import com.example.renting.utils.SharedPref;
import com.example.renting.utils.Urls;
import com.google.gson.Gson;

public class GetJourneyTask extends AsyncTask<Void, Void, LesTrajets> {
	private ProgressDialog dialog;
	private LesTrajets lesTrajets;
	private Activity context;
	private SpeedScrollListener listener;
	private JourneyAdapter adapter;
	
	public void setContext(Activity context) {
		this.context =  context;
	}

	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(context);
		dialog.setMessage("Chargement en cours ...");
		dialog.show();
		super.onPreExecute();
	}

	@Override
	protected LesTrajets doInBackground(Void... params) {
		String jsonRes = "";
		LesTrajets lesTrajets = null;
		//prepare connexion to server
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(Urls.listTrajets);
		try {
			HttpResponse response = client.execute(httppost);
			jsonRes = (HttpHelper.request(response));
			//remove ads from the server response (we are using a free web host)
			jsonRes = jsonRes.substring(0, jsonRes.indexOf("<!--"));

			Gson gson = new Gson();
			lesTrajets = gson.fromJson(jsonRes, LesTrajets.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// we got a non null response, we save it for an offline use
		if (lesTrajets != null && lesTrajets.getLesTrajets().size() > 0)
			SharedPref.SavelesTrajects(context, lesTrajets);
		
		return lesTrajets;
	}

	@Override
	protected void onPostExecute(LesTrajets res) {
		if (dialog.isShowing())
			dialog.dismiss();
		lesTrajets = res;
		// if we got a null response, we try to load data from sharedPref
		if (lesTrajets == null) {
			lesTrajets = SharedPref.getlesTrajects(context);
		}

		if (lesTrajets != null)
		{
			ListView listView = (ListView) context.findViewById(R.id.main_listview);
			listener = new SpeedScrollListener();
			listView.setOnScrollListener(listener);
		    adapter = new JourneyAdapter(context, listener,lesTrajets);
		    listView.setAdapter(adapter);
		}

		super.onPostExecute(lesTrajets);
	}
}
