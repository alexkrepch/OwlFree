package iAndroid.owlFree;

import android.R.bool;

public class Profile
{
	private boolean male;	//זכר = TRUE
	private int wight;		// משקל
	private String name;		// שם
	private double r;		
	
	public Profile(boolean input_male, int input_wight, String input_name) // בנאי
	{
		this.male=input_male;
		this.wight=input_wight;
		this.name=input_name;
		if (this.male)
			this.r=0.73;	// אם זכר
		else 
			this.r=0.66;
	}
	
	public boolean getMale() {	return this.male;	}
	public int getWight()	 {	return this.wight;	}
	public String getName()	 {	return this.name;	}
	public double getR()   	 {	return this.r;  	}

}
