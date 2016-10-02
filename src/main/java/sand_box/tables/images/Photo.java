package sand_box.tables.images;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Zinoviy on 9/9/16.
 */
@Entity
@Table(name = "Photos")
public class Photo {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String path;
    private String surl;
    private String murl;
    private String burl;
    public Photo() {

    }

    public Photo(String name, String path, String surl, String murl, String burl) {
        this.name = name;
        this.path = path;
        this.surl = surl;
        this.murl = murl;
        this.burl = burl;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }

    public String getBurl() {
        return burl;
    }

    public void setBurl(String burl) {
        this.burl = burl;
    }
}
