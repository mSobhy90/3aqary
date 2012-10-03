package aaqary.MobileApp.MainPackage;

import java.util.ArrayList;

import aaqary.MobileApp.R;
import aaqary.MobileApp.GoogleMaps.GoogleMapsActivity;
import aaqary.MobileApp.ParserPackage.XMLParser;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

public class SignInActivity extends Activity {
	UserProfileActivity myUserProfile = new UserProfileActivity();
	public static UsersDataBase usersDB = new UsersDataBase();
	public static PlaceDataBase myPlacesDB = new PlaceDataBase();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		addTempUser();
		addTempPlaces();
		GoogleMapsActivity.placesDB = myPlacesDB;
		ShowApartmentActivity.placesDB = myPlacesDB;
	}

	public User getUser(String userName) {
		return usersDB.getUser(userName);
	}

	public boolean addUser(String fullName, String phoneNo, String mobileNo,
			String eMail, String userName, String password, String creditNo) {
		User newUser = new User(fullName, phoneNo, mobileNo, eMail, userName,
				password, creditNo);
		return usersDB.addUser(newUser);
	}

	public void addTempPlaces() {
		ArrayList<GeoPoint> myPoint = new ArrayList<GeoPoint>();
		myPoint.add(new GeoPoint(30576890, 31202220));
		myPoint.add(new GeoPoint(31576810, 30202210));
		myPoint.add(new GeoPoint(30050000, 31250000));
		myPoint.add(new GeoPoint(29990630, 31139650));
		myPoint.add(new GeoPoint(30027331, 31207877));
		myPoint.add(new GeoPoint(31249508, 30064742));
		myPoint.add(new GeoPoint(30027031, 31207857));
		myPoint.add(new GeoPoint(31329590, 30130332));
		myPoint.add(new GeoPoint(30027231, 31207817));
		myPoint.add(new GeoPoint(31208726, 30037600));
		myPoint.add(new GeoPoint(30021331, 31202847));
		myPoint.add(new GeoPoint(30025531, 31202377));
		for (int i = 0; i < myPoint.size(); i++)
			myPlacesDB.addPlace(myPoint.get(i));
	}

	public void addTempUser() {
		addUser("Mohamed Sobhy Sayed", "33834083", "0115668792",
				"m_sobhy_90@hotmail.com", "msobhy", "msobhy", "11");
		addUser("Alaa emam ali", "33433123", "0113445455", "", "alaa", "alaa",
				"");
		addUser("Yehia zakaria", "21312412", "0102133213", "basem_zakaria",
				"yehia", "111090", "1");
		addUser("Mohamed Magdi Ali", "37403640", "0181233123",
				"loka_fci@hotmail.com", "loka", "loka", "1");
		addUser("Salma Salama", "123123", "0123123312",
				"salmasalamaa@hotmail.com", "salma", "salma", "1");
	}

	// Sign In button Action
	@SuppressWarnings("static-access")
	public void buttonClick(View V) {
		EditText userName = (EditText) findViewById(R.id.editText2);
		EditText password = (EditText) findViewById(R.id.editText3);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
		// Should Connect to DB here
		// Always put network local IP
		HttpConn httpConn = new HttpConn("http://192.168.1.8:80/remote/");
		String uN = userName.getText().toString();
		String pass = password.getText().toString();
		String req = XMLParser.createLoginRequest(uN, pass);
		// Xml request as a parameter
		String re = httpConn.executePostRequest(req);
		XMLParser.parse(re);
		Boolean r = XMLParser.loginRes;
		if (r) {
			myUserProfile.curUser = getUser(uN);
			// myUserProfile.setData();
			TabHost tabHost = AaqaryActivity.tabHost;
			tabHost.clearAllTabs();
			Resources res = getResources(); // Resource object to get
											// Drawables
			TabHost.TabSpec spec; // Resusable TabSpec for each tab
			Intent intent; // Reusable Intent for each tab
			// Create an Intent to launch an Activity for the tab (to be
			// reused)
			intent = new Intent().setClass(this, UserProfileActivity.class);
			// Initialize a TabSpec for each tab and add it to the TabHost
			spec = tabHost
					.newTabSpec("My Profile")
					.setIndicator("My Profile",
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
			showMsg("Wrong User Name or Password please try again.");
		}
	}

	// Sign Up text link Action
	public void onClick(View V) {
		TabHost tabHost = AaqaryActivity.tabHost;
		tabHost.clearAllTabs();
		Resources res = getResources(); // Resource object to get Drawables
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, RegisterActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("Sign Up")
				.setIndicator("Sign Up", res.getDrawable(R.drawable.signintab))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, GoogleMapsActivity.class);
		spec = tabHost.newTabSpec("Search")
				.setIndicator("Search", res.getDrawable(R.drawable.searchtab))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_IMPLICIT_ONLY);

	}

	private void showMsg(String message) {
		Toast msg = Toast.makeText(SignInActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}
}