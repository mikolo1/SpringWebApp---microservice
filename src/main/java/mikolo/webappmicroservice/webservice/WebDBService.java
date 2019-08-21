package mikolo.webappmicroservice.webservice;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.AllArgsConstructor;
import mikolo.webappmicroservice.model.User;
import mikolo.webappmicroservice.repository.UserRepository;
import mikolo.webappmicroservice.repository.UserService;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/service/users")
public class WebDBService {

	private UserRepository userRepository;
	private UserService userService;

	@GetMapping(value = "/id={id}")
	private User getUserById(@PathVariable("id") long id) {
		User user = userRepository.findById(id);
		getAndSetNrRoli(user);
		return user;
	}

	@GetMapping(value = "/email={email}")
	private User getUserByEmail(@PathVariable("email") String email) {
		User user = userRepository.findByEmail(email);
		getAndSetNrRoli(user);
		return user;
	}

	@GetMapping(value = "/all")
	private List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();
		userList.forEach(u -> getAndSetNrRoli(u));
		return userList;
	}

	@PostMapping(value = "/adduserslist", consumes = "application/json")
	private void addUserList(@RequestBody final String listAsjsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type type = new TypeToken<List<User>>(){}.getType();
		List<User> userList = gson.fromJson(listAsjsonString, type);
		userService.insertInBatch(userList);

	}

	@PostMapping(value = "/adduserslist1", consumes = "application/json")
	private void addUserList1(@RequestBody final List<User> userList) {
		userService.insertInBatch(userList);

	}

	@PostMapping(value = "/adduserslist2", consumes = "application/json")
	private void addUserList2(@RequestBody final String userList) {
		List<User> listOfUsers = new ArrayList<>();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(userList);
		JsonArray jArray = element.getAsJsonArray();
		Gson gson = new Gson();
		jArray.forEach(u -> {	
			User user = gson.fromJson(u, User.class);
			listOfUsers.add(user);
		});
		userService.insertInBatch(listOfUsers);

	}
	
	@PostMapping(value = "/adduser")
	private User addUser(@RequestBody final User user) {
		userService.saveUser(user);
		getAndSetNrRoli(user);
		return user;
	}

	private void getAndSetNrRoli(User u) {
		int nrRoli = u.getRoles().iterator().next().getId();
		u.setNrRoli(nrRoli);
	}

}
