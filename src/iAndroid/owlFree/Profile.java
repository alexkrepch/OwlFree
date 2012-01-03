package iAndroid.owlFree;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Profile
{
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "profileName";
	public static final String KEY_WIGHT = "profileWight";
	public static final String KEY_GENDER = "profileGender";
	public static final String KEY_ALC = "profileAlc";
	public static final String KEY_REF = "profileRef";
	private static final String DATABASE_NAME = "profileDb";
	private static final String DATABASE_TABLE = "profileTable";
	private static final int DATABASE_VERSION = 3;
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	
	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_WIGHT + " TEXT NOT NULL, "
					+ KEY_GENDER + " TEXT NOT NULL, " + KEY_ALC + " TEXT NOT NULL, "
					+ KEY_REF + " TEXT NOT NULL);");
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
			
		}
	
	}
	public Profile(Context c) {
		ourContext = c;
	}
	
	public Profile open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close() {
		ourHelper.close();
	}
	
	long createEntry(String name, String wight, String gender) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_WIGHT, wight);
		cv.put(KEY_GENDER, gender);
		
		cv.put(KEY_ALC, "0");
		long time = System.currentTimeMillis();
		String s = Long.toString(time);
		cv.put(KEY_REF, s);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public String gatData() {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER,KEY_ALC,KEY_REF };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iWight = c.getColumnIndex(KEY_WIGHT);
		int iGender = c.getColumnIndex(KEY_GENDER);
		int iAlc = c.getColumnIndex(KEY_ALC);
		int iRef = c.getColumnIndex(KEY_REF);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName)
					+ " " + c.getString(iWight) + " " + c.getString(iGender) + " "+c.getString(iAlc) + " "+c.getString(iRef)+ "\n";
		}
		return result;
	}
	
	public int getRowId(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			int r = c.getInt(0);
			return r;
		}
		return 0;
	}
	
	public String getName(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}
	
	public String getWight(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String alc = c.getString(2);
			return alc;
		}
		return null;
	}
	
	public String getGender(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String ml = c.getString(3);
			return ml;
		}
		return null;
	}
	
	public void updateEntry(long lRow, String mName, String mWight, String mGender) {
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_WIGHT, mWight);
		cvUpdate.put(KEY_GENDER, mGender);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
				null);
	}
	
	public void deleteEntry(long lDel) {
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lDel, null);

	}
	
	public int getNumofRows() {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		int num = c.getCount();
		return num;

	}
	
	public void setArray(String[] profileNames) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);

		long l = 0;
		int iName = c.getColumnIndex(KEY_NAME);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			{
				profileNames[(int) l] = c.getString(iName);
				l++;
			}
		}
	}

	public String getWightByName(String selected) {
		
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String s;
		int iWight = c.getColumnIndex(KEY_WIGHT);
		int iName = c.getColumnIndex(KEY_NAME);

		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				if (c.getString(iName).compareTo(selected) == 0) {
					s = c.getString(iWight);
				
					return s;
				}
			}
		}
		
		
		return null;
	}

	public String getGenderByName(String selected) {

		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String s;
		int iGender = c.getColumnIndex(KEY_GENDER);
		int iName = c.getColumnIndex(KEY_NAME);

		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				if (c.getString(iName).compareTo(selected) == 0) {
					s = c.getString(iGender);
				
					return s;
				}
			}
		}
		
		
		return null;
	}

	public void updateAlc(String userName,double a) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER, KEY_ALC, KEY_REF };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		
		int iName = c.getColumnIndex(KEY_NAME);
		int iAlc = c.getColumnIndex(KEY_ALC);
		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if (c.getString(iName).compareTo(userName) == 0) {
					String t =  c.getString(iAlc);
					//refresh a
					a=a+Double.parseDouble(t);
					ContentValues cvUpdate = new ContentValues();
					
					
				/*	
					cvUpdate.put(KEY_ALC, );
					cvUpdate.put(KEY_REF, );
					ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
							null);
					*/
					
				}
			}
		}
		
	}
	public void refreshAlc(String selected)
	{
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_WIGHT, KEY_GENDER,KEY_ALC,KEY_REF };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String s;
		int iGender = c.getColumnIndex(KEY_GENDER);
		int iName = c.getColumnIndex(KEY_NAME);
		int iWight = c.getColumnIndex(KEY_WIGHT);
		int iTime = c.getColumnIndex(KEY_REF);
		int iAlc = c.getColumnIndex(KEY_ALC);
		
		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				if (c.getString(iName).compareTo(selected) == 0) {
					long oldTime = Long.parseLong(c.getString(iTime));
					long curentTime = System.currentTimeMillis();
					long changeTime = (curentTime-oldTime)/(1000*3600);
					
							
				
					
				}
			}
		}

	}
	
}



