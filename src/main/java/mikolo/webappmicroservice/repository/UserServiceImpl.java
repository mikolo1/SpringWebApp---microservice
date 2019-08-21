package mikolo.webappmicroservice.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mikolo.webappmicroservice.model.Role;
import mikolo.webappmicroservice.model.User;

@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private JpaContext jpaContext;
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Override
	public void saveUser(User user) {
		user.setActive(1);
		Role role = roleRepository.findByRole("ROLE_USER");
//		user.setRoles(new HashSet<>(Arrays.asList(new Role(2, "ROLE_USER"))));
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		userRepository.save(user);
	}

	@Override
	public void insertInBatch(List<User> userList) {
		EntityManager em = jpaContext.getEntityManagerByManagedType(User.class);
		Role role = roleRepository.findByRole("ROLE_USER");
		userList.forEach(user -> {
			user.setRoles(new HashSet<>(Arrays.asList(role)));
			em.merge(user);
			if (userList.indexOf(user) % 50 == 0 && userList.indexOf(user) != 00) {
				em.flush();
				em.clear();
			}
		});
	}

}
