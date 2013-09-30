package com.example.parakhihotelbookingapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] name;
	private final String[] address;
	private final String[] no;
	//private final Drawable[] image;
	//private final Float[] rating;
	
	public MyListAdapter(Context context, String[] name, String[] address, String[] no) {	//, Float[] rating, Drawable[] image
		super(context, R.layout.helplist, name);
		this.context = context;
		this.name = name;
		this.address = address;
		this.no=no;
		//this.rating = rating;
		//this.image = image;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.helplist, parent, false);
		
		//Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Carton-Slab.otf");
		
		TextView textView1 = (TextView) rowView.findViewById(R.id.name);
		TextView textView2 = (TextView) rowView.findViewById(R.id.address);
		TextView textView3 = (TextView) rowView.findViewById(R.id.no);
		//ImageView imageView = (ImageView) rowView.findViewById(R.id.no);
		//RatingBar rbar = (RatingBar) rowView.findViewById(R.id.ratingBar1);
		
		//textView1.setTypeface(tf);
		textView1.setText(name[position]);
		textView2.setText(address[position]);
		textView3.setText(no[position]);
		//rbar.setRating(rating[position]);
		//imageView.setImageDrawable(image[position]);
		//imageView.setBackgroundResource(R.drawable.border);
		//imageView.setClickable(false);
		return rowView;
	}
	
	
}
