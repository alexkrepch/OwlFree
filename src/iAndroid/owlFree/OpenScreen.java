package iAndroid.owlFree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class OpenScreen extends Activity{

	@Override
	protected void onCreate(Bundle theOpenScreen) {
		// TODO Auto-generated method stub
		super.onCreate(theOpenScreen);
		setContentView(R.layout.open);
		Thread timer = new Thread(){
			public void run(){
				try {
					sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
					//finish();
					e.printStackTrace();
					
				}
				finally{
					Intent i = new Intent("iAndroid.owlFree.NEWOREXISTPROFILE");  
					startActivity(i);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

	
}
