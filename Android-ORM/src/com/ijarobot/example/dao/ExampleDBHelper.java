package com.ijarobot.example.dao;

import android.content.Context;

import com.ijarobot.example.model.User;
import com.ijarobot.orm.SmartDBHelper;

/**
 * 继承了SmartDBHelper，不需要重写SQLiteOpenHelper的那两个方法 
 * 父类构造方法参数modelClasses是实体类的数组，也就是需要生产表的类的Class数组
 * @author ijarobot (java2eer@gmail.com) 
 */
public class ExampleDBHelper extends SmartDBHelper {

	//数据库名称
    private final static String DATABASE_NAME = "Example_App.db";
    //数据库版本
    private final static int DATABASE_VERSION1 = 1;
    private final static int DATABASE_VERSION2 = 2;
    private final static int DATABASE_VERSION3 = 3;
    
    //需要生成数据库表的类的数组
    private final static Class<?>[] modelClasses = {User.class};//TODO 添加其他model到此处
    
	public ExampleDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION1, modelClasses);
	}
	


}