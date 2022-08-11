package gft.LojaAPI.dto.itemCompra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaItemCompraDTO {
    private Long id;
    private Long compraId;
    private Long produtoId;
    private Integer qtdProduto;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

}
