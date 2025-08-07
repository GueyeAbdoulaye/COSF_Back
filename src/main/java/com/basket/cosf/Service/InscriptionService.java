package com.basket.cosf.Service;

import com.basket.cosf.Dto.InscriptionDto;
import com.basket.cosf.Model.Inscription;
import com.basket.cosf.Repository.InscriptionRepository;
import com.basket.cosf.Service.Interface.AbstractService;
import com.basket.cosf.commons.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscriptionService implements AbstractService<InscriptionDto> {

    private final ObjectsValidator<InscriptionDto> validator;
    private final InscriptionRepository inscriptionRepository ;

    @Override
    public Integer save(InscriptionDto dto) {
        validator.validate(dto);
        Inscription inscription = InscriptionDto.toEntity(dto);
        return inscriptionRepository.save(inscription).getId();
    }

    @Override
    public List<InscriptionDto> findAll() {
        return inscriptionRepository.findAll()
                .stream().map(InscriptionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public InscriptionDto findById(Integer id) {

        return inscriptionRepository.findById(id)
                .map(InscriptionDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Inscription not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        inscriptionRepository.deleteById(id);
    }
}
