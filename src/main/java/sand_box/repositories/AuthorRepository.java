package sand_box.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sand_box.tables.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zinoviy on 8/24/16.
 */
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Author findAuthorByName(@Param("name") String name);
}
