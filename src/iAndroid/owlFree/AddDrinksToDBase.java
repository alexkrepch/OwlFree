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
import android.widget.Toast;

public class AddDrinksToDBase extends Activity implements OnClickListener {

	Button sqlView, sqlUpdate, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlAlc, sqlMl, sqlRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_to_db);

		sqlName = (EditText) findViewById(R.id.etDrinkName);
		sqlAlc = (EditText) findViewById(R.id.etAlc);
		sqlMl = (EditText) findViewById(R.id.etMl);
		sqlRow = (EditText) findViewById(R.id.etSqlRowInfo);
		sqlView = (Button) findViewById(R.id.bView);
		sqlUpdate = (Button) findViewById(R.id.bUpdate);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlDelete = (Button) findViewById(R.id.bDeleteEntry);

		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bUpdate:
			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String alc = sqlAlc.getText().toString();
				String ml = sqlMl.getText().toString();
				Drink entry = new Drink(AddDrinksToDBase.this);
				entry.open();
				entry.createEntry(name, alc, ml);
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
		case R.id.bView:

			Intent i = new Intent("iAndroid.owlFree.DRINKVIEW");
			startActivity(i);

			break;
		case R.id.bGetInfo:
			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);
				Drink d = new Drink(this);
				d.open();
				String returnedName = d.getName(l);
				String returnedAlc = d.getAlc(l);
				String returnedML = d.getMl(l);
				d.close();
				sqlName.setText(returnedName);
				sqlAlc.setText(returnedAlc);
				sqlMl.setText(returnedML);

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

		case R.id.bSQLmodify:
			try {
				String sRow = sqlRow.getText().toString();
				long lRow = Long.parseLong(sRow);
				String mName = sqlName.getText().toString();
				String mAlc = sqlAlc.getText().toString();
				String mMl = sqlMl.getText().toString();

				Drink dModify = new Drink(this);
				dModify.open();
				dModify.updateEntry(lRow, mName, mAlc, mMl);
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

		case R.id.bDeleteEntry:
			try {
				String sDel = sqlRow.getText().toString();
				long lDel = Long.parseLong(sDel);
				Drink dDel = new Drink(this);
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
