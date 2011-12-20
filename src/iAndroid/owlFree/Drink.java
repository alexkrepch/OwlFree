package iAndroid.owlFree;

public class Drink 
{
	private double vol; // אחוז אלכוהול
	private int ml;		// כמות
	private int id;
	private char name;
	
	public Drink (double input_vol, int input_ml, int input_id, char input_name )  // בנאי
	{
		this.vol = input_vol;
		this.ml = input_ml;
		this.id = input_id;
		this.name = input_name;
	}
	
	public double getVol()	{	return this.vol;	}
	public int getMl()		{	return this.ml;		}
	public int getId()		{	return this.id;		}
	public char getName() 	{	return this.name;	}
}


