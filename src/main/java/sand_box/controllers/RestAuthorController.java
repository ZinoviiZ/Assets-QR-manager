package sand_box.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sand_box.classes.factory.Factory;
import sand_box.tables.Asset;
import sand_box.tables.Author;
import sand_box.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zinoviy on 8/24/16.
 */
@RestController
@RequestMapping("/api/author")
public class RestAuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/{id}")
    public Object getAuthorById(@PathVariable("id") long id)
    {
        Author author = authorService.getAuthor(id);
        return author!= null ? Factory.getAuthor(author) : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/all")
    public Object getAuthors()
    {
        List<Author> authors = authorService.getAuthors();
        List<Object> result = new ArrayList<>();
        for(Author author : authors) {
            result.add(Factory.getAuthor(author));
        }
        return (result != null) ? result : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}/assets")
    public Object getAuthorAssets(@PathVariable("id") long id)
    {
        List<Asset> assets = authorService.getAuthorAssetes(id);
        List<Object> result = new ArrayList<>();
        for(Asset asset : assets) {
            result.add(Factory.getAsset(asset));
        }
        return (result!= null) ? result : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

}