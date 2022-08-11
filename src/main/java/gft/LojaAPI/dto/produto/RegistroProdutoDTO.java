package gft.LojaAPI.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistroProdutoDTO {
    private String descricao;
    private String unidadeMedida;
    private String foto;
    private BigDecimal preco;
    private Integer qtdEstoque;
}
