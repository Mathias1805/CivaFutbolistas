package Api.Futbolistas.Civa.Services.Impl;

import Api.Futbolistas.Civa.Models.FutbolistaEntity;
import Api.Futbolistas.Civa.Repositories.FutbolistaRepository;
import Api.Futbolistas.Civa.Services.FutbolistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;


@Service
public class FutbolistaServiceImpl implements FutbolistaService {
    @Autowired
    private FutbolistaRepository futbolistaRepository;
    public Page<FutbolistaEntity> getFutbolistas(Pageable pageable){
        return futbolistaRepository.findAll(pageable);
    }
    @Override
    public Object getFutbolistaById(Integer id) {
        return futbolistaRepository.findById(id).orElse(null);
    }
}
