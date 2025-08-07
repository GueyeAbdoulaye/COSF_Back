package com.basket.cosf.Controller;

import com.basket.cosf.Dto.InscriptionDto;
import com.basket.cosf.Service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inscription")
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @PostMapping("/save")
    public Integer saveInscription(@RequestBody InscriptionDto inscriptionDto) {
        return inscriptionService.save(inscriptionDto);
    }

    @GetMapping("/all")
    public Set<InscriptionDto> getAllInscriptions() {
        List<InscriptionDto> inscriptions = inscriptionService.findAll();
        Set<InscriptionDto> inscriptionSet = Set.copyOf(inscriptions);
        return inscriptionSet;
    }

    @GetMapping("/{id}")
    public InscriptionDto getInscriptionById(@PathVariable Integer id) {
        return inscriptionService.findById(id);
    }

    @DeleteMapping("/delete")
    public void deleteInscription(@RequestParam Integer id) {
        inscriptionService.delete(id);
    }

}
