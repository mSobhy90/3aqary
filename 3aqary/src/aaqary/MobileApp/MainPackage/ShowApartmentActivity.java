package aaqary.MobileApp.MainPackage;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class ShowApartmentActivity extends Activity {
	private ImageAdapter imgAd;
	private Boolean flag = false;
	private ShowApartmentActivity th=this;
	public static PlaceDataBase placesDB=new PlaceDataBase();
	public static User curUser;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showapartment);
		imgAd = new ImageAdapter(this);
		// Todo: Must add Drawables to image adapter before using it. example:
		// imgAd.addDrawable(R.id.imageView2);
		GridView gridview = (GridView) findViewById(R.id.gridview);
		for (int i = 0; i < placesDB.size(); i++)
			imgAd.addDrawable(R.drawable.icon);
		imgAd.setDrawable(1, R.drawable.androidmarker);
		gridview.setAdapter(imgAd);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (flag) {// then delete was clicked
					imgAd.removeDrawable(position);
					imgAd.notifyDataSetChanged();
					placesDB.remove(position);
				} else {
					ViewPlaceActivity.myPlace=placesDB.get(position);
					ViewPlaceActivity.owner=curUser;
					ViewPlaceActivity.flag=0;
					startActivity(new Intent(th,ViewPlaceActivity.class));
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menuItem;
		menuItem = menu.add(0, 0, 0, "Delete: On");
		menuItem.setIcon(R.drawable.ic_tab_albums_white);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.view_place_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		// case R.id.animateTo:
		case 0:
			flag = !flag;
			if (flag) {
				item.setIcon(R.drawable.ic_tab_albums_grey);
				item.setTitle("Delete: Off.");
			} else {
				item.setIcon(R.drawable.ic_tab_albums_white);
				item.setTitle("Delete: On.");
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressWarnings("unused")
	private void showMsg(String message) {
		Toast msg = Toast.makeText(ShowApartmentActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		this.finish();
	}

}
