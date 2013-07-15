package com.ijarobot.orm;

import java.lang.reflect.Field;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ijarobot.orm.annotation.Column;
import com.ijarobot.orm.annotation.Id;
import com.ijarobot.orm.annotation.Table;

/**
 * @description DB操作基类
 * @param <T>
 * @author ijarobot (java2eer@gmail.com)
 * @date 2012-8-6 
 * @version V1.0 
 */
public class BaseDAO<T> {
	public SQLiteOpenHelper dbHelper;
	protected String tableName;
	private String idColumn;
	private Class<T> clazz;

	public BaseDAO(SQLiteOpenHelper dbHelper) {
		this.dbHelper = dbHelper;

		this.clazz = ((Class) ((java.lang.reflect.ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
		Log.d("BaseDAO","clazz:" + this.clazz);

		if (this.clazz.isAnnotationPresent(Table.class)) {
			Table table = this.clazz.getAnnotation(Table.class);
			this.tableName = table.name();
			Log.d("BaseDAO","tableName:" + this.tableName);
		}

		Field[] fields = this.clazz.getDeclaredFields();
		for (Field field : fields)
			if (field.isAnnotationPresent(Id.class)) {
				Log.d("BaseDAO","### get idColumn");
				Column column = field.getAnnotation(Column.class);
				this.idColumn = column.name();
				break;
			}
	}

	/**
	 * 得到DAO当前表的名字  --add by 仇加林
	 * @return
	 */
	public String getTableName() {
		return tableName;
	}
	
	public T get(int id) {
		return get(Integer.toString(id));
	}
	
	public T get(String id) {
		Log.d("BaseDAO","get by " + this.idColumn);

		String selection = this.idColumn + " = ?";

		String[] selectionArgs = {id};
		Log.d("BaseDAO","id:" + id);
		Log.d("BaseDAO","where:" + selection);

		List<T> list = find(null, selection, selectionArgs, null, null, null, null);
		if ((list != null) && (list.size() > 0)) {
			return list.get(0);
		}
		return null;
	}

	public List<T> rawQuery(String sql, String[] selectionArgs) {
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = this.dbHelper.getReadableDatabase();
			cursor = db.rawQuery(sql, selectionArgs);

			getListFromCursor(list, cursor);
		} catch (Exception e) {
			Log.d("BaseDAO","rawQuery from DB Exception.");
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return list;
	}

	public List<T> find() {
		return find(null, null, null, null, null, null, null);
	}

	public List<T> find(String[] columns, String selection,
			String[] selectionArgs, String groupBy, String having,
			String orderBy, String limit) {
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = this.dbHelper.getReadableDatabase();
//			long starttime = System.currentTimeMillis();
			cursor = db.query(this.tableName, columns, selection,selectionArgs, groupBy, having, orderBy, limit);
//			long  endtime =  System.currentTimeMillis();
			getListFromCursor(list, cursor);
		} catch (Exception e) {
			Log.d("BaseDAO","find from DB Exception");
			e.printStackTrace();
		} finally {
			try {
				if (cursor != null) {
					cursor.close();
				}
				if (db != null) {
					db.close();
				}
			} catch (RuntimeException e2) {
				//偶尔在多线程场景下会出关闭数据库的RuntimeException
			}
		}

		return list;
	}

	private void getListFromCursor(List<T> list, Cursor cursor)throws IllegalAccessException, InstantiationException{
		
    while (cursor.moveToNext()) {
      Object entity = this.clazz.newInstance();

      for (Field field : this.clazz.getDeclaredFields())
      {
        Column column = null;
        if (field.isAnnotationPresent(Column.class)) {
          column = field.getAnnotation(Column.class);

          field.setAccessible(true);
          Class<?> fieldType = field.getType();

          if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
            int fieldValue = cursor.getInt(cursor.getColumnIndex(column.name()));
            field.set(entity, Integer.valueOf(fieldValue));
            
          }else if (String.class == fieldType) {
            String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
            field.set(entity, fieldValue);
            
          }
          else if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
            long fieldValue = cursor.getLong(cursor.getColumnIndex(column.name()));
            field.set(entity, Long.valueOf(fieldValue));
            
          }
          else if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
            float fieldValue = cursor.getFloat(cursor.getColumnIndex(column.name()));
            field.set(entity, Float.valueOf(fieldValue));
            
          }
          else if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
            short fieldValue = cursor.getShort(cursor.getColumnIndex(column.name()));
            field.set(entity, Short.valueOf(fieldValue));
            
          }
          else if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
            double fieldValue = cursor.getDouble(cursor.getColumnIndex(column.name()));
            field.set(entity, Double.valueOf(fieldValue));
            
          }
          else if ((Boolean.TYPE == fieldType) || (Boolean.class == fieldType)) {
        	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
              field.set(entity, Boolean.valueOf(fieldValue));
              
          }
          
//          else if (MsgType.class == fieldType) {// add for event
//        	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
//              field.set(entity, MsgType.valueOf(fieldValue));
//              
//          }
//          else if (MsgState.class == fieldType) {// add for event
//        	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
//              field.set(entity, MsgState.valueOf(fieldValue));
//          }
//          else if (EventState.class == fieldType) {// add for event
//        	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
//              field.set(entity, EventState.valueOf(fieldValue));
//          }
//          
//          else if (SendRecv.class == fieldType) {// add for event
//        	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
//              field.set(entity, SendRecv.valueOf(fieldValue));
//          }
//          
//          else if (CityType.class == fieldType) {// add for event
//        	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
//              field.set(entity, CityType.valueOf(fieldValue));
//          }
//         
//        else if (BoardType.class == fieldType) {// add for BBS
//    	  String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
//          field.set(entity, BoardType.valueOf(fieldValue));
//        }
          
          
          else if (Blob.class == fieldType) {
            byte[] fieldValue = cursor.getBlob(cursor.getColumnIndex(column.name()));
            field.set(entity, fieldValue);
            
          }
          else if (Character.TYPE == fieldType) {
            String fieldValue = cursor.getString(cursor.getColumnIndex(column.name()));
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
              field.set(entity, Character.valueOf(fieldValue.charAt(0)));
            }
          }
        }
      }
      Log.d("BaseDAO","----------------------");

      list.add((T) entity);
    }
  }

	public long insert(T entity) {
		Log.d("BaseDAO","inset ----------------------");
		SQLiteDatabase db = null;
		try {
			db = this.dbHelper.getWritableDatabase();
			ContentValues cv = new ContentValues();

			setContentValues(entity, cv, "create");

			long row = db.insert(this.tableName, null, cv);
			long l1 = row;
			return l1;
		} catch (Exception e) {
			Log.d("BaseDAO","insert into DB Exception.");
		} finally {
			if (db != null) {
				db.close();
			}
		}

		return 0L;
	}

	public void delete(int id) {
		delete(String.valueOf(id));
	}
	
	public void delete(String id) {
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		String where = this.idColumn + " = ?";
		String[] whereValue = { id };
		Log.d("BaseDAO","delelte where " + where + " " + whereValue);
		db.delete(this.tableName, where, whereValue);

		db.close();
	}	
	
	
	public void deleteAll() {
		SQLiteDatabase db = this.dbHelper.getWritableDatabase();
		db.delete(this.tableName, null, null);
//		db.execSQL("delete from " + this.tableName);
		db.close();
		Log.d("BaseDAO","delelte all OK");
	}
	
	

	public void update(T entity) {
		Log.d("BaseDAO","update by " + this.idColumn);
		SQLiteDatabase db = null;
		try {
			db = this.dbHelper.getWritableDatabase();
			ContentValues cv = new ContentValues();

			setContentValues(entity, cv, "update");

			String where = this.idColumn + " = ?";
			int id = Integer.parseInt(cv.get(this.idColumn).toString());
			cv.remove(this.idColumn);

			Log.d("BaseDAO","id:" + id);
			Log.d("BaseDAO","where:" + where);

			String[] whereValue = { Integer.toString(id) };
			db.update(this.tableName, cv, where, whereValue);
		} catch (Exception e) {
			Log.d("BaseDAO","update DB Exception.");
		} finally {
			if (db != null)
				db.close();
		}
	}

	private void setContentValues(T entity, ContentValues cv, String type)
			throws IllegalAccessException {
		Field[] fields = this.clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			Column column = field.getAnnotation(Column.class);
//			Log.d("BaseDAO",column.name()+"  : "+column.type());
			field.setAccessible(true);
			Object fieldValue = field.get(entity);
			if (fieldValue == null)
				continue;
			if (("create".equals(type))
					&& (field.isAnnotationPresent(Id.class))
					&& fieldValue.toString().equals("0")) {
				continue;
			}
			cv.put(column.name(), String.valueOf(fieldValue));
		}
	}
}