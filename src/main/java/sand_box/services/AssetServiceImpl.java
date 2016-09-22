package sand_box.services;

import org.springframework.web.multipart.MultipartFile;
import sand_box.repositories.MainPictureRepository;
import sand_box.repositories.PictureRepository;
import sand_box.services.interfaces.AssetService;
import sand_box.services.pictures.ImageConverter;
import sand_box.services.pictures.QrCode;
import sand_box.tables.Asset;
import sand_box.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sand_box.tables.Author;
import sand_box.tables.images.MainPicture;
import sand_box.tables.images.Photo;
import sand_box.tables.images.Picture;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Zinoviy on 8/24/16.
 */
@Service
public class AssetServiceImpl implements AssetService {
    private static final String PATHmain = "/Users/Zinoviy/Desktop/Project_QR/pictures/asset/main";
    private static final String URLmain    = "/asset/photo/main";
    private static final String PATH = "/Users/Zinoviy/Desktop/Project_QR/pictures/asset";
    private static final String PATHsecondary = "/Users/Zinoviy/Desktop/Project_QR/pictures/asset/secondary";
    private static final String URLsecondary    = "/asset/photo/secondary";

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private MainPictureRepository mainPictureRepository;

    @Override
    @Transactional
    public Asset findById(Long id) {
        return assetRepository.findAssetById(id);
    }

    @Override
    @Transactional
    public List<Asset> findByTitle(String title) {
        return assetRepository.findAssetByTitle(title);
    }

    //IMPROVE!!!
    @Override
    @Transactional
    public List<Asset> findByTag(String tag) {
        List<Asset> result = assetRepository.findAssetsByTag(tag);
        return result;
    }

    @Override
    @Transactional
    public List<Asset> getAssets() {
        return assetRepository.findAll();
    }

    @Override
    @Transactional
    public void updateAsset(Asset asset) {
        assetRepository.save(asset);
    }

    @Override
    @Transactional
    public long addAsset(Asset asset) {
        assetRepository.save(asset);
        QrCode.convertAuthor(PATH+"/qr",asset.getId(), asset.getId());
        return asset.getId();
    }

    @Override
    @Transactional
    public void deleteAsset(long id) {
        assetRepository.delete(id);
    }

    @Override
    @Transactional
    public Set<String> getTags() {
        Set<String> tags = new HashSet<>();
        List<Asset> assets = assetRepository.findAll();
        for(Asset next : assets)
            for(String t : next.getTags())
            tags.add(t);
        return tags;
    }

    @Override
    @Transactional
    public void addMainPicture(long asset_id, MultipartFile file) throws IOException {
        String photo_name = String.valueOf(asset_id);
        ImageConverter converter = new ImageConverter(PATHmain,file.getInputStream(), photo_name);
        converter.converting();

        Asset asset = assetRepository.findOne(asset_id);
        MainPicture mainPicture = new MainPicture(photo_name+".png",PATHmain, URLmain+"/"+300+"/"+photo_name,
                URLmain+"/"+900+"/"+photo_name, URLmain+"/"+2000+"/"+photo_name);
        asset.setMainPicture(mainPicture);
        mainPictureRepository.save(mainPicture);
    }


    @Override
    @Transactional
    public byte[] getMainPicture(long size, long id) throws IOException {
        Asset asset = assetRepository.getOne(id);
        MainPicture picture = asset.getMainPicture();
        File file = new File(picture.getPath() + "/" +size+"/"+picture.getName());
        FileInputStream stream = new FileInputStream(file);
        byte[] result = new byte[stream.available()];
        while(stream.available()>0) {
            stream.read(result);
        }
        stream.close();
        return result;
    }

}
