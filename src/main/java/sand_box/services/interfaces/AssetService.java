package sand_box.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import sand_box.tables.Asset;
import sand_box.tables.images.MainPicture;
import sand_box.tables.images.Picture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Zinoviy on 8/24/16.
 */
public interface AssetService {
    Asset findById(Long id);
    List<Asset> findByTitle(String title);
    List<Asset> findByTag(String tag);
    List<Asset> getAssets();
    void updateAsset(Asset asset);
    void deleteAsset(long id);
    long addAsset(Asset asset);
    Set<String> getTags();
    void addMainPicture(long asset_id, MultipartFile photo) throws IOException;
    byte[] getMainPicture(long size, long id) throws IOException;
}
