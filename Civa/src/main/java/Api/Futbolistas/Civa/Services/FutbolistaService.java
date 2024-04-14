package Api.Futbolistas.Civa.Services;

import Api.Futbolistas.Civa.Models.FutbolistaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FutbolistaService {
    Page<FutbolistaEntity> getFutbolistas(Pageable pageable);
    Object getFutbolistaById(Integer id);
}
