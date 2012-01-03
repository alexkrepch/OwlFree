package iAndroid.owlFree;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DrinkView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_view);
		TextView tv = (TextView)findViewById(R.id.tvDrinksInfo);
		Drink info = new Drink(this);
		info.open();
		String data = info.gatData();
		info.close();
		tv.setText(data);
		
	}
	
	

}
