package mikolo.webappmicroservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//alter table users modify column user_id int NOT NULL AUTO_INCREMENT;
	@Column(name = "user_id")
	private long id; 
	
	@NotNull
	private String email;

	@NotNull
	private String password;

	@NotNull
	private String name;

	@NotNull
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role>roles;
	
	@Transient
	private int nrRoli;
	
	
	@Column(name = "email_activation_code")
	private String emailActivationCode;

	public User(@NotNull String email, @NotNull String password, @NotNull String name, @NotNull String lastName) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
	}

}
