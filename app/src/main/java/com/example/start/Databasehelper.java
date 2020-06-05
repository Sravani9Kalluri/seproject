package com.example.start;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.Toast;

public class Databasehelper extends SQLiteOpenHelper {
    private static final String DATA_BASE_NAME ="tracker";
    SharedPreferences sp;
    SQLiteDatabase db;
    private Context appcontext;

    //SQLiteDatabase db = this.getWritableDatabase();

    private static final int DATA_BASE_VERSION = 17;
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
            + T_SHOP  + " TEXT );";


    String CREATE_C_TABLE ="CREATE TABLE " + CAT_TABLE   + "("

            + C_CAT + " TEXT, "+ C_LIMIT + " INTEGER, " + C_AMOUNT + " INTEGER," +
            C_DEFAULT + " INTEGER );";

    String CREATE_S_TABLE ="CREATE TABLE " + SHOP_TABLE + "(" + S_SHOP + " TEXT," + S_CATEGORY + " TEXT );";
    String CREATE_R_TABLE = "CREATE TABLE " + REGEX_TABLE + "(" + R_REGEX + " TEXT," + R_CATEGORY + " TEXT );";
    public Databasehelper(Context context) {

        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
        this.appcontext = context;
        Log.e("hello","kello");
        //db = this.getWritableDatabase();
        //db.close();


    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        sp = PreferenceManager.getDefaultSharedPreferences(appcontext);
        Log.e("db on create call","called");
        db.execSQL(CREATE_C_TABLE);
        db.execSQL(CREATE_S_TABLE);
        db.execSQL(CREATE_T_TABLE);
        db.execSQL(CREATE_R_TABLE);
        Log.e("db on create call","called");
        ContentValues cv = new ContentValues();
        cv.put(C_CAT, "food and necessities");
        cv.put(C_LIMIT, 0);
        cv.put(C_AMOUNT, 0);
        cv.put(C_DEFAULT, 1);
        ContentValues cp = new ContentValues();
        cp.put(C_CAT, "entertainment");
        cp.put(C_LIMIT, 0);
        cp.put(C_AMOUNT, 0);
        cp.put(C_DEFAULT, 1);
        ContentValues cg = new ContentValues();
        cg.put(C_CAT, "bills");
        cg.put(C_LIMIT, 0);
        cg.put(C_AMOUNT, 0);
        cg.put(C_DEFAULT, 1);
        ContentValues cb = new ContentValues();
        cb.put(C_CAT, "travel expenses");
        cb.put(C_LIMIT, 0);
        cb.put(C_AMOUNT, 0);
        cb.put(C_DEFAULT, 1);
        db.insert(CAT_TABLE,null,cv);
        db.insert(CAT_TABLE,null,cp);
        db.insert(CAT_TABLE,null,cb);
        db.insert(CAT_TABLE,null,cg);
        ContentValues cs1 = new ContentValues();
        cs1.put(S_SHOP, "aishwarya mart");
        cs1.put(S_CATEGORY, "food and necessities");
        ContentValues cs2  = new ContentValues();
        cs2.put(S_SHOP, "IRCTC");
        cs2.put(S_CATEGORY, "travel expenses");
        ContentValues cs3  = new ContentValues();
        cs3.put(S_SHOP, "ashoka restaurant");
        cs3.put(S_CATEGORY, "entertainment");
        db.insert(SHOP_TABLE,null,cs1);
        db.insert(SHOP_TABLE,null,cs2);
        db.insert(SHOP_TABLE,null,cs3);




        Toast.makeText(appcontext,"hello4",Toast.LENGTH_SHORT).show();
        Log.e("db on create call","called1");



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

    public List<List<String>> getCategories(){
        Log.e("db on create call","called3");
        String selectQuery = "SELECT  * FROM " + CAT_TABLE;
        SQLiteDatabase dbt = this.getReadableDatabase();
        Cursor cursor = dbt.rawQuery(selectQuery, null);
        Log.e("db on create call","called4");
        List<List<String>> result = new ArrayList<>();
        List<String> categories = new ArrayList<>();
        List<String> amount = new ArrayList<>();
        List<String> limit = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
                limit.add(cursor.getString(1));
                amount.add(cursor.getString(2));
                // Adding contact to list
            } while (cursor.moveToNext());

            result.add(categories);
            result.add(limit);
            result.add(amount);
        }

        cursor.close();

        Toast.makeText(appcontext,"hello4",Toast.LENGTH_SHORT).show();
        // return count
        dbt.close();
        return result;


    }

    public List<List<String>> getshops(){
        Log.e("db on create call","called6");
        String selectQuery = "SELECT  * FROM " + SHOP_TABLE;
        SQLiteDatabase dbt = this.getReadableDatabase();
        Cursor cursor = dbt.rawQuery(selectQuery, null);
        Log.e("db on create call","called4");
        List<List<String>> result = new ArrayList<>();
        List<String> sname = new ArrayList<>();
        List<String> scat = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                sname.add(cursor.getString(0));
                scat.add(cursor.getString(1));
                // Adding contact to list
            } while (cursor.moveToNext());

            result.add(sname);
            result.add(scat);

        }

        cursor.close();
        dbt.close();
        return result;


    }




    public String findCategory(String shop){

        return "bills";
    }
    public void addtransaction(){     //String amount,String shop,String date){
        //String category = findCategory(shop);
        SQLiteDatabase db = this.getWritableDatabase();
        String updatequery = "UPDATE "+ CAT_TABLE + " SET " + C_AMOUNT + " = "+ C_AMOUNT + "+ 100 WHERE " +  C_CAT + " = 'bills'";
        db.execSQL(updatequery);




    }



}
