package com.example.renting.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.example.lucky.R;

public class MyDialog {
	
	public static void SimpleDialog(final Context context, int title,int body)
	{
		//verifier si nous allons afficher le message
		boolean show = SharedPref.getBoolean(context, "ShowInternetConnexionMSG");
		
		if(show)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			LayoutInflater adbInflater = LayoutInflater.from(context);
	        
			View eulaLayout = adbInflater.inflate(R.layout.dialog_checkbox, null);
			final CheckBox dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.dialog_do_not_show_again);
			builder.setView(eulaLayout);
	        
		    builder.setTitle(context.getString(title));
		    builder.setMessage("\n"+context.getString(body)+"\n");

		    builder.setPositiveButton("Ok",
		    		new DialogInterface.OnClickListener() {
		    	@Override
		        public void onClick(DialogInterface dialog, int which) {
		            if(dontShowAgain.isChecked()) //do not show again
		            	SharedPref.SaveBoolean(context, "ShowInternetConnexionMSG", false);
		            else 
		            	SharedPref.SaveBoolean(context, "ShowInternetConnexionMSG", true);
		            
		            dialog.dismiss();
		        }

		    });

		    AlertDialog alert = builder.create();
		    alert.show();
		}
		
	}

}
