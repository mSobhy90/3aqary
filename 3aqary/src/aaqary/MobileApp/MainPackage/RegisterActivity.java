package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import aaqary.MobileApp.GoogleMaps.GoogleMapsActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	UsersDataBase usersDB;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		usersDB = SignInActivity.usersDB;
	}

	public void submitData(View V) {
		String userName, password, fullName, phoneNo, mobileNo, eMail, creditNo;
		userName = ((EditText) findViewById(R.id.editText1)).getText()
				.toString();
		password = ((EditText) findViewById(R.id.editText2)).getText()
				.toString();
		fullName = ((EditText) findViewById(R.id.editText3)).getText()
				.toString()
				+ ((EditText) findViewById(R.id.editText4)).getText()
						.toString();
		phoneNo = ((EditText) findViewById(R.id.editText5)).getText()
				.toString();
		mobileNo = ((EditText) findViewById(R.id.editText6)).getText()
				.toString();
		eMail = ((EditText) findViewById(R.id.editText7)).getText().toString();
		creditNo = ((EditText) findViewById(R.id.editText8)).getText()
				.toString();

		User newUser = new User(fullName, phoneNo, mobileNo, eMail, userName,
				password, creditNo);
		if (usersDB.addUser(newUser)) {
			showMsg("New User Added");
			TabHost tabHost = AaqaryActivity.tabHost;
			tabHost.clearAllTabs();
			Resources res = getResources(); // Resource object to get Drawables
			TabHost.TabSpec spec; // Resusable TabSpec for each tab
			Intent intent; // Reusable Intent for each tab
			// Create an Intent to launch an Activity for the tab (to be reused)
			intent = new Intent().setClass(this, SignInActivity.class);
			// Initialize a TabSpec for each tab and add it to the TabHost
			spec = tabHost
					.newTabSpec("Sign In")
					.setIndicator("Sign In",
							res.getDrawable(R.drawable.signintab))
					.setContent(intent);
			tabHost.addTab(spec);

			// Do the same for the other tabs
			intent = new Intent().setClass(this, GoogleMapsActivity.class);
			spec = tabHost
					.newTabSpec("Search")
					.setIndicator("Search",
							res.getDrawable(R.drawable.searchtab))
					.setContent(intent);
			tabHost.addTab(spec);
			tabHost.setCurrentTab(0);
		} else {
			showMsg("User Name already exists.");
		}
	}

	public void cancelClick(View V) {
		TabHost tabHost = AaqaryActivity.tabHost;
		tabHost.clearAllTabs();
		Resources res = getResources(); // Resource object to get Drawables
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, SignInActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("Sign In")
				.setIndicator("Sign In", res.getDrawable(R.drawable.signintab))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, GoogleMapsActivity.class);
		spec = tabHost.newTabSpec("Search")
				.setIndicator("Search", res.getDrawable(R.drawable.searchtab))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

	private void showMsg(String message) {
		Toast msg = Toast.makeText(RegisterActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}
}