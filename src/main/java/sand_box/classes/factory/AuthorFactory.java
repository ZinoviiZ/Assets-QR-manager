package sand_box.classes.factory;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zinoviy on 9/20/16.
 */
public class AuthorFactory {
    static Author createEntity(sand_box.tables.Author author) {
        List<Long> asset_ids = new ArrayList<>();
        for(sand_box.tables.Asset asset : author.getAssets()) {
            asset_ids.add(asset.getId());
        }
        Photo photo = new Photo(author.getPhoto().getSurl(),author.getPhoto().getMurl(),author.getPhoto().getBurl());
        return new AuthorImpl(author.getId(), author.getName(),photo,author.getPhone(),author.getEmail(),
                author.getWebsite(), asset_ids);
    }
}



class AuthorImpl implements Author {
    private long id;
    private String name;
    private Photo photo;
    private String phone;
    private String email;
    private String website;
    private List<Long> assetsid;

    public AuthorImpl(long id, String name, Photo photo, String phone, String email, String website, List<Long> assetsid) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.assetsid = assetsid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Long> getAssetsid() {
        return assetsid;
    }

    public void setAssetsid(List<Long> assetsid) {
        this.assetsid = assetsid;
    }
}


