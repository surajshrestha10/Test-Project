package com.example.parakhihotelbookingapp;

//import com.google.android.maps.MapActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newview);

		TextView t1=(TextView) findViewById(R.id.textView1);
		TextView t2=(TextView) findViewById(R.id.textView2);
		TextView t3=(TextView) findViewById(R.id.textView3);
		TextView t4=(TextView) findViewById(R.id.textView4);
		
		ImageView i1=(ImageView) findViewById(R.id.imageView1);
		
		//MapView map = findViewById(R.id.map);
		String id = getIntent().getExtras().getString("hotel_id");
		/*
		 * Toast.makeText(this, id, 4000).show();
		 */
		SQLiteDatabase db = openOrCreateDatabase("MyFavDatabase", MODE_PRIVATE,
				null);
		String sql = "SELECT Name, Location, Details FROM MyFavTable WHERE HotelId = '"
				+ id + "'";
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();

		
		String name = c.getString(c.getColumnIndex("Name"));
		String address = c.getString(c.getColumnIndex("Location"));
		String details = c.getString(c.getColumnIndex("Details"));
		
		String i = "h1";
		 
		int imageResource = this.getResources().getIdentifier(i, "drawable", getPackageName());
		if (imageResource == 0) {
			
		} else {
			i1.setImageDrawable(this.getResources().getDrawable(imageResource));
		}
		t1.setText(name);
		t2.setText(address);
		t3.setText(details);
		
	}
}
