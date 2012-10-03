package aaqary.MobileApp.GoogleMaps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;

//My Home 29990765,31139659
public class GeoUpdateHandler implements LocationListener {
	private GoogleMapsActivity th;
	public static Location curLocation;
	public static int myLocNewIndex = 0, myLocCurIndex = 0;

	public GeoUpdateHandler(GoogleMapsActivity g) {
		th = g;
		curLocation = new Location("My Location");
	}

	@Override
	public void onLocationChanged(Location location) {
		int lat = (int) (location.getLatitude() * 1E6);
		int lng = (int) (location.getLongitude() * 1E6);
		GeoPoint myLocation = new GeoPoint(lat, lng);
		curLocation = location;
		// mapController.setCenter(myLocation);
		// MapOverlay mO = new MapOverlay(th, myLocation);
		// th.getMapView().getOverlays().add(mO);
		if (myLocNewIndex != 0)
			th.removeOverlay(myLocCurIndex);
		else
			th.getMapController().animateTo(myLocation);
		th.addOverlay(myLocation);
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
}