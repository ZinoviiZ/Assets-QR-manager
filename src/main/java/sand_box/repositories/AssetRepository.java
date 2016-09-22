package sand_box.repositories;

import sand_box.tables.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Created by Zinoviy on 8/24/16.
 */
public interface AssetRepository extends JpaRepository<Asset,Long>{
    @Query("SELECT a FROM Asset a WHERE a.id = :id")
    Asset findAssetById(@Param("id") long id);

    @Query("SELECT a FROM Asset a WHERE a.title = :title")
    List<Asset> findAssetByTitle(@Param("title") String title);

    @Query("SELECT a FROM Asset a WHERE :tag MEMBER OF a.tags")
    List<Asset> findAssetsByTag(@Param("tag") String tag);

}
