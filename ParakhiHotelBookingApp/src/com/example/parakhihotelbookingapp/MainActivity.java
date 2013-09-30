package com.example.parakhihotelbookingapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	SQLiteDatabase db;
	String [] hotel_id; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		List<String> name_list = new ArrayList<String>();
		List<String> address_list = new ArrayList<String>();
		List<String> detail_list = new ArrayList<String>();
		List<String> id_list = new ArrayList<String>();
		
		db = openOrCreateDatabase("MyFavDatabase", MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS MyFavTable (HotelId VARCHAR, Name VARCHAR, Location VARCHAR, Details VARCHAR);");
		
		/*insert_val("1", "H1", "A1", "D1");
		insert_val("2", "H2", "A2", "D2");
		insert_val("3", "H3", "A3", "D3");
		*/
		setContentView(R.layout.activity_main);
		ListView lv=(ListView) findViewById(R.id.listHotels);
		
		/*String[] str1=new String[]{"sth","sth1"};
		String[] str2=new String[]{"thm","thm2"};
		String[] str3=new String[]{"20","30"};*/
		Cursor c = db.rawQuery("SELECT * FROM MyFavTable", null);
		if (c.getCount() == 0) {
			Toast.makeText(this, "NO DATA", 5000).show();
		} else {
			c.moveToFirst();
			do {
				id_list.add(c.getString(c.getColumnIndex("HotelId")));
				name_list.add(c.getString(c.getColumnIndex("Name")));
				address_list.add(c.getString(c.getColumnIndex("Location")));
				detail_list.add(c.getString(c.getColumnIndex("Details")));
				
			} while (c.moveToNext());
			c.close();
			db.close();
		}
		hotel_id = new String [id_list.size()];
		String [] name = new String [name_list.size()];
		String [] address = new String [address_list.size()];
		String [] detail = new String [detail_list.size()];
		
		id_list.toArray(hotel_id);
		name_list.toArray(name);
		address_list.toArray(address);
		detail_list.toArray(detail);
		
		MyListAdapter m=new MyListAdapter(this,name, address, detail);
		lv.setAdapter(m);
		lv.setOnItemClickListener(this);
	}

	
	private void insert_val(String id, String name, String add, String detail) {
		String sql1="INSERT INTO MyFavTable (HotelId, Name, Location, Details)"+" VALUES('"+id+"','"+name+"','"+add+"','"+ detail+"');";
		db.execSQL(sql1);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent i = new Intent (MainActivity.this, NewActivity.class);
		i.putExtra ("hotel_id", hotel_id[position]);
		startActivity(i);
		
	}

}
