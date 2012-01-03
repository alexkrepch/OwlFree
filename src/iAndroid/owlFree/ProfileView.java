package iAndroid.owlFree;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_view);
		TextView tv = (TextView)findViewById(R.id.tvProfileInfo);

		Profile info = new Profile(this);
		info.open();
		String data = info.gatData();
		info.close();
		tv.setText(data);

	}

}
