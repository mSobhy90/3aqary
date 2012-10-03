package aaqary.MobileApp.MainPackage;

import java.util.ArrayList;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditCertainDataActivity extends Activity {
	public static UserProfileActivity th;
	
	/** Called when the activity is first created. */
	private String[] userData = { "User Name: ", "Password: ", "Name: ",
			"Phone: ", "Mobile: ", "Email: ", "Credit Card No.: " };
	private ArrayList<String> valueData = new ArrayList<String>(7);
	private ArrayList<EditText> editTexts = new ArrayList<EditText>();
	public static ArrayList<Boolean> checkedData;
	public static User user;
	public static UsersDataBase usersDB;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editcertaindata);
		LinearLayout submitScoreLayout = (LinearLayout) findViewById(R.id.linearLayout2);
		LayoutInflater inflater = getLayoutInflater();
		usersDB=SignInActivity.usersDB;
		View temp;
		ArrayList<String> usersData = user.getParams();
		for (int i = 0; i < checkedData.size(); i++) {
			if (checkedData.get(i)) {
				temp = inflater.inflate(R.layout.textview, null);
				editTexts.add((EditText) temp.getFocusables(
						View.FOCUSABLES_TOUCH_MODE).get(0));
				submitScoreLayout.addView(temp);
				TextView tv = (TextView) temp.findViewById(R.id.testTextView);
				tv.setText(userData[i]);
				valueData.add("");
			} else
				valueData.add(usersData.get(i));
		}
	}

	@SuppressWarnings("unused")
	private void showMsg(String message) {
		Toast msg = Toast.makeText(EditCertainDataActivity.this, message,
				Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}

	public static void addElement(ArrayList<Boolean> b) {
		checkedData = (b);
	}

	public void onSubmitClick(View V) {
		for (int i = 0; i < editTexts.size(); i++) {
			valueData.set(checkedData.indexOf(true), editTexts.get(i).getText()
					.toString());
			checkedData.set(checkedData.indexOf(true), false);
		}
		user.setAllUserData(valueData);
		usersDB.replaceUser(user);
		checkedData.clear();
		valueData.clear();
		editTexts.clear();
		th.finish();
		UserProfileActivity.curUser=EditCertainDataActivity.user;
		AaqaryActivity.i=1;
		startActivity(new Intent(this,AaqaryActivity.class));
		this.finish();
	}

	public void onCancelClick(View V) {
		this.finish();
	}
}
