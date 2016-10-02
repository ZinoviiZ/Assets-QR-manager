package sand_box.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sand_box.repositories.AssetRepository;
import sand_box.repositories.AuthorRepository;
import sand_box.repositories.PhotoRepository;
import sand_box.services.interfaces.AuthorService;
import sand_box.services.pictures.ImageConverter;
import sand_box.services.pictures.QrCode;
import sand_box.tables.Asset;
import sand_box.tables.Author;
import sand_box.tables.images.Photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zinoviy on 8/24/16.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private PhotoRepository photoRepository;

    static final int DEFAULT_ID = -1;
    private static final String PATH = "pictures/author";
    private static final String URL    = "/author/photo";
    private static final String SMALL = "300";
    private static final String MIDDLE = "900";
    private static final String BIG = "2000";

    @Override
    @Transactional
    public Author getAuthor(long id) {
        return authorRepository.findOne(id);
    }

    @Override
    public Author getAuthor(String name) {
        return authorRepository.findAuthorByName(name);
    }

    @Override
    @Transactional
    public List<Author> getAuthors() {return authorRepository.findAll();}

    @Override
    @Transactional
    public List<Asset> getAuthorAssetes(long id) {
        return authorRepository.findOne(id).getAssets();
    }

    @Override
    @Transactional
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void addAuthor(Author author, long[] assets_id) {
        List<Asset> assets = new ArrayList<>();
        for(long id : assets_id) {
            if(id != DEFAULT_ID) {
                assets.add(assetRepository.findAssetById(id));
            }
        }
        author.addAsset(assets);
        authorRepository.save(author);
        QrCode.convertAuthor(PATH+"/qr",author.getId(), author.getId());
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        authorRepository.delete(id);
    }

    @Override
    @Transactional
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void addPhoto(long author_id, MultipartFile file) throws IOException {
        String photo_name = String.valueOf(author_id);
        ImageConverter converter = new ImageConverter(PATH, file.getInputStream(), photo_name);
        int n = converter.converting();
        Author author = authorRepository.findOne(author_id);
        Photo photo = null;
        switch(n) {
            case 1 : {
                photo = new Photo(photo_name+".png",PATH, URL+"/"+SMALL+"/"+photo_name,
                        URL+"/"+SMALL+"/"+photo_name, URL+"/"+SMALL+"/"+photo_name);
                break;
            }
            case 2 : {
                photo = new Photo(photo_name+".png",PATH, URL+"/"+SMALL+"/"+photo_name,
                        URL+"/"+MIDDLE+"/"+photo_name, URL+"/"+MIDDLE+"/"+photo_name);
                break;
            }
            case 3 : {
                photo = new Photo(photo_name+".png",PATH, URL+"/"+SMALL+"/"+photo_name,
                        URL+"/"+MIDDLE+"/"+photo_name, URL+"/"+BIG+"/"+photo_name);
                break;
            }
            default: throw new IOException("Some problems with saving images");
        }
        author.setPhoto(photo);
        photoRepository.save(photo);
    }


    @Override
    @Transactional
    public byte[] getPhoto(long size, long id) throws IOException {
        Author author = authorRepository.findOne(id);
        byte[] result = null;
        Photo photo = author.getPhoto();
        File file = null;
        file = new File(photo.getPath()+"/"+size+"/"+photo.getName());
        if(!file.exists()) {
            if(size == Integer.parseInt(BIG))
                file = new File(photo.getPath()+"/"+size+"/"+photo.getName());
            if(!file.exists()) {
                if(size == Integer.parseInt(BIG))
                    file = new File(photo.getPath()+"/"+size+"/"+photo.getName());
            }
        }
        try(FileInputStream stream = new FileInputStream(file)) {
            result = new byte[stream.available()];
            while (stream.available() > 0) {
                stream.read(result);
            }
        }
        catch (IOException e) {
            System.out.println("Can`t get Photo");
        }
        return result;
    }

    @Override
    @Transactional
    public byte[] getQR(long id) {
        byte[] result = null;
        File file = new File(PATH+"/qr/"+id+".png");
        try(FileInputStream stream = new FileInputStream(file);) {
            result = new byte[stream.available()];
            while (stream.available() > 0) {
                stream.read(result);
            }
        }
        catch (IOException e) {
            System.out.println("Can`t get QR code");
        }
        return result;
    }

//    private File check(String path, long size, String name) {
//        File file = new File(path+"/"+size+"/"+ name);
//        if(!file.exists()) {
//            if(size == Integer.parseInt(BIG))
//                file = new File(path+"/"+size+"/"+name);
//            if(!file.exists()) {
//                if(size == Integer.parseInt(BIG))
//                    file = new File(path+"/"+size+"/"+name);
//            }
//        }
//    }

}
