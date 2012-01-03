package iAndroid.owlFree;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddDrinkScreen extends Activity implements OnItemSelectedListener{
	
	TextView title;
	Spinner drinkList;
	EditText ttt;

	Button dbMenu,dbProfile,addDrink;
	String[] drinksNames;
	String userName;
	double itemAlc,itemMl;
	Profile p = new Profile(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink);
		title = (TextView)findViewById(R.id.tvTitle);
		dbMenu = (Button)findViewById(R.id.bDbMenu);
		ttt = (EditText)findViewById(R.id.etNumOfDrinks);
		dbProfile=(Button)findViewById(R.id.bDbProfile);
		addDrink =(Button)findViewById(R.id.bAddDrink);
		
		Drink d = new Drink(this);
		d.open();
			int numOfRows = d.getNumofRows();
			if (numOfRows==0)
				{
				d.init();
				numOfRows=d.getNumofRows();
				}
			
			String[] drinksNames = new String[numOfRows];
			d.setArray(drinksNames);
		d.close();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,drinksNames);
		drinkList = (Spinner)findViewById(R.id.spDrinks);
		drinkList.setAdapter(adapter);
		drinkList.setOnItemSelectedListener(this);
		
		
		
		
		dbMenu.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent openD =new  Intent("iAndroid.owlFree.ADDDRINKSTODBASE");
				startActivity(openD);
				
			}
		});
		dbProfile.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent openP =new  Intent("iAndroid.owlFree.EDITPROFILEDB");
				startActivity(openP);
				
			}
		});
		
		addDrink.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				double ml,alc;
				ml=itemMl/30;
				alc = itemAlc;
				double a = ml*alc;
				p.open();
				
			//	p.updateAlc(userName);
				p.close();
				
			}
		});
		
		int userWeight = this.getIntent().getExtras().getInt("weight");
		userName = this.getIntent().getExtras().getString("name");
		boolean userGender = this.getIntent().getExtras().getBoolean("gender");
		title.setText("Hi "+userName);
		
	}
	
	  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	        String selected = parent.getItemAtPosition(pos).toString();
	     
	        Drink d = new Drink(this);
			d.open();
			 itemAlc = d.getAlcByName(selected);
			 itemMl = d.getMLByName(selected);
			
			
			d.close();
	       ttt.setText(selected+" "+itemAlc+" "+itemMl);
	        
	    }
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
