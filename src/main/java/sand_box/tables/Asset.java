package sand_box.tables;

import com.fasterxml.jackson.annotation.JsonFormat;
import sand_box.tables.images.MainPicture;
import sand_box.tables.images.Picture;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Zinoviy on 8/24/16.
 */
@Entity
@Table(name = "Assets")
public class Asset {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    @Column(columnDefinition="TEXT")
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private Author author;
    private int price;

    @Column(name = "creation_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd.mm.yyyy")
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_picture_id")
    private MainPicture mainPicture;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)//
    private List<Picture> pictures = new ArrayList<>();

    @ElementCollection
    private List<String> tags;

    public Asset() {
    }

    public Asset(String title, String description, int price, Author author) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.author = author;
    }

    public Asset(String title, String description, Author author, int price, Date date, MainPicture mainPicture, List<String> tags) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.price = price;
        this.date = date;
        this.mainPicture = mainPicture;
        this.tags = tags;
    }

    public void addPicture(Picture picture) {
        pictures.add(picture);
        picture.setAsset(this);
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MainPicture getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(MainPicture mainPicture) {
        this.mainPicture = mainPicture;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", price=" + price +
                ", date=" + date +
                ", mainPicture=" + mainPicture +
                ", pictures=" + pictures +
                ", tags=" + tags +
                '}';
    }
}
