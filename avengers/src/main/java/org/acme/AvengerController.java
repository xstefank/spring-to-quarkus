package org.acme;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/avengers")
public class AvengerController {

    private final AvengerRepository avengerRepository;

    public AvengerController(AvengerRepository avengerRepository) {
        this.avengerRepository = avengerRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Avenger> getAll() {
        return avengerRepository.findAll();
    }

    @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Avenger getByName(@PathVariable String name) {
        return avengerRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Avenger not found: " + name));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Avenger create(@Valid @RequestBody Avenger avenger) {
        return avengerRepository.save(avenger);
    }
}
