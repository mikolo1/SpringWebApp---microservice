package mikolo.webappmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mikolo.webappmicroservice.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	public List<User> findAll();
	public User findById(long id);
}
