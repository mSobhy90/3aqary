package aaqary.MobileApp.GoogleMaps;

import java.util.ArrayList;

import aaqary.MobileApp.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

class MapOverlay extends Overlay {

	GoogleMapsActivity th;
	GeoPoint p;
	public static ArrayList<GeoPoint> mPoints = new ArrayList<GeoPoint>();

	public MapOverlay(GoogleMapsActivity th, GeoPoint p) {
		super();
		this.th = th;
		this.p = p;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,
			long when) {
		super.draw(canvas, mapView, shadow);

		// ---translate the GeoPoint to screen pixels---
		Point screenPts = new Point();
		mapView.getProjection().toPixels(p, screenPts);

		// ---add the marker---
		Bitmap bmp = BitmapFactory.decodeResource(th.getResources(),
				R.drawable.androidmarker);
		canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 50, null);

		return true;
	}

}