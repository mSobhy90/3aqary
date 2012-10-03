package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewPlaceActivity extends Activity {
	public static User owner;
	public static Place myPlace;
	public static int flag=0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewplace);
		
		if(flag==0){
		TextView helloU = (TextView) findViewById(R.id.helloUser);
		helloU.setText("Hello, " + owner.getUserName());
		}
		else{
			TextView helloU = (TextView) findViewById(R.id.helloUser);
			helloU.setText("Place View Form");
		}
		flag=0;
		TextView tv;
		tv = (TextView) findViewById(R.id.placeUserName);
		tv.append(owner.getUserName());

		tv = (TextView) findViewById(R.id.placeArea);
		tv.setText(tv.getText().toString() + myPlace.getArea());

		tv = (TextView) findViewById(R.id.placeCity);
		tv.setText(tv.getText().toString() + myPlace.getCity());

		tv = (TextView) findViewById(R.id.placeCoord);
		tv.setText(tv.getText().toString() + myPlace.getCoord().toString());

		tv = (TextView) findViewById(R.id.placeDesc);
		tv.setText(tv.getText().toString() + myPlace.getDescription());

		tv = (TextView) findViewById(R.id.placeID);
		tv.setText(tv.getText().toString() + myPlace.getId());

		tv = (TextView) findViewById(R.id.placePrice);
		if (myPlace.getRentPrice() == 0)
			tv.setText(tv.getText().toString() + myPlace.getSellPrice());
		else
			tv.setText(tv.getText().toString() + myPlace.getRentPrice());

		tv = (TextView) findViewById(R.id.placeRooms);
		tv.setText(tv.getText().toString() + myPlace.getNoOfRooms());

		tv = (TextView) findViewById(R.id.placeStatus);
		if (myPlace.getStatus() == 1)
			tv.setText(tv.getText().toString() + "busy");
		else
			tv.setText(tv.getText().toString() + "vacant");

		tv = (TextView) findViewById(R.id.placeStreet);
		tv.setText(tv.getText().toString() + myPlace.getStreet());

		tv = (TextView) findViewById(R.id.placeSuburb);
		tv.setText(tv.getText().toString() + myPlace.getSuburb());

		tv = (TextView) findViewById(R.id.placeType);
		tv.setText(tv.getText().toString() + myPlace.getType());

	}

	public void onClick(View V) {
		this.finish();
	}

}
