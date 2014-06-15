package com.example.renting;


import android.app.Activity;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.example.lucky.R;
import com.example.renting.utils.Common;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;

public class Menu {
	
	private static SlidingMenu menu;
	
	public static int width = 0; 

	private static Interpolator interp = new Interpolator() {
		@Override
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t + 1.0f;
		}		
	};
	
	public static CanvasTransformer getSlideAnimation()
	{
		return new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.translate(0, canvas.getHeight()*(1-interp.getInterpolation(percentOpen)));
			}			
		};
	}
	
	public static CanvasTransformer getICSAnimation()
	{
		return new CanvasTransformer() {
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen*0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
			}
		};
	}
	
	public static SlidingMenu getMenuRight(Activity activity)
	{
		int size, marg = 50;
		if(width == 0)	
			width = activity.getWindowManager().getDefaultDisplay().getWidth();
		//small screens
		//if(width < size)
		{
			size = (int) Math.floor(width*0.9);
			//marg = 25;
		}
		
		menu = new SlidingMenu(activity);
        menu.setMode(SlidingMenu.RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setTouchmodeMarginThreshold(marg);
        menu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_right);
        //menu.setBehindOffset(100);
        menu.setBehindWidth(size);
        menu.setBehindScrollScale(0.0f);
        menu.setBehindCanvasTransformer(Menu.getSlideAnimation());

        return menu;
	}
	
	
	
	public static SlidingMenu getMenuLeft(Activity activity)
	{
		int size = 520, marg = 50;
		if(width == 0)	
			width = activity.getWindowManager().getDefaultDisplay().getWidth();
		//small screens
		if(width < size)
		{
			size = (int) Math.floor(width*0.9);
			marg = 25;
		}
		
		menu = new SlidingMenu(activity);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setTouchmodeMarginThreshold(marg);
        menu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu_left);
        //menu.setBehindOffset(100);
        menu.setBehindWidth(size);
        menu.setBehindScrollScale(0.0f);
        menu.setBehindCanvasTransformer(Menu.getSlideAnimation());
        
        //set fontello icons
        TextView inscri = (TextView)menu.findViewById(R.id.menu_inscription_icon);
        TextView connexion = (TextView)menu.findViewById(R.id.menu_connexion_icon);
        TextView comment = (TextView)menu.findViewById(R.id.menu_comment_ca_marche_icon);
        TextView setting = (TextView)menu.findViewById(R.id.menu_params_icon);
        TextView propos = (TextView)menu.findViewById(R.id.menu_propos_icon);
        TextView quitter = (TextView)menu.findViewById(R.id.menu_quitter_icon);
        
        if(inscri != null && connexion != null && comment != null && setting != null 
        		&& propos != null && quitter != null)
			{
				inscri.setTypeface(Common.font);
				connexion.setTypeface(Common.font);
				comment.setTypeface(Common.font);
				setting.setTypeface(Common.font);
				propos.setTypeface(Common.font);
				quitter.setTypeface(Common.font);
			}
        return menu;
	}
	
}
