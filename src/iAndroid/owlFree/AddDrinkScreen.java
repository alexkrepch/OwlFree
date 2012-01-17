package iAndroid.owlFree;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
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
	
	
	DrowGraph ourView;
	TextView title;
	Spinner drinkList;
	//EditText ttt;
	TextView ttt;
	Button dbMenu,dbProfile,addDrink;
	String[] drinksNames;
	String userName;
	double itemAlc,itemMl;
	String selected;
	Profile p = new Profile(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.drink);
		title = (TextView)findViewById(R.id.tvTitle);
		dbMenu = (Button)findViewById(R.id.bDbMenu);
		
		ttt = (TextView)findViewById(R.id.showAlc);
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
				p.open();
				int r = p.getRowByName(userName);
			/*	double oldAlc = Double.parseDouble( p.getAlc(r));
				double oldTime = Double.parseDouble( p.getRef(r));
				double curentTime = System.currentTimeMillis();
				double timeDef =(curentTime-oldTime)/3600000;
				double curentAlc = Double.parseDouble(p.getAlc(r))-0.15*timeDef;
				double curentA = calcA(curentAlc, 0);*/
				p.close();
				
				
				refreshAlc(r);
				p.open();
				double curentAlc = Double.parseDouble(p.getAlc(r));
				double curentA = calcA(curentAlc, 0);
				p.close();
			
			
			
			p.open();	
				double ml,alc;
				ml=itemMl/30;
				alc = itemAlc;
				double a = ml*alc;
				////*****************
				curentA = curentA+ a;
				 curentAlc=calcBAC(curentA, 0);
				p.setAlc(r, curentAlc);
				double curentTime = System.currentTimeMillis();

				p.setTime(r, curentTime);
				
			//	refreshAlc(r);
				String s = p.getRef(r);
				double tmp = curentAlc/100;
						
				String alcToView = (""+tmp);
				alcToView = alcToView.substring(0, 6);
				ttt.setText(alcToView);
				p.close();
				refreshAlc(r);
				
			}
		});
		
		int userWeight = this.getIntent().getExtras().getInt("weight");
		userName = this.getIntent().getExtras().getString("name");
		boolean userGender = this.getIntent().getExtras().getBoolean("gender");
		title.setText("Hi "+userName);
		
		p.open();
		int r = p.getRowByName(userName);
		p.close();
		
		refreshAlc(r);
	/*	double oldAlc = Double.parseDouble( p.getAlc(r));
		double oldTime = Double.parseDouble( p.getRef(r));
		double curentTime = System.currentTimeMillis();
		double timeDef =(curentTime-oldTime)/3600000;
		double curentAlc = Double.parseDouble(p.getAlc(r))-0.15*timeDef;
		if (curentAlc<0)
			curentAlc=0;
		p.setAlc(r, curentAlc);
		p.setTime(r, curentTime);
		String s = p.getRef(r);
	
		
		
		double tmp = curentAlc/100;
		
		String alcToView = (""+tmp);

		ttt.setText(alcToView);
		
		p.close();*/
	}
	
	  public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
	        selected = parent.getItemAtPosition(pos).toString();
	     
	        Drink d = new Drink(this);
			d.open();
			 itemAlc = d.getAlcByName(selected);
			 itemMl = d.getMLByName(selected);
			
			
			d.close();
	  
	        
	    }
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	public double calcBAC(double a,double h)
	{
		
		String wString=p.getWightByName(userName);
		String gString=p.getGenderByName(userName);
		double w = Double.parseDouble(wString);
		double r = 0.69;
		if (gString.compareTo("male")==0)
			r=0.73;
		
		double bac =(a*5.14)/(w*r)-0.15*h;
		if (bac < 0 )
			return 0;
		return bac;
		
	}
	public double calcA(double BAC,double h)
	{	
		String wString=p.getWightByName(userName);
		String gString=p.getGenderByName(userName);
		double w = Double.parseDouble(wString);
		double r = 0.69;
		if (gString.compareTo("male")==0)
			r=0.73;
		double a = ((w*r)*(BAC+0.15*h))/5.14;
		if (a < 0)
			return 0;
		return a;
	}
	
	public void refreshAlc (int r)
	{
		p.open();
		
			double oldAlc = Double.parseDouble( p.getAlc(r));
			double oldTime = Double.parseDouble( p.getRef(r));
			double curentTime = System.currentTimeMillis();
			double timeDef =(curentTime-oldTime)/3600000;
			double curentAlc = Double.parseDouble(p.getAlc(r))-0.15*timeDef;
			if (curentAlc<0)
				curentAlc=0;
			p.setAlc(r, curentAlc);
			p.setTime(r, curentTime);
			String s = p.getRef(r);
			double tmp = curentAlc/100;
			
			if (tmp<0.025)
				ttt.setTextColor(Color.WHITE);
			if (tmp<0.05 && tmp >= 0.025)
				ttt.setTextColor(Color.YELLOW);
			if (tmp > 0.05)
				ttt.setTextColor(Color.RED);
			String alcToView = (tmp+"");
			
			if (alcToView.length()>6)
				alcToView = alcToView.substring(0, 6);
			alcToView= alcToView+ "%";
			ttt.setText(alcToView);
		/*	double h;
			if (tmp > 0.05)
				 h = (0.05- tmp)/(-0.15);
			else
				h=0;
			ttt.setText(alcToView+"   "+h);*/
		p.close();
	}
}



