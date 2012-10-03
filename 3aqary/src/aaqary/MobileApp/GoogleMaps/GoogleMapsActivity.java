package aaqary.MobileApp.GoogleMaps;

import java.util.ArrayList;
import java.util.List;

import aaqary.MobileApp.R;
import aaqary.MobileApp.MainPackage.AaqaryActivity;
import aaqary.MobileApp.MainPackage.Place;
import aaqary.MobileApp.MainPackage.PlaceDataBase;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

//Costa is at 3057689, 3120221
//3amo Maged is at 30050000,31250000
public class GoogleMapsActivity extends MapActivity {
	private MapController mapController;
	private LocationManager locationManager;
	private GeoUpdateHandler geoHandler;
	private MapView mapView;
	private SubMenu viewSubMenu;
	private Menu myMenu;

	public static ArrayList<Place> searchResult = new ArrayList<Place>();
	public static String city;
	public static int i = 0;
	public static GoogleMapsActivity gmA;
	public static AaqaryActivity th;
	public static PlaceDataBase placesDB;
	public static double toDistance = 0;
	public static Context context;

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.googlemaps);
		// Get application context for later use
		searchResult = placesDB.getAll();
		context = getApplicationContext();
		// Create a map view
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setStreetView(true);
		mapController = mapView.getController();
		mapController.setZoom(19); // Zoom 1 is world view
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		geoHandler = new GeoUpdateHandler(this);
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, geoHandler);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, geoHandler);
		gmA = this;
		showMsg("Map Loading Done");
	}

	private void showMsg(String message) {
		Toast msg = Toast.makeText(GoogleMapsActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		myMenu = menu;
		menu.add(0, 0, 0, "My Location.");
		menu.add(0, 1, 1, "To Appartment->1.");
		menu.add(0, 2, 4, "Exit!!");
		menu.add(0, 6, 3, "Refresh Data");
		viewSubMenu = menu.addSubMenu(0, 3, 2, "Search by:");
		fillSubMenu();
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.map_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		// case R.id.animateTo:
		case 0:
			GeoPoint myLoc = new GeoPoint(
					(int) (GeoUpdateHandler.curLocation.getLatitude() * 1E6),
					(int) (GeoUpdateHandler.curLocation.getLongitude() * 1E6));
			mapController.animateTo(myLoc);
			i = 0;
			myMenu.getItem(1).setTitle(
					"To Apartment->" + ((i % placesDB.size()) + 1) + ".");
			mapView.invalidate();
			return true;
		case 1:
			if (searchResult.size() == 0) {
				showMsg("No Appartments Available");
			} else {
				mapController.animateTo(placesDB.get(i % placesDB.size())
						.getCoord());
				// showMsg("Here is at "+ placesDB.get(i %
				// placesDB.size()).getCoord().toString());
				i++;
				myMenu.getItem(1).setTitle(
						"To Apartment->" + ((i % placesDB.size()) + 1) + ".");
				mapView.invalidate();
			}
			return true;
		case 2:
			this.finish();
			th.finish();
			return true;
		case 4:
			i = 0;
			GeoUpdateHandler.myLocNewIndex = 0;
			myMenu.getItem(1).setTitle(
					"To Apartment->" + ((i % placesDB.size()) + 1) + ".");
			myLoc = new GeoPoint(
					(int) (GeoUpdateHandler.curLocation.getLatitude() * 1E6),
					(int) (GeoUpdateHandler.curLocation.getLongitude() * 1E6));
			mapController.animateTo(myLoc);
			startActivity(new Intent(this, SearchByDistanceActivity.class));
			return true;
		case 5:
			i = 0;
			GeoUpdateHandler.myLocNewIndex = 0;
			myMenu.getItem(1).setTitle(
					"To Apartment->" + ((i % placesDB.size()) + 1) + ".");
			myLoc = new GeoPoint(
					(int) (GeoUpdateHandler.curLocation.getLatitude() * 1E6),
					(int) (GeoUpdateHandler.curLocation.getLongitude() * 1E6));
			mapController.animateTo(myLoc);
			startActivity(new Intent(this, SearchByCityActivity.class));
			return true;
		case 6:
			searchResult = placesDB.getAll();
			i = 0;
			GeoUpdateHandler.myLocNewIndex = 0;
			myMenu.getItem(1).setTitle(
					"To Apartment->" + ((i % placesDB.size()) + 1) + ".");
			myLoc = new GeoPoint(
					(int) (GeoUpdateHandler.curLocation.getLatitude() * 1E6),
					(int) (GeoUpdateHandler.curLocation.getLongitude() * 1E6));
			mapController.animateTo(myLoc);
			List<Overlay> mapOverlays = mapView.getOverlays();
			mapOverlays.remove(0);
			addOverlay(new GeoPoint(
					(int) (GeoUpdateHandler.curLocation.getLatitude() * 1E6),
					(int) (GeoUpdateHandler.curLocation.getLongitude() * 1E6)));
			showMsg("Data set refreshed");
			showMsg(searchResult.size() + " place added");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public ArrayList<GeoPoint> getGeoPoints() {
		ArrayList<GeoPoint> myPoints = new ArrayList<GeoPoint>();
		for (int i = 0; i < searchResult.size(); i++) {
			myPoints.add(searchResult.get(i).getCoord());
		}
		return myPoints;
	}

	public static void setNewOverlay(Boolean flag) {
		if (!flag)
			gmA.getWithinDistance();
		else
			gmA.getSameCity();
		if (searchResult.size() != 0) {
			gmA.showMsg(searchResult.size() + " Apartments found");
			List<Overlay> mapOverlays = gmA.mapView.getOverlays();
			mapOverlays.remove(0);
		} else {
			gmA.showMsg("No Apartments Available");
			i = -1;
			gmA.myMenu.getItem(1).setTitle("No Apartments.");
		}
		gmA.addOverlay(new GeoPoint((int) (GeoUpdateHandler.curLocation
				.getLatitude() * 1E6), (int) (GeoUpdateHandler.curLocation
				.getLongitude() * 1E6)));
	}

	public void addOverlay(GeoPoint myLoc) {
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.curloc);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		MyItemizedOverlay itemizedOverlay = new MyItemizedOverlay(drawable,
				this);

		OverlayItem overlayItem;
		GeoUpdateHandler.myLocNewIndex++;
		overlayItem = new OverlayItem(myLoc, "You Are Here!", myLoc.toString());
		itemizedOverlay.addOverlay(overlayItem);
		if (GeoUpdateHandler.myLocNewIndex == 1) {
			mapOverlays.add(itemizedOverlay);
			addOverlay(getGeoPoints());
		} else {
			((MyItemizedOverlay) mapOverlays.get(0)).addOverlay(itemizedOverlay
					.getItem(0));
		}
		mapView.invalidate();
	}

	public void addOverlay(ArrayList<GeoPoint> myPoints) {
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(
				R.drawable.androidmarker);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		MyItemizedOverlay itemizedOverlay = new MyItemizedOverlay(drawable,
				this);
		OverlayItem overlayItem;
		GeoUpdateHandler.myLocNewIndex += myPoints.size();
		for (int i = 0; i < myPoints.size(); i++) {
			overlayItem = new OverlayItem(myPoints.get(i), "Appartment "
					+ (i + 1) + " is here:", myPoints.get(i).toString());
			overlayItem.setMarker(drawable);
			itemizedOverlay.addOverlay(overlayItem);
		}
		for (int i = 0; i < itemizedOverlay.size(); i++)
			((MyItemizedOverlay) mapOverlays.get(0)).addOverlay(itemizedOverlay
					.getItem(i));
		// mapView.invalidate();
	}

	public void fillSubMenu() {// For adding Appartments to the sub menu.
		viewSubMenu.add(0, 4, 0, "Distance from here?");
		viewSubMenu.add(0, 5, 1, "City name?");
	}

	public void removeOverlay(int index) {
		GeoUpdateHandler.myLocNewIndex -= 1;
		List<Overlay> mapOverlays = mapView.getOverlays();
		MyItemizedOverlay myOverlay = (MyItemizedOverlay) mapOverlays.get(0);
		myOverlay.removeItem(index);
	}

	public void getWithinDistance() {
		searchResult = new ArrayList<Place>();
		ArrayList<GeoPoint> myPoints = placesDB.getGeoPoints();
		Location l2 = new Location("");
		float temp;
		for (int i = 0; i < myPoints.size(); i++) {
			l2.setLatitude(myPoints.get(i).getLatitudeE6());
			l2.setLongitude(myPoints.get(i).getLongitudeE6());
			temp = GeoUpdateHandler.curLocation.distanceTo(l2);
			if (temp < toDistance)
				searchResult.add(placesDB.get(i));
		}
	}

	public void getSameCity() {
		searchResult = new ArrayList<Place>();
		for (int i = 0; i < placesDB.size(); i++) {
			if (placesDB.get(i).getCity().toLowerCase()
					.equals(city.toLowerCase()))
				searchResult.add(placesDB.get(i));
		}
	}

	public MapController getMapController() {
		return mapController;
	}

	public MapView getMapView() {
		return mapView;
	}

	public SubMenu getSubMenu() {
		return viewSubMenu;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}