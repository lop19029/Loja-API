package gft.LojaAPI.services;

import gft.LojaAPI.entities.Perfil;
import gft.LojaAPI.repositories.PerfilRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public Perfil salvarPerfil(Perfil perfil){
        return perfilRepository.save(perfil);
    }
}
