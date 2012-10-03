package aaqary.MobileApp.MainPackage;

import java.util.ArrayList;

import aaqary.MobileApp.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class UserDataEditingActivity extends Activity {
	public static User curUser;
	public static UserProfileActivity th;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editdata);

		TextView helloU = (TextView) findViewById(R.id.helloUser);
		helloU.setText("Hello, " + curUser.getUserName());
		CheckBox cB;
		cB = (CheckBox) findViewById(R.id.userNameCheckBox);
		cB.append(curUser.getUserName());

		cB = (CheckBox) findViewById(R.id.passwordCheckBox);
		cB.setText(cB.getText().toString() + curUser.getPassword());

		cB = (CheckBox) findViewById(R.id.fullNameCheckBox);
		cB.setText(cB.getText().toString() + curUser.getFullName());

		cB = (CheckBox) findViewById(R.id.phoneCheckBox);
		cB.setText(cB.getText().toString() + curUser.getPhoneNo());

		cB = (CheckBox) findViewById(R.id.mobileCheckBox);
		cB.setText(cB.getText().toString() + curUser.getMobileNo());

		cB = (CheckBox) findViewById(R.id.emailCheckBox);
		cB.setText(cB.getText().toString() + curUser.geteMail());

		cB = (CheckBox) findViewById(R.id.creditCardCheckBox);
		cB.setText(cB.getText().toString() + curUser.getCreditNo());
	}

	public void onClickOk(View v) {
		int no = 0;
		CheckBox cB;
		ArrayList<Boolean> b = new ArrayList<Boolean>();
		cB = (CheckBox) findViewById(R.id.userNameCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		cB = (CheckBox) findViewById(R.id.passwordCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		cB = (CheckBox) findViewById(R.id.fullNameCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		cB = (CheckBox) findViewById(R.id.phoneCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		cB = (CheckBox) findViewById(R.id.mobileCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		cB = (CheckBox) findViewById(R.id.emailCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		cB = (CheckBox) findViewById(R.id.creditCardCheckBox);
		if (cB.isChecked())
			no++;
		b.add(cB.isChecked());
		if (no != 0) {
			startActivity(new Intent(this, EditCertainDataActivity.class));
			EditCertainDataActivity.addElement(b);
			EditCertainDataActivity.user = curUser;
		}
		EditCertainDataActivity.th=th;
		this.finish();
	}

	public void onClickCancel(View v) {
		this.finish();
	}

}
