package sand_box.services.interfaces;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sand_box.tables.Asset;
import sand_box.tables.Author;
import sand_box.tables.images.Photo;
import sand_box.tables.images.Picture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by Zinoviy on 8/24/16.
 */
public interface AuthorService {
    Author getAuthor(long id);

    Author getAuthor(String name);

    List<Author> getAuthors();

    List<Asset> getAuthorAssetes(long id);

    void addAuthor(Author author);

    @Transactional
    void addAuthor(Author author, long[] assets_id);

    void deleteAuthor(long id);

    void updateAuthor(Author author);

    void addPhoto(long author_id, MultipartFile photo) throws IOException;

    byte[] getPhoto(long size, long author_id) throws IOException;

}
