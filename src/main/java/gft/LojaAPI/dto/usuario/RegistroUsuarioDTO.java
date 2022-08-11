package gft.LojaAPI.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistroUsuarioDTO {
    private String cpf;
    private String nome;
    private String email;
    private String senha;
}
