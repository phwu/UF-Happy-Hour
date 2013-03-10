package com.example.uf_happy_hour;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;


/**
 * @author Polly
 * This will create and manage our database of bars and their info
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static String DB_PATH = "/data/data/uf_happy_hour/databases/";

    private static String DB_NAME = "UFHappyHourDB.sqlite";
	
	// Update per change
	static final String dbName = "UFHappyHourDB";
	static final int ver = 1;
	
	// Bars Table
	static final String BarsTable = "BarsTable";
	static final String colName = "BarName";
	static final String colAddress = "BarAddress";
	/*static final String colPhone = "PhoneNumber";
	static final String colType = "TypeOfVenue";
	static final String colHoursOpen = "HoursOpen";
	static final String colAgeLimit = "AgeLimit";
	static final String colFood = "Food";
	static final String colFullBar = "FullBar";
	*/

	// Happy Hours Table
	/*static final String deptTable = "Dept";
	static final String colDeptID = "DeptID";
	static final String colDeptName = "DeptName";

	static final String viewEmps = "ViewEmps";
	*/
	private SQLiteDatabase db = null;
	Context context;
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, ver); 
	}
	
	public boolean createDataBase() throws IOException
    {

        boolean dbExist = checkDataBase();

        if (dbExist)
        { // do nothing - database already exist
            return false;
        }
        else
        {
            this.getReadableDatabase();
            return true;
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     * 
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;

        try
        {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e)
        {
            //db doesn't exist
        }

        if (checkDB != null)
        {
            checkDB.close();
        }

        return checkDB != null;
    }

    /**
     * @throws SQLException
     */
    public void openDataBase() throws SQLException
    {
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("CREATE TABLE "+ BarsTable + "(" + colName + " TEXT PRIMARY KEY, "+
			    colAddress+ " TEXT)");
			  
		/*	  db.execSQL("CREATE TABLE "+BarsTable+" 
			    ("+colID+" TEXT PRIMARY KEY, "+
			        colName+" TEXT, "+colAge+" Integer, "+colDept+" 
			    INTEGER NOT NULL ,FOREIGN KEY ("+colDept+") REFERENCES 
			    "+deptTable+" ("+colDeptID+"));");
			  
			  
			  db.execSQL("CREATE TRIGGER fk_empdept_deptid " +
			    " BEFORE INSERT "+
			    " ON "+employeeTable+
			    
			    " FOR EACH ROW BEGIN"+
			    " SELECT CASE WHEN ((SELECT "+colDeptID+" FROM "+deptTable+" 
			    WHERE "+colDeptID+"=new."+colDept+" ) IS NULL)"+
			    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
			    "  END;");
			  
			  db.execSQL("CREATE VIEW "+viewEmps+
			    " AS SELECT "+employeeTable+"."+colID+" AS _id,"+
			    " "+employeeTable+"."+colName+","+
			    " "+employeeTable+"."+colAge+","+
			    " "+deptTable+"."+colDeptName+""+
			    " FROM "+employeeTable+" JOIN "+deptTable+
			    " ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID
			    );
			    
			  */
		
		//Inserts pre-defined departments
		insertBarInfo(db, context);  	

	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		/*
		db.execSQL("DROP TABLE IF EXISTS "+employeeTable);
		db.execSQL("DROP TABLE IF EXISTS "+deptTable);
		  
		db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger");
		db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22");
		db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
		db.execSQL("DROP VIEW IF EXISTS "+viewEmps);
		onCreate(db);
		*/
	}
	
	public void insertBarInfo(SQLiteDatabase db, Context context) {
		try {
			// may need to do this another way...
			AssetManager am = context.getAssets();
			InputStream is = am.open("bars.txt");
			InputStreamReader irs = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(irs);
		
			try {
				ContentValues cv = new ContentValues();
				String input = null;
				
				input = br.readLine();
				while (input != null) {
					StringTokenizer st = new StringTokenizer(input, ",");
					
					while (st.hasMoreElements()) {
						cv.put(colName, st.nextToken());	// String
						cv.put(colAddress, st.nextToken()); // String
						//cv.put(colPhone, st.nextToken());	// Integer
						//cv.put(colType, st.nextToken());	// String
						
						
						db.insert(BarsTable, null, cv);
					}
				}
			
			}
			finally {
				// Close everything
				db.close();
				br.close();
				irs.close();
				is.close();
				am.close();
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public Bar getBarInfo() {
		Bar bar = new Bar();
		
		return bar;
	}

	
	// end
}
