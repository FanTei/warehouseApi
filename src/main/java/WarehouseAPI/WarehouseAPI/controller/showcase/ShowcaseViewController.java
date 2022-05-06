package WarehouseAPI.WarehouseAPI.controller.showcase;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/showcases")
public class ShowcaseViewController {
    @Autowired
    ShowcaseService showcaseService;

    @GetMapping("")
    public String showcasesList(Model model) {
        final List<Showcase> showcases = showcaseService.readAll();
        model.addAttribute("allShowcases", showcases);
        return "showcasesPage";
    }

    @GetMapping("/update")
    public String updateShowcase(@RequestParam(name = "showcaseId") Long showcaseId,
                                 Model model) {
        Showcase updatingShowcase = showcaseService.read(showcaseId);
        model.addAttribute("showcaseForm", updatingShowcase);
        return "interactionShowcasePage";
    }

    @GetMapping("/create")
    public String createShowcase(Model model) {
        model.addAttribute("showcaseForm", new Showcase());
        return "interactionShowcasePage";
    }
}
//    @GetMapping("/showcases/{id}")
//    public ResponseEntity<Showcase> read(@PathVariable(name = "id") Long id) {
//        final Showcase showcase = showcaseService.read(id);
//        return showcase != null
//                ? new ResponseEntity<>(showcase, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }