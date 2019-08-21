package mikolo.webappmicroservice.repository;

import java.util.List;

import mikolo.webappmicroservice.model.User;

public interface UserService {
//	public User findByEmail(String email);
//	public List<User> findAll();
//	public User findById(long id);
	public void saveUser(User user);
	public void insertInBatch(List<User> userList);
	
}
