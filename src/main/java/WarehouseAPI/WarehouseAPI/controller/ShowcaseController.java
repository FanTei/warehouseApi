package WarehouseAPI.WarehouseAPI.controller;

import WarehouseAPI.WarehouseAPI.entity.Showcase;
import WarehouseAPI.WarehouseAPI.service.ShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("showcases")
public class ShowcaseController {
    @Autowired
    private ShowcaseService showcaseService;

    @GetMapping("")
    public String showcasesList(Model model) {
        final List<Showcase> showcases = showcaseService.readAll();
        model.addAttribute("showcases", showcases);
        return "showcasesPage";
    }

    @GetMapping("/create")
    public String createShowcase(Model model) {
        model.addAttribute("showcase", new Showcase());
        return "createShowcasePage";
    }

    @GetMapping("/update")
    public String updateShowcase(@RequestParam(name = "showcaseId") Long showcaseId,
                                 Model model) {
        Showcase updatingShowcase = showcaseService.read(showcaseId);
        model.addAttribute("showcase", updatingShowcase);
        return "editShowcasePage";
    }

    @PostMapping("/create")
    public String create(@Valid Showcase showcase, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "createShowcasePage";
        showcaseService.create(showcase);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Showcase showcase,
                         @RequestParam Long showcaseId,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "editShowcasePage";
        final boolean update = showcaseService.update(showcase, showcaseId);
        return "redirect:/";
    }

    @DeleteMapping()
    public ResponseEntity<?> delete(@RequestParam Long showcaseId) {
        final boolean delete = showcaseService.delete(showcaseId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:9090/");
        return delete ? new ResponseEntity<>(null, headers, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
