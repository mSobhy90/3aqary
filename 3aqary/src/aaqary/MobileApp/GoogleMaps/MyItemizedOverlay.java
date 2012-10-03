package aaqary.MobileApp.GoogleMaps;

import java.util.ArrayList;

import aaqary.MobileApp.MainPackage.SignInActivity;
import aaqary.MobileApp.MainPackage.ViewPlaceActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> mOverlays;
	public static Context mContext;

	public MyItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mOverlays = new ArrayList<OverlayItem>();
		mContext = context;
		populate();
	}

	public MyItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		mOverlays = new ArrayList<OverlayItem>();
		populate();
	}

	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	public void removeItem(int i) {
		mOverlays.remove(i);
		populate();
	}

	@Override
	protected boolean onTap(int index) {
		GeoPoint gpoint = mOverlays.get(index).getPoint();
		double lat = gpoint.getLatitudeE6() / 1e6;
		double lon = gpoint.getLongitudeE6() / 1e6;
		String toast = "Title: " + mOverlays.get(index).getTitle();
		toast += "\nText: " + mOverlays.get(index).getSnippet();
		toast += "\nSymbol coordinates: Lat = " + lat + " Lon = " + lon
				+ " (microdegrees)";
		Toast.makeText(GoogleMapsActivity.context, toast, Toast.LENGTH_LONG)
				.show();
		ViewPlaceActivity.myPlace = GoogleMapsActivity.searchResult.get(index);
		ViewPlaceActivity.owner = SignInActivity.usersDB
				.getUser(ViewPlaceActivity.myPlace.getOwner().getUserName());
		ViewPlaceActivity.flag = 1;
		mContext.startActivity(new Intent(mContext, ViewPlaceActivity.class));
		return true;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

}
