package sand_box.classes.factory;


/**
 * Created by Zinoviy on 9/19/16.
 */

// Response classes
public class Factory {
    public static Asset getAsset(sand_box.tables.Asset asset) {
        return AssetFactory.createEntity(asset);
    }

    public static Author getAuthor(sand_box.tables.Author author) {
        return AuthorFactory.createEntity(author);
    }
}



interface Author {

}

interface Asset {

}

class Photo {
    private String sphoto;
    private String mphoto;
    private String bphoto;

    public Photo(String sphoto, String mphoto, String bphoto) {
        this.sphoto = sphoto;
        this.mphoto = mphoto;
        this.bphoto = bphoto;
    }

    public String getSphoto() {
        return sphoto;
    }

    public void setSphoto(String sphoto) {
        this.sphoto = sphoto;
    }

    public String getMphoto() {
        return mphoto;
    }

    public void setMphoto(String mphoto) {
        this.mphoto = mphoto;
    }

    public String getBphoto() {
        return bphoto;
    }

    public void setBphoto(String bphoto) {
        this.bphoto = bphoto;
    }
}
