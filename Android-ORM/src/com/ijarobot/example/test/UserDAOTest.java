package com.ijarobot.example.test;

import com.ijarobot.example.dao.UserDAO;
import com.ijarobot.example.model.User;

import android.test.AndroidTestCase;

/**
 * UserDAOTest
 * @author ijarobot (java2eer@gmail.com)
 *
 */
public class UserDAOTest extends AndroidTestCase {
	
	/**
	 * 测试保存操作
	 */
	public void testSave(){
		UserDAO userDAO = new UserDAO(this.getContext());
		for(int x = 0; x < 20; x++){
			User user = new User();
			user.setUserID(3243343 + x);
			user.setUserName("ijarobot-" + x);
		    user.setSignature("二流程序猿");
		    user.setUserDesc("二流程序猿,需要努力~~~~~~~~");    
		    //TODO  more setXxx
		    userDAO.insert(user);
		}
	}

}
