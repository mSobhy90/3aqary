package aaqary.MobileApp.MainPackage;

import java.util.ArrayList;

public class UsersDataBase {
	ArrayList<User> users = new ArrayList<User>();

	public User getUser(String userName) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getUserName().equals(userName))
				return users.get(i);
		return null;
	}

	public User getUser(int index) {
		return users.get(index%users.size());
	}
	
	public boolean addUser(User newUser) {
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getUserName().equals(newUser.getUserName()))
				return false;
		users.add(newUser);
		return true;
	}
	
	public boolean replaceUser(User newUser){
		for (int i = 0; i < users.size(); i++)
			if (users.get(i).getUserName().equals(newUser.getUserName())){
				users.set(i, newUser);
				return true;
			}
		return false;
	}
}
