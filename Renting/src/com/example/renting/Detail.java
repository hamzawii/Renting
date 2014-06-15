package com.example.renting;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.lucky.R;
import com.example.renting.utils.Common;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Detail extends FragmentActivity {

	LayoutInflater inflater;
	private GoogleMap mMap;
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		// init common font
		if (Common.font == null) {
			Common.font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
		}
		
		TextView detail_array = (TextView)findViewById(R.id.detail_flech);
		detail_array.setTypeface(Common.font);
				
		setUpMapIfNeeded();

	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded() {

		if (mMap == null) {
			mMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();

			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {
		mMap.addMarker(new MarkerOptions().position(HAMBURG).title(
				"Voiture 0"));
		mMap.setMyLocationEnabled(true);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 5));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
	}

}
