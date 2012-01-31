package iAndroid.owlFree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NewOrExistProfile extends Activity implements OnClickListener {

	Button newProfile,existProfile;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_or_exist_profile);
		newProfile =(Button)findViewById(R.id.bNew);
		existProfile=(Button)findViewById(R.id.bExist);
		newProfile.setOnClickListener(this);
		existProfile.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		switch (v.getId()){
		case R.id.bNew:
			Intent i = new Intent("iAndroid.owlFree.OWLFREE");
			startActivity(i);
			
			
			
			break;
		case R.id.bExist:
			
			Intent selectProfileScreen = new Intent("iAndroid.owlFree.EXSITPROFILECHOOSE");
			startActivity(selectProfileScreen);
			
			break;
		}
		
	}
	
	

}
