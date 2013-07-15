Android-ORM
===========

Android-ORM Framework

写sql你很烦？
但缓存、本地收藏、本地数据…这些功能都需要使用sqlite需要建库、建表、写sql.

Android-ORM,Android上的对象关系映射。让你不需要关心创建db、创建table、sql，你只需要关心model即可

Android-ORM,封装了普通的增、删、改、查功能。

你不需要写一行sql，就能完成CRUD

Example:

1.Code

>
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
    
2.Run

![testrun icon](https://raw.github.com/ijarobot/Android-ORM/master/Screenshot/0130715150338.jpg)



3.就能完成…如下图

![dbresult icon](https://raw.github.com/ijarobot/Android-ORM/master/Screenshot/0130715150016.jpg)
