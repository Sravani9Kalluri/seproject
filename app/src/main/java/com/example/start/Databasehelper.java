/*package com.example.start;
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

    //SQLiteDatabase db = this.getWritableDatabase();
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
            + T_AMOUNT + "INTEGER,"
            + T_DATE + " TEXT," + T_CATEGORY + " TEXT,"
            + T_SHOP + " TEXT," + " TEXT );";

    String CREATE_R_TABLE = "CREATE TABLE " + REGEX_TABLE + "("
            + R_REGEX + " INTEGER PRIMARY KEY, "
            + R_CATEGORY + " TEXT NOT NULL );";

    String CREATE_C_TABLE ="CREATE TABLE " + CAT_TABLE   + "("

            + C_CAT + " TEXT, "+ C_LIMIT + " INTEGER, " + C_AMOUNT + "INTEGER" +
            C_DEFAULT + "INTEGER );";

    String CREATE_S_TABLE ="CREATE TABLE" + SHOP_TABLE + "(" + S_SHOP + "TEXT" + S_CATEGORY + "TEXT );";




}*/
