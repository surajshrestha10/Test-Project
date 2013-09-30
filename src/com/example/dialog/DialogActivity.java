package com.example.dialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class DialogActivity extends Activity {

	CharSequence[] items = { "Google", "Apple", "Microsoft" };
	boolean[] itemChecked = new boolean[items.length];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
	}

	@SuppressWarnings("deprecation")
	public void onClick(View v) {
		showDialog(1);
	}
	
	
	public void onClick2(View v)
	{
		final ProgressDialog dialog = ProgressDialog.show(
				this, "Doing Something","Please Wait....",true);
				new Thread (new Runnable()
				{
					public void run(){
						
						try
						{
							Thread.sleep(5000);
							dialog.dismiss();
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}).start();		
	
	}
	

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("This is a dialog with some simple text");
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(getBaseContext(), "OK clicked!",
									Toast.LENGTH_SHORT).show();
						}

					});
			builder.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							Toast.makeText(getBaseContext(), "Cancel Clicked!",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.setMultiChoiceItems(items, itemChecked,
					new DialogInterface.OnMultiChoiceClickListener() {
						public void onClick(DialogInterface dialog, int which,
								boolean isChecked) {
							Toast.makeText(
									getBaseContext(),
									items[which]
											+ (isChecked ? "checked!"
													: " unchecked!"),
									Toast.LENGTH_SHORT).show();
						}

					});
			return builder.create();
		}

		return null;
	}

}
