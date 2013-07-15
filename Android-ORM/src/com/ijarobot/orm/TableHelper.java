package com.ijarobot.orm;

import java.lang.reflect.Field;
import java.sql.Blob;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ijarobot.orm.annotation.Column;
import com.ijarobot.orm.annotation.Id;
import com.ijarobot.orm.annotation.Table;

/**
 * @description 根据Class数组,据自动创建,删除表
 * @author ijarobot (java2eer@gmail.com)
 * @date 2012-8-6 
 * @version V1.0 
 */
public class TableHelper {
	
	public static <T> void createTablesByClasses(SQLiteDatabase db,
			Class<?>[] clazzs) {
		for (Class<?> clazz : clazzs)
			createTable(db, clazz);
	}

	public static <T> void dropTablesByClasses(SQLiteDatabase db,
			Class<?>[] clazzs) {
		for (Class<?> clazz : clazzs)
			dropTable(db, clazz);
	}

	public static <T> void createTable(SQLiteDatabase db, Class<T> clazz) {
		String tableName = "";
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			tableName = table.name();
			Log.d("TableHelper","tableName:" + tableName);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(tableName).append(" (");

		Field[] fields = clazz.getDeclaredFields();
		Log.d("TableHelper",fields.length + "");
		for (Field field : fields) {
			Log.d("TableHelper",field.getName());

			if (!field.isAnnotationPresent(Column.class)) {
				continue;
			}
			Column column = (Column) field.getAnnotation(Column.class);

			String columnType = "";
			if (column.type().equals(""))
				columnType = getColumnType(field.getType());
			else {
				columnType = column.type();
			}

			sb.append(column.name() + " " + columnType);

			if ((!column.type().equals("")) && (column.lenght() != 0)) {
				sb.append("(" + column.lenght() + ")");
			}

			if (((field.isAnnotationPresent(Id.class)) && (field.getType() == Integer.TYPE))
					|| (field.getType() == Integer.class))
//				sb.append(" primary key autoincrement");id不需要自动增长，使用服务端返回的id
				sb.append(" primary key");
			else if (field.isAnnotationPresent(Id.class)) {
				sb.append(" primary key");
			}

			sb.append(", ");
		}

		sb.delete(sb.length() - 2, sb.length() - 1);
		sb.append(")");

		String sql = sb.toString();

		Log.d("TableHelper","sql:" + sql);

		db.execSQL(sql);
	}

	public static <T> void dropTable(SQLiteDatabase db, Class<T> clazz) {
		String tableName = "";
		if (clazz.isAnnotationPresent(Table.class)) {
			Table table = (Table) clazz.getAnnotation(Table.class);
			tableName = table.name();
			Log.d("TableHelper","tableName:" + tableName);
		}
		String sql = "DROP TABLE IF EXISTS " + tableName;
		db.execSQL(sql);
	}

	private static String getColumnType(Class<?> fieldType) {
		if (String.class == fieldType) {
			return "TEXT";
		}
		if ((Integer.TYPE == fieldType) || (Integer.class == fieldType)) {
			return "INTEGER";
		}
		if ((Long.TYPE == fieldType) || (Long.class == fieldType)) {
			return "BIGINT";
		}
		if ((Float.TYPE == fieldType) || (Float.class == fieldType)) {
			return "FLOAT";
		}
		if ((Short.TYPE == fieldType) || (Short.class == fieldType)) {
			return "INT";
		}
		if ((Double.TYPE == fieldType) || (Double.class == fieldType)) {
			return "DOUBLE";
		}
		if((Boolean.TYPE == fieldType) || (Boolean.class == fieldType)){
			return "VARCHAR(5)";
		}
		
		if((Enum.class == fieldType)){
			return "VARCHAR(10)";
		}
		if ((Blob.class == fieldType)) {
			return "BLOB";
		}

		return "TEXT";
	}
}