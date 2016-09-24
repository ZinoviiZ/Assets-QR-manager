package sand_box.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sand_box.tables.images.Picture;

/**
 * Created by Zinoviy on 8/26/16.
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query ("SELECT p FROM Picture p WHERE p.name = :name ")
    Picture getPictureForName(@Param("name") String name);
}
