package com.example.renting;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.R;
import com.example.renting.utils.Common;
import com.example.renting.utils.ConnectionDetector;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {

	private SlidingMenu menu_left,menu_right;
	private long touchTime;
	private TextView searchT, menuT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		searchT = (TextView) findViewById(R.id.search_action);
		menuT = (TextView) findViewById(R.id.menu_action);
		
		//load icons with fontello
		init_fontelleo_icons();
		
		//set click event to top buttons (in action bar)
		menuT.setOnClickListener(MenuclickListner);
		searchT.setOnClickListener(MenuclickListner);
		
		//init the two slinding menu
		menu_left = Menu.getMenuLeft(this);
		menu_right = Menu.getMenuRight(this);
		
		//test internet connexion
		ConnectionDetector.testConnexion(this);

		//run JourneyTask to get and display journeys from server
		GetJourneyTask JourneyTask = new GetJourneyTask();
		JourneyTask.setContext(this);
		JourneyTask.execute();
		
	}

	

	OnClickListener MenuclickListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.menu_action) {
				menu_left.toggle();
			}
			else if(v.getId() == R.id.search_action) {
				menu_right.toggle();
			}
		}
	};

	
	//override hadware keys
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {

		//back key pressed
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			
			//hide left menu if showing
			if (menu_left.isMenuShowing()) {
				menu_left.showContent(true);
			} 
			//hide right menu if showing
			else if (menu_right.isMenuShowing()) {
				menu_right.showContent(true);
			} 
			//close the app after double click
			else {
				long newtouchTime = System.currentTimeMillis();
				if (touchTime == 0 || newtouchTime - touchTime > 1000) {
					Toast.makeText(getApplicationContext(),
							"Double clique pour quitter", 400).show();
					touchTime = System.currentTimeMillis();
				} else
					finish();
			}
		}
		//menu key pressed
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			
			if(!menu_right.isMenuShowing())
				menu_left.toggle(true);
		}
		return true;
	}
	
	//init fontello icons
	private void init_fontelleo_icons() {
		// init common font
		if (Common.font == null) {
			Common.font = Typeface.createFromAsset(getAssets(), "fontello.ttf");
		}
		menuT.setTypeface(Common.font);
		searchT.setTypeface(Common.font);
	}

}
