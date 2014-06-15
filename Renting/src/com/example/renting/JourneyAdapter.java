package com.example.renting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cuubonandroid.sugaredlistanimations.GPlusListAdapter;
import com.cuubonandroid.sugaredlistanimations.SpeedScrollListener;
import com.example.lucky.R;
import com.example.renting.entity.LesTrajets;
import com.example.renting.utils.Common;

public class JourneyAdapter extends GPlusListAdapter {

	private Activity context;
	private LesTrajets lesTrajets;

	public JourneyAdapter(Activity context, SpeedScrollListener scrollListener, LesTrajets lesTrjets) {
		super(context, scrollListener, lesTrjets.getLesTrajets());
		this.context = context;
		this.lesTrajets = lesTrjets;
	}

	@Override
	protected View getRowView(final int position, View journey,
			ViewGroup parent) {

		//wi load xml journey patern 
		if (journey == null) {
			journey = LayoutInflater.from(context).inflate(R.layout.patern_main_activity, parent, false);
		}

		TextView from 		= (TextView) journey.findViewById(R.id.patern_main_from);
		TextView to 		= (TextView) journey.findViewById(R.id.patern_main_to);
		TextView dateDeb 	= (TextView) journey.findViewById(R.id.main_date_debut);
		TextView dateFin 	= (TextView) journey.findViewById(R.id.main_date_fin);
		TextView marque 	= (TextView) journey.findViewById(R.id.marque_pattern);
		TextView desciption = (TextView) journey.findViewById(R.id.desciption_pattern);

		TextView arrow 		= (TextView) journey.findViewById(R.id.main_flech);
		arrow.setTypeface(Common.font);

		//fill journey with data from json journey ArrayList
		from.setText(lesTrajets.getLesTrajets().get(position).getDebut());
		to.setText(lesTrajets.getLesTrajets().get(position).getFin());
		dateDeb.setText(lesTrajets.getLesTrajets().get(position).getDateDebut());
		dateFin.setText(lesTrajets.getLesTrajets().get(position).getDateFin());
		marque.setText(lesTrajets.getLesTrajets().get(position).getVoiture().getMarque());
		desciption.setText(lesTrajets.getLesTrajets().get(position).getVoiture().getModele());

		
		journey.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//override detail activity open animation 
				context.overridePendingTransition(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
				
				//we send a journey object to the detail activity
				Intent i = new Intent(context, Detail.class);
				Bundle args = new Bundle();
				args.putSerializable("trajet", lesTrajets.getLesTrajets().get(position));
				i.putExtras(args);
				
				//show detail activity
				context.startActivity(i);
			}
		});

		return journey;
	}

}
