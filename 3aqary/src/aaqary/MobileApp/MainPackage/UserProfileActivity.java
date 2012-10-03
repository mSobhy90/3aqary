package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends Activity {
	public static User curUser;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userprofile);
		TextView helloU = (TextView) findViewById(R.id.helloUser);
		helloU.setText("Hello " + curUser.getUserName());

		TextView name = (TextView) findViewById(R.id.fullName);
		name.setText("Name: " + curUser.getFullName());
		TextView phoneNo = (TextView) findViewById(R.id.phoneNo);
		phoneNo.setText("Phone: " + curUser.getPhoneNo());
		TextView mobileNo = (TextView) findViewById(R.id.mobileNo);
		mobileNo.setText("Mobile: " + curUser.getMobileNo());
		TextView email = (TextView) findViewById(R.id.email);
		email.setText("Email: " + curUser.geteMail());
		TextView creditCard = (TextView) findViewById(R.id.creditCard);
		creditCard.setText("Credit Card No.: " + curUser.getCreditNo());
		// setData();
	}

	@SuppressWarnings("unused")
	private void showMsg(String message) {
		Toast msg = Toast.makeText(UserProfileActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}

	public void setData() {
		TextView helloU = (TextView) findViewById(R.id.helloUser);
		helloU.setText("Hello " + curUser.getUserName());

		TextView name = (TextView) findViewById(R.id.fullName);
		name.setText("Name: " + curUser.getFullName());
		TextView phoneNo = (TextView) findViewById(R.id.phoneNo);
		phoneNo.setText("Phone: " + curUser.getPhoneNo());
		TextView mobileNo = (TextView) findViewById(R.id.mobileNo);
		mobileNo.setText("Mobile: " + curUser.getMobileNo());
		TextView email = (TextView) findViewById(R.id.email);
		email.setText("Email: " + curUser.geteMail());
		TextView creditCard = (TextView) findViewById(R.id.creditCard);
		creditCard.setText("Credit Card No.: " + curUser.getCreditNo());
	}

	public void editData(View V) {
		UserDataEditingActivity.curUser = curUser;
		UserDataEditingActivity.th=this;
		startActivity(new Intent(this, UserDataEditingActivity.class));
	}

	public void showAppartments(View V) {
		ShowApartmentActivity.curUser=curUser;
		startActivity(new Intent(this, ShowApartmentActivity.class));
	}

	public void addAppartment(View V) {
		PlaceAddingActivity.curUser = curUser;
		startActivity(new Intent(this, PlaceAddingActivity.class));
	}

	public void signOutClick(View V) {
		this.finish();
		startActivity(new Intent(this, AaqaryActivity.class));
	}
}
