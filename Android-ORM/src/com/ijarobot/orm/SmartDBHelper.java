package com.ijarobot.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @description 建库\建表\表结构更新相关操作
 * @author ijarobot (java2eer@gmail.com)
 * @date 2012-8-6 
 * @version V1.0 
 */
public class SmartDBHelper extends SQLiteOpenHelper
{
  private Class<?>[] modelClasses;

  public SmartDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, Class<?>[] modelClasses){
    super(context, databaseName, factory, databaseVersion);

    this.modelClasses = modelClasses;
  }

  public void onCreate(SQLiteDatabase db){
	  
    TableHelper.createTablesByClasses(db, this.modelClasses);
    
  }

  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    TableHelper.dropTablesByClasses(db, this.modelClasses);
//	  db.execSQL("alter table xxx_xxx add groupID INTEGER, groupType TEXT, groupName TEXT;");
    onCreate(db);
  }
}