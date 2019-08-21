package mikolo.webappmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mikolo.webappmicroservice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	public Role findByRole(String role);
}
