package com.ambinusian.adab.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;

public class ClassScheduleDatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "class_schedule";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TRANSACTION_DATE = "date";
    public static final String COLUMN_CLASS_TYPE = "class_type";
    public static final String COLUMN_CLASS_TOPIC = "class_topic";
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_COURSE_CODE = "course_code";
    public static final String COLUMN_CLASS_CODE = "class_code";
    public static final String COLUMN_CLASS_TIME = "class_time";

    // Database Name
    private static final String DATABASE_NAME = "class_schedule_db";

    public ClassScheduleDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"("
               + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TRANSACTION_DATE + "TEXT,"
                + COLUMN_CLASS_TYPE + "TEXT,"
                + COLUMN_CLASS_TOPIC + "TEXT,"
                + COLUMN_COURSE_NAME + "TEXT,"
                + COLUMN_COURSE_CODE + "TEXT,"
                + COLUMN_CLASS_CODE + "TEXT,"
                + COLUMN_CLASS_TIME + "TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

}
