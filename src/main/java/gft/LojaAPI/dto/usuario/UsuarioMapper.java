package gft.LojaAPI.dto.usuario;

import gft.LojaAPI.entities.Usuario;

public class UsuarioMapper {
    public static Usuario fromDTO(RegistroUsuarioDTO dto){
        return new Usuario(dto.getCpf(), dto.getNome(), dto.getEmail(), dto.getSenha(), null);
    }

    public static ConsultaUsuarioDTO fromEntity(Usuario en){
        return new ConsultaUsuarioDTO(en.getCpf(), en.getNome(), en.getEmail());
    }
}
