package sand_box.tables;

import sand_box.tables.images.Photo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zinoviy on 8/24/16.
 */
@Entity
@Table(name="Authores")
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;
    private String phone;
    private String email;
    private String website;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Asset> assets;

    public Author(){

    }

    public Author(String name, String phone, String email, String website, List<Asset> assets) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.assets = assets;
    }

    public Author(String name, String phone, String email, String website) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.assets = new ArrayList<>();
    }

    public Author(String name, Photo photo, String phone, String email, String website, List<Asset> assets) {
        this.name = name;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.assets = assets;
    }

    public void addAsset(List<Asset> assets) {
        for(Asset asset : assets) {
            this.assets.add(asset);
            asset.setAuthor(this);
        }
        System.out.println("");
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

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", assets=" + assets +
                '}';
    }
}
