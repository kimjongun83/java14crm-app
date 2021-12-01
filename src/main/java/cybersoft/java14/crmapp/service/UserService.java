package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java12.crmapp.dao.UserDao;
import cybersoft.java12.crmapp.dto.UserCreateDto;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.model.User;

public class UserService {
	private UserDao dao;


	public UserService() {
		dao = new UserDao();
	}

	public List<User> findAll() {
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User findUserById(int id) {
		User user = null;
		try {
			user = dao.findUserById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void deleteById(int id) {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void add(UserCreateDto dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);

		try {
			dao.add(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(UserCreateDto dto, int idToUpdate) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);

		try {
			dao.update(dto, idToUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> sortUserById(int id) {
		List<User>users=new LinkedList<User>();
		users=findAll();
		for(int i=1;i<users.size();i++) {
			if(users.get(i).getId()==id) {
				User user=new User();
				user=users.get(i);
				users.set(i, users.get(0));
				users.set(0, user);
			}
		}
		return users;
	}

	public List<User> findStaffNotJoinProjectStaff(int idToUpdate) {
		List<User> users = null;
		try {
			users = dao.findStaffNotJoinProjectStaff(idToUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	

}
