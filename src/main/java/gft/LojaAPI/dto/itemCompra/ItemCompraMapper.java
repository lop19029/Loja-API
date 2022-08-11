package gft.LojaAPI.dto.itemCompra;

import gft.LojaAPI.entities.ItemCompra;
import gft.LojaAPI.entities.Produto;

public class ItemCompraMapper {
    public static ItemCompra fromDTO(RegistroItemCompraDTO dto){
        Produto produto = new Produto(dto.getProdutoId());
        return new ItemCompra(null, null, produto, dto.getQtdProduto(), dto.getValorUnitario());
    }

    public static ConsultaItemCompraDTO fromEntity(ItemCompra en){
        return new ConsultaItemCompraDTO(en.getId(), en.getCompra().getId(), en.getProduto().getId(),
                en.getQtdProduto(), en.getValorUnitario(), en.getValorTotal());
    }
}
