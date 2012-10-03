package aaqary.MobileApp.MainPackage;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

public class PlaceDataBase {
	ArrayList<Place> places = new ArrayList<Place>();
	static int id;

	public Place getPlace(int id) {
		for (int i = 0; i < places.size(); i++)
			if (places.get(i).getId() == id)
				return places.get(i);
		return null;
	}

	public boolean addPlace(Place newPlace) {
		for (int i = 0; i < places.size(); i++)
			if (places.get(i).getId() == newPlace.getId())
				return false;
		places.add(newPlace);
		id++;
		return true;
	}

	public boolean addPlace(GeoPoint g) {
		Place myPlace=null;
		if (g.toString().charAt(1) == '0')
			myPlace = new Place(id, SignInActivity.usersDB.getUser(id),
					"Cairo", "Madenet Nasr", "City Stars", 3, 120, 0,
					500000, 0, "Great", 2, "Appartment", g);
		else if (g.toString().charAt(1) == '1')
			myPlace = new Place(id, SignInActivity.usersDB.getUser(id), "Giza",
					"Madenet Nasr", "City Stars", 3, 120, 1000, 0, 1,
					"Good", 2, "Villa", g);
		else
			myPlace = new Place(id, SignInActivity.usersDB.getUser(id), "6 October",
					"Madenet Nasr", "City Stars", 3, 120, 0, 2000000, 0,
					"Nice", 2, "Shalet", g);
		if (places.add(myPlace))
			return true;
		
			return false;
	}

	public void addMultiplePlaces() {
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
			addPlace(myPoint.get(i));
	}

	public ArrayList<GeoPoint> getGeoPoints() {
		ArrayList<GeoPoint> myPoints = new ArrayList<GeoPoint>();
		for (int i = 0; i < places.size(); i++) {
			myPoints.add(places.get(i).getCoord());
		}
		return myPoints;
	}

	public Place remove(int index) {
		return places.remove(index);
	}

	public int size() {
		return places.size();
	}

	public Place get(int index) {
		return places.get(index);
	}

	public ArrayList<Place> getAll() {
		return places;
	}

}
