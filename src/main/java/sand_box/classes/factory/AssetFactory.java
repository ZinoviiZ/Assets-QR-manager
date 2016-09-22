package sand_box.classes.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zinoviy on 9/20/16.
 */
public class AssetFactory {
    static Asset createEntity(sand_box.tables.Asset asset) {
        Photo main = new Photo(asset.getMainPicture().getSurl(),asset.getMainPicture().getMurl(),asset.getMainPicture().getBurl());
        List<Photo> photos = new ArrayList<>();
        for(sand_box.tables.images.Picture picture : asset.getPictures()) {
            photos.add(new Photo(picture.getSurl(),picture.getMurl(),picture.getBurl()));
        }
        return new AssetImpl(asset.getId(),asset.getTitle(),asset.getDescription(),asset.getPrice(),main,photos);
    }
}

class AssetImpl implements Asset {
    private long id;
    private String title ;
    private String description ;
    private long price ;
    private Photo main;
    private List<Photo> photos;

    public AssetImpl(long id, String title, String description, long price, Photo main, List<Photo> photos) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.main = main;
        this.photos = photos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Photo getMain() {
        return main;
    }

    public void setMain(Photo main) {
        this.main = main;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}


