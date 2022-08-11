package gft.LojaAPI.dto.compra;

import gft.LojaAPI.dto.itemCompra.ItemCompraMapper;
import gft.LojaAPI.dto.usuario.ConsultaUsuarioDTO;
import gft.LojaAPI.dto.usuario.UsuarioMapper;
import gft.LojaAPI.entities.Compra;
import gft.LojaAPI.entities.Usuario;

import java.util.stream.Collectors;

public class CompraMapper {
    public static Compra fromDTO(RegistroCompraDTO dto){
        return new Compra(null, null, dto.getDataVenda(), dto.getCepEnvio(),
                dto.getItens().stream().map(ItemCompraMapper::fromDTO).collect(Collectors.toList()));
    }

    public static ConsultaCompraDTO fromEntity(Compra en){
        ConsultaUsuarioDTO usuario = UsuarioMapper.fromEntity(en.getUsuario());
        return new ConsultaCompraDTO(en.getId(), usuario, en.getDataVenda(),
                en.getValorVenda(), en.getCepEnvio(),
                en.getItens().stream().map(ItemCompraMapper::fromEntity).collect(Collectors.toList()));
    }
}
