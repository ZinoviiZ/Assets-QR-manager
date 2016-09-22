package sand_box.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sand_box.tables.images.MainPicture;

/**
 * Created by Zinoviy on 9/13/16.
 */
public interface MainPictureRepository extends JpaRepository<MainPicture, Long> {
}
