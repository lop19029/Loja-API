package gft.LojaAPI.dto.produto;

import gft.LojaAPI.entities.Produto;

public class ProdutoMapper {
    public static Produto fromDTO(RegistroProdutoDTO dto){
        return new Produto(null, dto.getDescricao(), dto.getUnidadeMedida(), dto.getFoto(), dto.getPreco(),
                dto.getQtdEstoque());
    }

    public static ConsultaProdutoDTO fromEntity(Produto en){
        return new ConsultaProdutoDTO(en.getId(), en.getDescricao(), en.getUnidadeMedida(), en.getFotoPath(),
                en.getPreco(), en.getQtdEstoque());
    }
}
