package pl.jaceklewinski.FunniestVideos.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jaceklewinski.FunniestVideos.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET user.password =?1 WHERE user.username = ?2", nativeQuery = true)
    int update(String password, String username);
}
