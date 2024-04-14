package Api.Futbolistas.Civa.Controllers;

import Api.Futbolistas.Civa.Services.FutbolistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class FutbolistaController {
    @Autowired
    private FutbolistaService futbolistaService;
    @GetMapping("/futbolista")
    public ResponseEntity<?> getFutbolista(Pageable pageable){
        return new ResponseEntity<> (futbolistaService.getFutbolistas(pageable), HttpStatus.OK);
    }
    @GetMapping("/futbolista/{id}")
    public ResponseEntity<?> getFutbolistaById(@PathVariable Integer id){
        return new ResponseEntity<>(futbolistaService.getFutbolistaById(id), HttpStatus.OK);
    }
}
