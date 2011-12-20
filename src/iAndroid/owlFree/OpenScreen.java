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
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
					//finish();
					e.printStackTrace();
					
				}
				finally{
					Intent openProfileScreen = new Intent("iAndroid.owlFree.OWLFREE");  
					startActivity(openProfileScreen);
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
