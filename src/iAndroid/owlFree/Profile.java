package iAndroid.owlFree;

import android.R.bool;

public class Profile
{
	private boolean male;	//��� = TRUE
	private int wight;		// ����
	private String name;		// ��
	private double r;		
	
	public Profile(boolean input_male, int input_wight, String input_name) // ����
	{
		this.male=input_male;
		this.wight=input_wight;
		this.name=input_name;
		if (this.male)
			this.r=0.73;	// �� ���
		else 
			this.r=0.66;
	}
	
	public boolean getMale() {	return this.male;	}
	public int getWight()	 {	return this.wight;	}
	public String getName()	 {	return this.name;	}
	public double getR()   	 {	return this.r;  	}

}
