package iAndroid.owlFree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Drink {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "dirnkName";
	public static final String KEY_ALC = "drinkAlc";
	public static final String KEY_ML = "drinkMl";
	private static final String DATABASE_NAME = "drinkDb";
	private static final String DATABASE_TABLE = "drinkTable";
	private static final int DATABASE_VERSION = 7;
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_ALC + " TEXT NOT NULL, "
					+ KEY_ML + " TEXT NOT NULL);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);

		}
	}

	public Drink(Context c) {
		ourContext = c;
	}

	public Drink open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	long createEntry(String name, String alc, String ml) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_ALC, alc);
		cv.put(KEY_ML, ml);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String gatData() {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iAlc = c.getColumnIndex(KEY_ALC);
		int iMl = c.getColumnIndex(KEY_ML);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iRow) + " " + c.getString(iName)
					+ " " + c.getString(iAlc) + " " + c.getString(iMl) + "\n";
		}
		return result;
	}

	public String getName(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String name = c.getString(1);
			return name;
		}
		return null;
	}

	public String getAlc(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String alc = c.getString(2);
			return alc;
		}
		return null;
	}

	public double getAlcByName(String selected) {

		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		double a;
		String s;
		int iAlc = c.getColumnIndex(KEY_ALC);
		int iName = c.getColumnIndex(KEY_NAME);

		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				if (c.getString(iName).compareTo(selected) == 0) {
					s = c.getString(iAlc);
					a = Double.parseDouble(s);
					return a;
				}
			}
		}
		return 0;
	}

	public double getMLByName(String selected) {

		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		double a;
		String s;
		int iMl = c.getColumnIndex(KEY_ML);
		int iName = c.getColumnIndex(KEY_NAME);

		if (c != null) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

				if (c.getString(iName).compareTo(selected) == 0) {
					s = c.getString(iMl);
					a = Double.parseDouble(s);
					return a;
				}
			}
		}
		return 0;
	}

	public String getMl(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String ml = c.getString(3);
			return ml;
		}
		return null;
	}

	public int getRowId(long l) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "="
				+ l, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			int r = c.getInt(0);
			return r;
		}
		return 0;
	}

	public void updateEntry(long lRow, String mName, String mAlc, String mMl) {
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(KEY_NAME, mName);
		cvUpdate.put(KEY_ALC, mAlc);
		cvUpdate.put(KEY_ML, mMl);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow,
				null);
	}

	public void deleteEntry(long lDel) {
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lDel, null);

	}

	public int getNumofRows() {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);
		int num = c.getCount();
		return num;

	}

	public void setArray(String[] drinksNames) {
		String[] columns = new String[] { KEY_ROWID, KEY_NAME, KEY_ALC, KEY_ML };
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,
				null, null);

		long l = 0;
		int iName = c.getColumnIndex(KEY_NAME);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			{
				drinksNames[(int) l] = c.getString(iName);
				l++;
			}
		}
	}

	public void init() {
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_NAME, "water");
		cv.put(KEY_ALC, "0");
		cv.put(KEY_ML, "100");
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		cv.clear();
		
		cv.put(KEY_NAME, "arak");
		cv.put(KEY_ALC, "40");
		cv.put(KEY_ML, "50");
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		cv.clear();
		
		cv.put(KEY_NAME, "wine");
		cv.put(KEY_ALC, "11");
		cv.put(KEY_ML, "120");
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		cv.clear();
		cv.put(KEY_NAME, "beer");
		cv.put(KEY_ALC, "4");
		cv.put(KEY_ML, "500");
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		cv.clear();
		cv.put(KEY_NAME, "cocktail");
		cv.put(KEY_ALC, "13");
		cv.put(KEY_ML, "150");
		ourDatabase.insert(DATABASE_TABLE, null, cv);
		cv.clear();
		
		
	}

	
}
