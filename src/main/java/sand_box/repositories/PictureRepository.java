package sand_box.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sand_box.tables.images.Picture;

/**
 * Created by Zinoviy on 8/26/16.
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
