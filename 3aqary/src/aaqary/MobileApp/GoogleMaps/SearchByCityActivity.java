package aaqary.MobileApp.GoogleMaps;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchByCityActivity extends Activity {

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.dataentry);
		EditText ed= (EditText) findViewById(R.id.editText1);
		ed.setInputType(InputType.TYPE_CLASS_TEXT);
		TextView tv = (TextView) findViewById(R.id.dataEntryParamText);
		tv.setText("Find places in(City):");
		
		tv = (TextView) findViewById(R.id.textView4);
		tv.setVisibility(View.INVISIBLE);
	}

	public void submitDistance(View V) {
		EditText to = (EditText) findViewById(R.id.editText1);
		GoogleMapsActivity.city = (to.getText().toString());
		GoogleMapsActivity.setNewOverlay(true);
		this.finish();
	}
}
