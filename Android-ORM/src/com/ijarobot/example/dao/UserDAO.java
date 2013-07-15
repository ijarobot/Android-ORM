package com.ijarobot.example.dao;

import java.util.List;

import android.content.Context;

import com.ijarobot.example.model.User;
import com.ijarobot.orm.BaseDAO;

/**
 * 此类需要继承BaseDAO，在构造方法里面给父类的属性dbHelper赋值，即可实现CRUD操作.<br>
 * 如果有复杂的操作，可以自定义方法来实现
 * @author ijarobot (java2eer@gmail.com)
 */
public class UserDAO extends BaseDAO<User> {

	public UserDAO(Context context) {
		super(new ExampleDBHelper(context));
	}

	public User getByUserName(String userName){
		User user = null;
		List<User> userList = find(null, "userName = ?", new String[]{userName}, null, null, null, null);
		if(userList != null && userList.size() > 0){
			user = userList.get(0);
		}
		return user;
	}
	
	
	
	public User getCurrUser(){
		User user = null;
		List<User> userList = find(null, "currUser = ?", new String[]{"true"}, null, null, null, null);
		if(userList != null && userList.size() > 0){
			user = userList.get(0);
		}
		return user;
	}
	/**
	 * 设置某用户为当前用户
	 * @param currId 当前用户id
	 */
    public void updateOtherCurrUser(int currId){
    	List<User> allUser=this.find();
    	for(User user:allUser){
    		if(user.getUserID() != currId){
    			user.setCurrUser(false);
    			update(user);
    		}

    	}
    	
    }
	
}
