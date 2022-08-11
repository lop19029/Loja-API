package gft.LojaAPI.dto.produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaProdutoDTO {
    private Long id;
    private String descricao;
    private String unidadeMedida;
    private String foto;
    private BigDecimal preco;
    private Integer qtdEstoque;
}
