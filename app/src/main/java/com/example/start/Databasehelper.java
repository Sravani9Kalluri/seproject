package com.example.start;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;
import android.widget.Toast;
import java.util.Calendar;
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
        String salaryst = sp.getString("com.start.salary","0");
        int salary = Integer.parseInt(salaryst);
        int limit = salary/4;
        Calendar now = Calendar.getInstance();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("com.start.month",now.get(Calendar.MONTH) + 1);
        editor.commit();
        Log.e("db on create call","called");
        db.execSQL(CREATE_C_TABLE);
        db.execSQL(CREATE_S_TABLE);
        db.execSQL(CREATE_T_TABLE);
        db.execSQL(CREATE_R_TABLE);
        Log.e("db on create call","called");
        ContentValues cv = new ContentValues();
        cv.put(C_CAT, "food and necessities");
        cv.put(C_LIMIT, limit);
        cv.put(C_AMOUNT, 0);
        cv.put(C_DEFAULT, 1);
        ContentValues cp = new ContentValues();
        cp.put(C_CAT, "entertainment");
        cp.put(C_LIMIT,limit);
        cp.put(C_AMOUNT, 0);
        cp.put(C_DEFAULT, 1);
        ContentValues cg = new ContentValues();
        cg.put(C_CAT, "bills");
        cg.put(C_LIMIT, limit);
        cg.put(C_AMOUNT, 0);
        cg.put(C_DEFAULT, 1);
        ContentValues cb = new ContentValues();
        cb.put(C_CAT, "travel expenses");
        cb.put(C_LIMIT, limit);
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

        Map<String,String> shoplist = new HashMap<>();
        shoplist.put("reliance","food and necessities");
        shoplist.put("apollo","bills");
        shoplist.put("irctc","travel expenses");
        shoplist.put("redbus","travel expenses");
        shoplist.put("airtel","bills");
        shoplist.put("jio","bills");
        shoplist.put("atm","food and necessities");
        shoplist.put("inox","entertainment");
        shoplist.put("pvr","entertainment");
        shoplist.put("just bake","foods and necessities");
        String selectQuery = "SELECT  * FROM " + SHOP_TABLE + " WHERE " + S_SHOP + "=" + "'" + shop + "'";
        SQLiteDatabase dbt = this.getReadableDatabase();
        Cursor cursor = dbt.rawQuery(selectQuery, null);
        if(cursor != null && cursor.getCount()!= 0){
            Log.e("getcat returnrd","found");
            cursor.moveToFirst();
            String category = cursor.getString(1);
            cursor.close();
            dbt.close();
            return category;
        }
        else{
            cursor.close();
            dbt.close();
            if(shop.contains("retail") || shop.contains("mart") || shop.contains("foods") || shop.contains("supermart") ){
                return "foods and necessities";
            }
            if(shop.contains("mall") ||shop.contains("cinemas") ||shop.contains("movies") ||shop.contains("restaurant") ||shop.contains("dining")){
                return "entertainment";
            }
            if(shop.contains("travels")||shop.contains("resort") || shop.contains("hotel")  ) {
                return "travel expenses";
            }
            if(shop.contains("premium") ){
                return "bills";
            }
            if(shoplist.containsKey(shop)){
                return shoplist.get(shop);
            }
            return "bills";
        }


    }


    public boolean addtransaction(String amount,String date,String shop){     //String amount,String shop,String date){
        Log.e("getcategory called","found");
        String category = findCategory(shop);
        SQLiteDatabase db = this.getWritableDatabase();
        String updatequery = "UPDATE "+ CAT_TABLE + " SET " + C_AMOUNT + " = "+ C_AMOUNT + "+" +  amount + " WHERE " +  C_CAT + " = " + "'" + category + "'";
        db.execSQL(updatequery);
        ContentValues t = new ContentValues();
        t.put(T_AMOUNT, Integer.parseInt(amount));
        t.put(T_CATEGORY, category);
        t.put(T_DATE, date);
        t.put(T_SHOP, shop);
        db.insert(TRAN_TABLE,null,t);
        Log.e("added trans",date);
        boolean flag = false;
        String checkquery = "SELECT * FROM " + CAT_TABLE + " WHERE " + C_CAT + "='" + category + "'";
        Cursor cursor = db.rawQuery(checkquery,null);
        if(cursor!=null && cursor.getCount()!=0) {
            cursor.moveToFirst();
            if (cursor.getInt(1) < (cursor.getInt(2))) {
                Log.e("limit","exceeeded");
                flag = true;
            }
        }
        cursor.close();
        db.close();
        return flag;




    }

    public List<List<String>> gettransactions(String from_date,String to_date){
        List<List<String>> result = new ArrayList<>();
        String [] frst = from_date.split("/");
        String [] tst = to_date.split("/");
        if(Integer.parseInt(frst[1]) < 10){
            frst[1] = "0" + frst[1];
        }
        if(Integer.parseInt(frst[0]) < 10){
            frst[0] = "0" + frst[0];
        }
        if(Integer.parseInt(tst[1]) < 10){
            tst[1] = "0" + tst[1];
        }
        if(Integer.parseInt(tst[0]) < 10){
            tst[0] = "0" + tst[0];
        }
        String nfrom_date = frst[2] + "/" + frst[1] + "/" + frst[0];
        String nto_date = tst[2] + "/" + tst[1] + "/" + tst[0];
        Log.e("from",nfrom_date);
        Log.e("to",nto_date);
        SQLiteDatabase dbt = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TRAN_TABLE + " WHERE " + T_DATE + ">=" + "'" + nfrom_date + "'" + " AND " + T_DATE + "<=" + "'" + nto_date + "'";
        Cursor cursor = dbt.rawQuery(sql,null);
        List<String> id = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<String> amount = new ArrayList<>();
        List<String> category = new ArrayList<>();
        List<String> shop = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                id.add(cursor.getString(0));
                date.add(cursor.getString(2));
                amount.add(cursor.getString(1));
                category.add(cursor.getString(3));
                shop.add(cursor.getString(4));
            } while (cursor.moveToNext());
            result.add(id);
            result.add(amount);
            result.add(date);
            result.add(category);
            result.add(shop);
        }
        int result1 = cursor.getCount();
        cursor.close();
        dbt.close();

        return result;
    }
    public void initamount(){
        SQLiteDatabase dbt = this.getWritableDatabase();
        String sql = "UPDATE " + CAT_TABLE + " SET " + C_AMOUNT + "=0";
        dbt.execSQL(sql);
        Calendar now = Calendar.getInstance();
        sp = PreferenceManager.getDefaultSharedPreferences(appcontext);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("com.start.month",now.get(Calendar.MONTH) + 1);
        Log.e("initamount","success");
        dbt.close();

    }
    public void addnewcat(String cat,String limit){
        SQLiteDatabase dbt = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(C_CAT, cat);
        cn.put(C_LIMIT, Integer.parseInt(limit));
        cn.put(C_AMOUNT, 0);
        cn.put(C_DEFAULT, 0);
        dbt.insert(CAT_TABLE,null,cn);
        dbt.close();

    }
    public void editcat(String cat,String limit){
        SQLiteDatabase dbt = this.getWritableDatabase();
        String sql = "UPDATE " + CAT_TABLE + " SET " + C_LIMIT + "=" + limit + " WHERE " + C_CAT + "= '" + cat + "'";
        dbt.execSQL(sql);
        dbt.close();
    }
    public void addnewshop(String name,String cat){
        SQLiteDatabase dbt = this.getWritableDatabase();
        ContentValues cn = new ContentValues();
        cn.put(S_SHOP,name);
        cn.put(S_CATEGORY,cat);
        dbt.insert(SHOP_TABLE,null,cn);
        dbt.close();


    }
    public void editshop(String shop,String cat){
        SQLiteDatabase dbt = this.getWritableDatabase();
        String sql = "UPDATE " + SHOP_TABLE + " SET " + S_CATEGORY + "=" + "'" + cat + "'" + " WHERE " + S_SHOP + "= '" + shop + "'";
        dbt.execSQL(sql);
        dbt.close();

    }
    public void deleteshop(String shop){
        SQLiteDatabase dbt = this.getWritableDatabase();
        String sql = "DELETE FROM " + SHOP_TABLE  + " WHERE " + S_SHOP + "= '" + shop + "'";
        dbt.execSQL(sql);
        dbt.close();
    }
    public void edittran(String id,String amt,String ocat,String ncat){
        SQLiteDatabase dbt = this.getWritableDatabase();
        String sql1 = "UPDATE " + TRAN_TABLE + " SET " + T_CATEGORY + "=" + "'" + ncat + "'" + " WHERE " + T_ID + "=" + id;
        String sql2 = "UPDATE " + CAT_TABLE + " SET " + C_AMOUNT + "=" + C_AMOUNT + "+" + amt +  " WHERE " + C_CAT + "=" + "'" + ncat + "'" ;
        String sql3 = "UPDATE " + CAT_TABLE + " SET " + C_AMOUNT + "=" + C_AMOUNT + "-" + amt +  " WHERE " + C_CAT + "=" + "'" + ocat + "'" ;
        dbt.execSQL(sql1);
        dbt.execSQL(sql2);
        dbt.execSQL(sql3);
        dbt.close();


    }
    public void deletetran(String id,String amt,String ocat){
        SQLiteDatabase dbt = this.getWritableDatabase();
        String sql = "DELETE FROM " + TRAN_TABLE  + " WHERE " + T_ID + "=" + id;
        String sql1 = "UPDATE " + CAT_TABLE + " SET " + C_AMOUNT + "=" + C_AMOUNT + "-" + amt +  " WHERE " + C_CAT + "=" + "'" + ocat + "'" ;
        dbt.execSQL(sql1);
        dbt.execSQL(sql);
        dbt.close();
    }



}
