package gft.LojaAPI;

import gft.LojaAPI.dto.produto.ProdutoMapper;
import gft.LojaAPI.dto.produto.RegistroProdutoDTO;
import gft.LojaAPI.entities.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ProdutoTest {

    @Test
    void deveCriarUmProdutoAPartirDeDTO() {
        RegistroProdutoDTO dto = new RegistroProdutoDTO("Novo produto", "caixa", "foto.png", new BigDecimal(16.90), 6);
        Produto produto = ProdutoMapper.fromDTO(dto);
    }
}
