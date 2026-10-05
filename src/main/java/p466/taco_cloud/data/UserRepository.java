package p466.taco_cloud.data;

import org.springframework.data.repository.CrudRepository;
import p466.taco_cloud.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}