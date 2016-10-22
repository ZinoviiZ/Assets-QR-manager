package sand_box.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sand_box.classes.factory.Factory;
import sand_box.tables.Asset;
import sand_box.services.interfaces.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Zinoviy on 8/24/16.
 */
// RestController for giving information about Asset to Android
@RestController
@RequestMapping("/api/asset")
public class RestAssetController {
    @Autowired
    private AssetService assetService;

    @RequestMapping(value = "/")
    public Object getAsset(@RequestParam String title)
    {
        List<Asset> assets = assetService.findByTitle(title);
        List<Object> result = new ArrayList<>();
        for(Asset asset : assets) {
            result.add(Factory.getAsset(asset));
        }
        return (result!= null) ? result : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/all")
    public Object getAssets() {
        List<Asset> assets = assetService.getAssets();
        List<Object> result = new ArrayList<>();
        for(Asset asset : assets) {
            result.add(Factory.getAsset(asset));
        }
        return (result!= null) ? result : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}")
    public Object getAsset(@PathVariable("id") long id)
    {
        Asset asset  = assetService.findById(id);
        return (asset != null) ? Factory.getAsset(asset) : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/tags")
    public Set<String> getTags()
    {
        return assetService.getTags();
    }

    @RequestMapping(value = "/tag/{name}")
    public Object getAssetsByTag(@PathVariable("name") String tag)
    {
        List<Asset> assets = assetService.findByTag(tag);
        List<Object> result = new ArrayList<>();
        for(Asset asset : assets) {
            result.add(Factory.getAsset(asset));
        }
        return (result!= null) ? result : new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }
}
