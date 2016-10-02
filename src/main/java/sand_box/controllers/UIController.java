package sand_box.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sand_box.services.interfaces.AssetService;
import sand_box.services.interfaces.AuthorService;
import sand_box.tables.Asset;
import sand_box.tables.Author;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Zinoviy on 9/5/16.
 */
@Controller
public class UIController {
    static final int DEFAULT_ID = -1;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/admin")
    public String test() {
        return "admin";
    }

    @RequestMapping(value = "/")
    public String index(Model model)
    {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("userName", user.getUsername());
            model.addAttribute("userRoles", user.getAuthorities());
        }
        catch (Exception e) {
            model.addAttribute("userRoles", "GUEST");
        }

        model.addAttribute("assets", assetService.getAssets());
        model.addAttribute("authors", authorService.getAuthors());
        return "index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }

    @RequestMapping("/asset_add_page")
    public String toAddAssetPage(Model model) {
        model.addAttribute("authors", authorService.getAuthors());
        return "asset_add_page";
    }

    @RequestMapping("/author_add_page")
    public String toAddAuthorPage(Model model) {
        model.addAttribute("assets",assetService.getAssets());
        return "author_add_page";
    }

    @RequestMapping(value = "/asset", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAsset(@RequestParam long id)
    {
        assetService.deleteAsset(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "/editor/asset", method = RequestMethod.POST)
    public String addAsset(@RequestParam (value = "author") long author_id,
                           @RequestParam String title,
                           @RequestParam String description,
                           @RequestParam int price,
                           @RequestParam String tag,
                           @RequestParam MultipartFile image,
                           @RequestParam MultipartFile[] pictures,
                           Model model) throws IOException {
        Author author = (author_id!=DEFAULT_ID)  ? authorService.getAuthor(author_id) : null;
        List<String> tags = Arrays.asList(tag.split(" "));
        long id = assetService.addAsset(new Asset(title,description,author,price, new Date(), null, tags));
        assetService.addMainPicture(id, image);
        assetService.addPictures(id, pictures);
        model.addAttribute("assets", assetService.getAssets());
        model.addAttribute("authors", authorService.getAuthors());
        return "redirect:/";
    }

    @RequestMapping(value = "/editor/asset", method = RequestMethod.PUT)
    public Asset updateAsset(@RequestBody Asset asset)
    {
        assetService.updateAsset(asset);
        return assetService.findById(asset.getId());
    }

    @RequestMapping(value = "/editor/author", method = RequestMethod.POST)
    public String addAuthor(@RequestParam(value = "image")MultipartFile image,
                            @RequestParam (value = "asset") long[] assets_id,
                            @RequestParam String name,
                            @RequestParam String phone,
                            @RequestParam String email,
                            @RequestParam String website,
                            Model model) throws IOException {
        Author author = new Author(name,phone,email,website);

        authorService.addAuthor(author, assets_id);
        authorService.addPhoto(authorService.getAuthor(author.getName()).getId(),image);

        model.addAttribute("assets", assetService.getAssets());
        model.addAttribute("authors", authorService.getAuthors());
        return "redirect:/";
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.PUT)
    public Author updateAuthor(@RequestBody Author author)
    {
        authorService.updateAuthor(author);
        return authorService.getAuthor(author.getId());
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") long id)
    {
        authorService.deleteAuthor(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/author/{id}/photo",method = RequestMethod.POST)
    public void addAuthorPhoto(@RequestParam(value = "image")MultipartFile image, @PathVariable("id") long id) throws IOException {
        authorService.addPhoto(id, image);
    }

    @RequestMapping(value = "/author/photo/{size}/{id}")
    public void getAuthorPhoto(HttpServletResponse response, @PathVariable("size")long size, @PathVariable("id") long id)
    {
        try {
            byte[] bytes = authorService.getPhoto(size,id);
            response.setContentType("image/png");
            response.getOutputStream().write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/asset/{id}/photo/main",method = RequestMethod.POST)
    public void addAssetMainPhoto(@RequestParam(value = "image")MultipartFile image, @PathVariable("id") long id) throws IOException {
        assetService.addMainPicture(id, image);
    }

    @RequestMapping(value = "/asset/photo/main/{size}/{id}")
    public void getAssetMainPhoto(HttpServletResponse response, @PathVariable("size")long size, @PathVariable("id") long id) {
        try {
            byte[] bytes = assetService.getMainPicture(size,id);
            response.setContentType("image/png");
            response.getOutputStream().write(bytes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/asset/photo/secondary/{size}/{name}")
    public void getAssetPictures(HttpServletResponse response, @PathVariable long size, @PathVariable String name) {
        try {
            byte[] bytes = assetService.getPictures(size, name);
            response.setContentType("image/png");
            response.getOutputStream().write(bytes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/author/qr/{id}")
    public void getAuthorQR(HttpServletResponse response, @PathVariable long id) {
        try {
            byte[] bytes = authorService.getQR(id);
            response.setContentType("image/png");
            response.getOutputStream().write(bytes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
