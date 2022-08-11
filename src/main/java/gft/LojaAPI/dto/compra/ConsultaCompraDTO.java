package gft.LojaAPI.dto.compra;

import gft.LojaAPI.dto.itemCompra.ConsultaItemCompraDTO;
import gft.LojaAPI.dto.usuario.ConsultaUsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsultaCompraDTO {
    private Long id;
    private ConsultaUsuarioDTO usuario;
    private Date dataVenda;
    private BigDecimal valorVenda;
    private String cepEnvio;
    private List<ConsultaItemCompraDTO> itens;
}
