package iAndroid.owlFree;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfileDB extends Activity implements OnClickListener{
	
	Button sqlView, sqlUpdate, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlWight, sqlGender, sqlRow;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_to_profile_db);
		
		
		sqlName = (EditText) findViewById(R.id.etUserName1);
		sqlWight = (EditText) findViewById(R.id.etWight);
		sqlGender = (EditText) findViewById(R.id.etGender);
		sqlRow = (EditText) findViewById(R.id.etSqlRowInfo1);
		sqlView = (Button) findViewById(R.id.bView1);
		sqlUpdate = (Button) findViewById(R.id.bUpdate1);
		sqlModify = (Button) findViewById(R.id.bSQLmodify1);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo1);
		sqlDelete = (Button) findViewById(R.id.bDeleteEntry1);
		
		
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bUpdate1:
			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String wight = sqlWight.getText().toString();
				String gender = sqlGender.getText().toString();
				Profile entry = new Profile(EditProfileDB.this);
				entry.open();
				entry.createEntry(name, wight, gender);
				entry.close();

			} catch (Exception e) {
				didItWork = false;
				String erorr = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("dang it");
				TextView tv = new TextView(this);
				tv.setText(erorr);
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("yes!");
					TextView tv = new TextView(this);
					tv.setText("success");
					d.setContentView(tv);
					d.show();
				}
			}
			break;
		case R.id.bView1:
			// 
		
			Intent i = new Intent("iAndroid.owlFree.PROFILEVIEW");
			startActivity(i);

			break;
		case R.id.bGetInfo1:
			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);
				Profile p = new Profile(this);
				p.open();
				String returnedName = p.getName(l);
				String returnedWight = p.getWight(l);
				String returnedGender = p.getGender(l);
				p.close();
				sqlName.setText(returnedName);
				sqlWight.setText(returnedWight);
				sqlGender.setText(returnedGender);

			} catch (Exception e) {

				String erorr = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("dang it");
				TextView tv = new TextView(this);
				tv.setText(erorr);
				d.setContentView(tv);
				d.show();

			}
			break;

		case R.id.bSQLmodify1:
			try {
				String sRow = sqlRow.getText().toString();
				long lRow = Long.parseLong(sRow);
				String mName = sqlName.getText().toString();
				String mWight = sqlWight.getText().toString();
				String mGender = sqlGender.getText().toString();

				Profile dModify = new Profile(this);
				dModify.open();
				dModify.updateEntry(lRow, mName, mWight, mGender);
				dModify.close();
			} catch (Exception e) {

				String erorr = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("dang it");
				TextView tv = new TextView(this);
				tv.setText(erorr);
				d.setContentView(tv);
				d.show();

			}
			break;

		case R.id.bDeleteEntry1:
			try {
				String sDel = sqlRow.getText().toString();
				long lDel = Long.parseLong(sDel);
				Profile dDel = new Profile(this);
				dDel.open();
				dDel.deleteEntry(lDel);
				dDel.close();
			} catch (Exception e) {

				String erorr = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("dang it");
				TextView tv = new TextView(this);
				tv.setText(erorr);
				d.setContentView(tv);
				d.show();

			}
			break;
		}
		
	}

}
