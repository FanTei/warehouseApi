package WarehouseAPI.WarehouseAPI.controller.showcase;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("showcases")
public class ShowcaseRestController {
    @Autowired
    private ShowcaseService showcaseService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute("showcaseForm") Showcase showcase) { //BindingResult bindingResult, Model model)
        showcaseService.create(showcase);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return new ResponseEntity<>(null,headers,HttpStatus.FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<Showcase> update(@ModelAttribute("showcaseForm") Showcase showcase,
                                           @RequestParam(name = "showcaseId") Long showcaseId) {
        final boolean update = showcaseService.update(showcase, showcaseId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return update ? new ResponseEntity<>(null,headers,HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "showcaseId") Long showcaseId) {
        final boolean delete = showcaseService.delete(showcaseId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return delete ? new ResponseEntity<>(null,headers,HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
//    @GetMapping("/showcases")
//    public ResponseEntity<?> read(Model model) {
//        final List<Showcase> showcases = showcaseService.readAll();
//        return showcases != null && !showcases.isEmpty()
//                ? new ResponseEntity<>(showcases, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    @PostMapping("/showcases")
//    public ResponseEntity<?> create(@RequestBody Showcase showcase) {
//        showcaseService.create(showcase);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
