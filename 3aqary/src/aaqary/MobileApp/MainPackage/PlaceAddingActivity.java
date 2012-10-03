package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PlaceAddingActivity extends Activity {
	public static User curUser;
	PlaceDataBase placeDB=new PlaceDataBase();

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.addplace);
		EditText userName = (EditText) findViewById(R.id.editText1);
		userName.setText(curUser.getUserName());
		userName.setEnabled(false);
	}

	public void cancelClick(View V) {
		this.finish();
	}

	public void onClick(View V) {
		// Get Coordinates using google maps service.
		// Use GeoCoder to get Addres to set.
		Place newPlace = new Place();
		newPlace.setOwner(curUser);
		newPlace.setArea(Double
				.parseDouble(((EditText) findViewById(R.id.editText3))
						.getText().toString()));
		newPlace.setNoOfRooms(Integer
				.parseInt(((EditText) findViewById(R.id.editText4)).getText()
						.toString()));
		newPlace.setDescription(((EditText) findViewById(R.id.editText9))
				.getText().toString());
		newPlace.setFloorNo(Integer
				.parseInt(((EditText) findViewById(R.id.editText5)).getText()
						.toString()));
		String status = ((EditText) findViewById(R.id.editText6)).getText()
				.toString();
		if (status.toLowerCase().charAt(0) == 'v')
			newPlace.setStatus(1);
		else
			newPlace.setStatus(1);
		if (((CheckBox) findViewById(R.id.checkBox1)).isChecked()){
			newPlace.setSellPrice(Double
					.parseDouble(((EditText) findViewById(R.id.editText8))
							.getText().toString()));
			newPlace.setRentPrice(0);
		}
		else{
			newPlace.setRentPrice(Double
					.parseDouble(((EditText) findViewById(R.id.editText8))
							.getText().toString()));
			newPlace.setSellPrice(0);
		}
		newPlace.setType(((EditText) findViewById(R.id.editText7)).getText()
				.toString());
		newPlace.setPremuim(((CheckBox) findViewById(R.id.checkBox1)).isChecked());
		// newPlace.setCoord(coord);
		// newPlace.setCity("SD");
		// newPlace.setStreet("SD");
		// newPlace.setSuburb("SD");

		placeDB.addPlace(newPlace);
		showMsg("Apartment Added");
		this.finish();
	}

	private void showMsg(String message) {
		Toast msg = Toast.makeText(PlaceAddingActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}
}