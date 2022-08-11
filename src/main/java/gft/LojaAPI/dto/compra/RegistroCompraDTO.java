package gft.LojaAPI.dto.compra;

import gft.LojaAPI.dto.itemCompra.RegistroItemCompraDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroCompraDTO {
    private Date dataVenda;
    private String cepEnvio;
    private List<RegistroItemCompraDTO> itens;
}
