package gft.LojaAPI.controller;

import gft.LojaAPI.dto.compra.CompraMapper;
import gft.LojaAPI.dto.compra.ConsultaCompraDTO;
import gft.LojaAPI.dto.compra.RegistroCompraDTO;
import gft.LojaAPI.entities.Compra;
import gft.LojaAPI.entities.ItemCompra;
import gft.LojaAPI.entities.Produto;
import gft.LojaAPI.entities.Usuario;
import gft.LojaAPI.exception.EstoqueDeProdutoInsuficienteParaCompraException;
import gft.LojaAPI.services.CompraService;
import gft.LojaAPI.services.ProdutoService;
import gft.LojaAPI.services.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/compras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompraController {

    private final CompraService compraService;
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    public CompraController(CompraService compraService, ProdutoService produtoService, UsuarioService usuarioService) {
        this.compraService = compraService;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Page<ConsultaCompraDTO> listarTodasAsCompras(@PageableDefault Pageable pageable){
        return compraService.listarTodasAsCompras(pageable).map(CompraMapper::fromEntity);
    }

    @PostMapping
    public ConsultaCompraDTO realizarCompra(@RequestBody RegistroCompraDTO dto){
        Compra compra = compraService.salvarCompra(CompraMapper.fromDTO(dto));

        //Garante que as mudanças nos produtos sejam feitos somente
        // se todos os produtos da compra passarem a validação de estoque disponivel
        List<Produto> produtosParaSalvar = new ArrayList<>();

        //Adicionar referencia a Produto e Compra para cada ItemCompra
        for (ItemCompra i:
             compra.getItens()) {
            Produto produto = produtoService.buscarProdutoPorId(i.getProduto().getId());

            //Checar disponibilidade do produto
            try{
                produtoService.checarDisponibilidadeDosProduto(produto, i.getQtdProduto());
            } catch (EstoqueDeProdutoInsuficienteParaCompraException ex){
                compraService.excluirCompra(compra.getId());
                throw ex;
            }

            //Criar copia do produto para evitar modificar o original caso a compra não possa ser finalizada
            Produto produtoCopia = new Produto(produto.getId(), produto.getDescricao(), produto.getUnidadeMedida(),
                    produto.getFoto(), produto.getPreco(), produto.getQtdEstoque());

            //Reduzir quantidade de estoque do produto
            int novoProdutoEstoque = produtoCopia.getQtdEstoque() - i.getQtdProduto();
            produtoCopia.setQtdEstoque(novoProdutoEstoque);

            if(produtoCopia.getQtdEstoque() <= 0){
                produtoCopia.setForaDeEstoque(true);
            }

            //Colocar o produto em espera para ser salvo
            produtosParaSalvar.add(produtoCopia);

            i.setProduto(produto);
            i.setCompra(compra);
        }

        //Persistir mudanças nos produtos
        produtoService.salvarListaProdutos(produtosParaSalvar);

        //Adicionar informações do usuario que realiza a compra
        Usuario usuario = usuarioService.obterUsuarioLogado();
        compra.setUsuario(usuario);


        //Salvar alterações
        Compra compraSalva = compraService.salvarCompra(compra);
        return CompraMapper.fromEntity(compraSalva);
    }

    @DeleteMapping("{id}")
    public void excluirCompra(@PathVariable Long id){
        compraService.excluirCompra(id);
    }
}
