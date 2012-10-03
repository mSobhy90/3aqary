package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import aaqary.MobileApp.GoogleMaps.GoogleMapsActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class AaqaryActivity extends TabActivity {
	public static TabHost tabHost;
	public static int i=0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		GoogleMapsActivity.th=this;
		if(i==0)
			setTabSignIn();
		else
			setTabUserProfile();
	}
	
	void setTabSignIn(){
		Resources res = getResources(); // Resource object to get Drawables
		tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec specs; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, SignInActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		specs = tabHost.newTabSpec("Sign In")
				.setIndicator("Sign In", res.getDrawable(R.drawable.signintab))
				.setContent(intent);
		tabHost.addTab(specs);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, GoogleMapsActivity.class);
		specs = tabHost.newTabSpec("Search")
				.setIndicator("Search", res.getDrawable(R.drawable.searchtab))
				.setContent(intent);
		tabHost.addTab(specs);

		tabHost.setCurrentTab(0);
	}
	
	void setTabUserProfile(){
		TabHost tabHost = getTabHost();
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
		i=0;
	}
}