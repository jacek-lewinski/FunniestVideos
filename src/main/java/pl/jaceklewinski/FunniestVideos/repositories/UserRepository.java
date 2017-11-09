package pl.jaceklewinski.FunniestVideos.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jaceklewinski.FunniestVideos.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Query(value = "UPDATE user SET password =?1 WHERE username = ?2")
    int update(String password, String username);
}
