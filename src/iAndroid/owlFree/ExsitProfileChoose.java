package iAndroid.owlFree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ExsitProfileChoose extends Activity implements OnItemSelectedListener{

	
	
	Spinner profileList;
	String[] profileNames;
	Button select;
	EditText profDet;
	int wight ;
	boolean userGender;
	String selected ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exist_profile_choose_screen);
		
		select =(Button)findViewById(R.id.bSelectProfile);
		
		profileList = (Spinner)findViewById(R.id.spPRofile);
		Profile p = new Profile(this);
		p.open();
		int numOfRows =p.getNumofRows();
		String [] profileNames = new String[numOfRows];
		p.setArray(profileNames);
		p.close();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,profileNames);
		profileList.setAdapter(adapter);
		profileList.setOnItemSelectedListener(this);
		
		select.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				
				
				Intent openDrinkScreen = new Intent("iAndroid.owlFree.ADDDRINKSCREEN");
				
				openDrinkScreen.putExtra("name",selected);
				openDrinkScreen.putExtra("gender",userGender);
				openDrinkScreen.putExtra("weight",wight);
				
				startActivity(openDrinkScreen);
				
			}
		});
		
		
}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		
		selected = parent.getItemAtPosition(pos).toString();
		Profile p = new Profile(this);
		p.open();
		String userWightTxt=  p.getWightByName(selected);
		String userGenderTxt= p.getGenderByName(selected);
		
		p.close();
		
		wight = Integer.parseInt(userWightTxt);
		userGender=true;
		if (userGenderTxt.compareTo("male")!=0)
			userGender=false;
		
		
		
		
	//	profDet.setText(selected+" "+userWightTxt+" "+userGenderTxt);
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
