package sand_box.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sand_box.tables.images.Photo;

/**
 * Created by Zinoviy on 9/9/16.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
