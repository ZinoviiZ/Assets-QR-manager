package sand_box.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sand_box.tables.login.User;

/**
 * Created by Zinoviy on 9/5/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login = :login")
    User getUsetByLogin(@Param("login") String login);


}
