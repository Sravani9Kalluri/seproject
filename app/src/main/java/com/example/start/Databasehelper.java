package com.example.start;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Databasehelper extends SQLiteOpenHelper {
    private static final String DATA_BASE_NAME ="tracker";
    SQLiteDatabase db;


    private static final int DATA_BASE_VERSION = 1;
    Cursor cursor;

    //table name
    public static final String TRAN_TABLE = "tran_tab";
    public static final String CAT_TABLE = "category_tab";
    public static final String SHOP_TABLE ="shop_tab";
    public static final String REGEX_TABLE = "regex_tab";


    //column name of transaction_tab
    public static final String T_ID ="t_id";
    public static final String T_AMOUNT = "t_amount";
    public static final String T_DATE = "t_date";
    public static final String T_CATEGORY = "t_category";
    public static final String T_SHOP = "t_shop";

    //column name of regex_table
    public static final String R_REGEX = "regex";
    public static final String R_CATEGORY = "category";

    //column name for the shop_table
    public static final String S_SHOP ="s_name";
    public static final String S_CATEGORY = "s_cat";

    //column name for the category_tabLE
    public static String C_CAT = "c_cat";
    public static String C_LIMIT = "c_limit";
    public static String C_AMOUNT = "c_amount";
    public static String C_DEFAULT = "c_def";


    String CREATE_T_TABLE = "CREATE TABLE " + TRAN_TABLE + "("
            + T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + T_AMOUNT + " INTEGER,"
            + T_DATE + " TEXT," + T_CATEGORY + " TEXT,"
            + T_SHOP + " TEXT," + " TEXT );";


    String CREATE_C_TABLE ="CREATE TABLE " + CAT_TABLE   + "("

            + C_CAT + " TEXT, "+ C_LIMIT + " INTEGER, " + C_AMOUNT + " INTEGER," +
            C_DEFAULT + "INTEGER );";

    String CREATE_S_TABLE ="CREATE TABLE " + SHOP_TABLE + "(" + S_SHOP + " TEXT," + S_CATEGORY + "TEXT );";
    String CREATE_R_TABLE = "CREATE TABLE " + REGEX_TABLE + "(" + R_REGEX + " TEXT," + R_CATEGORY + "TEXT );";
    public Databasehelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        db = this.getWritableDatabase();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_C_TABLE);
        db.execSQL(CREATE_S_TABLE);
        db.execSQL(CREATE_T_TABLE);
        db.execSQL(CREATE_R_TABLE);
        //Log.e("db on create call","called");
        ContentValues cv = new ContentValues();
        cv.put(C_CAT, "cat1");
        cv.put(C_LIMIT, 0);
        cv.put(C_AMOUNT, 0);
        cv.put(C_DEFAULT, 1);
        ContentValues cp = new ContentValues();
        cp.put(C_CAT, "cat2");
        cp.put(C_LIMIT, 0);
        cp.put(C_AMOUNT, 0);
        cp.put(C_DEFAULT, 1);
        ContentValues cg = new ContentValues();
        cg.put(C_CAT, "cat3");
        cg.put(C_LIMIT, 0);
        cg.put(C_AMOUNT, 0);
        cg.put(C_DEFAULT, 1);
        ContentValues cb = new ContentValues();
        cb.put(C_CAT, "cat4");
        cb.put(C_LIMIT, 0);
        cb.put(C_AMOUNT, 0);
        cb.put(C_DEFAULT, 1);
        db = this.getWritableDatabase();
        db.insert(CAT_TABLE,null,cv);
        db.insert(CAT_TABLE,null,cp);
        db.insert(CAT_TABLE,null,cb);
        db.insert(CAT_TABLE,null,cg);

        db.close();



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + CAT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + REGEX_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SHOP_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRAN_TABLE);
        // Create tables again
        onCreate(db);
    }





}
