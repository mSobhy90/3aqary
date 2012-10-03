package aaqary.MobileApp.MainPackage;

import com.google.android.maps.GeoPoint;

public class Place {
	static int id;
	User owner;
	String city;
	String suburb;
	String street;
	int noOfRooms;
	double area;
	double rentPrice;
	double sellPrice;
	int status;
	String description;
	int floorNo;
	String type;
	GeoPoint coord;
	boolean isPremuim;

	public Place() {
	}

	public Place(int id, User owner, String city, String suburb, String street,
			int noOfRooms, double area, double rentPrice, double sellPrice,
			int status, String description, int floorNo, String type,
			GeoPoint coord) {
		id = id++;
		this.owner = owner;
		this.city = city;
		this.suburb = suburb;
		this.street = street;
		this.noOfRooms = noOfRooms;
		this.area = area;
		this.rentPrice = rentPrice;
		this.sellPrice = sellPrice;
		this.status = status;
		this.description = description;
		this.floorNo = floorNo;
		this.type = type;
		this.coord = coord;
	}

	public int getId() {
		return id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPoint getCoord() {
		return coord;
	}

	public void setCoord(GeoPoint coord) {
		this.coord = coord;
	}

	public boolean isPremuim() {
		return isPremuim;
	}

	public void setPremuim(boolean isPremuim) {
		this.isPremuim = isPremuim;
	}

}
