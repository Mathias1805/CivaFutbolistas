package Api.Futbolistas.Civa.Services.Impl;

import Api.Futbolistas.Civa.Repositories.PosicionRepository;
import Api.Futbolistas.Civa.Services.PosicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosicionServiceImpl implements PosicionService {
    @Autowired
    private PosicionRepository posicionRepository;
}
