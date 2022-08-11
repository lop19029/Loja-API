package gft.LojaAPI.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaUsuarioDTO {
    private String cpf;
    private String nome;
    private String email;
}
