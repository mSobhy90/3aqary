package aaqary.MobileApp.GoogleMaps;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchByDistanceActivity extends Activity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.dataentry);

	}

	public void submitDistance(View V) {
		EditText to = (EditText) findViewById(R.id.editText1);
		GoogleMapsActivity.toDistance=(Double.parseDouble(to.getText().toString())*1000);
		GoogleMapsActivity.setNewOverlay(false);
		this.finish();
	}
}
