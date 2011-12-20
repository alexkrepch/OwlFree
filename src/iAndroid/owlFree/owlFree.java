package iAndroid.owlFree;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class owlFree extends Activity {
   
	Button create;
	EditText name,weight; 
	TextView nameLabel;
	CheckBox gender;
	int userWeight;
	RadioButton male,female;
	
	/** Called when the activity is first created. */
    @Override
    
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        create = (Button) findViewById(R.id.bCreate);
        name = (EditText) findViewById(R.id.etName);
        weight = (EditText)findViewById(R.id.etWeight);
        nameLabel = (TextView)findViewById(R.id.tvName);
        male = (RadioButton)findViewById(R.id.rbMale);
        female = (RadioButton)findViewById(R.id.rbFemale);
    //    gender = (CheckBox)findViewById(R.id.cbMale);
        
        create.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				String userName = name.getText().toString();
				String userWeightText = weight.getText().toString();
				boolean userGender;
				if (male.isChecked())
					userGender=true;
				else
					userGender=false;
				try	{
					userWeight= Integer.parseInt(userWeightText);//atoi
					Profile userPro = new Profile(userGender,userWeight, userName);
					Intent openDrinkScreen = new Intent("iAndroid.owlFree.ADDDRINKSCREEN");  
					startActivity(openDrinkScreen);
				}catch (Exception e) {
					weight.setTextColor(Color.RED);	
				}
			}
		});
        
        weight.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				weight.setTextColor(Color.BLACK);
				weight.setText("");
				
			}
		});
        
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
    
	
}