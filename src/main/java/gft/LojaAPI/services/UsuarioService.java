package gft.LojaAPI.services;

import gft.LojaAPI.entities.Usuario;
import gft.LojaAPI.repositories.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarUsuarioPorCpf(String cpf) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findByCpf(cpf);

        if (optionalUsuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        return optionalUsuario.get();
    }

    public Usuario obterUsuarioLogado(){
        try{
            Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return usuarioLogado;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new EntityNotFoundException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return buscarUsuarioPorCpf(username);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
