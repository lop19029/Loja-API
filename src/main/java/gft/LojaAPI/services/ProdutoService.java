package gft.LojaAPI.services;

import gft.LojaAPI.entities.Produto;
import gft.LojaAPI.exception.EstoqueDeProdutoInsuficienteParaCompraException;
import gft.LojaAPI.repositories.ProdutoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> salvarListaProdutos(List<Produto> produtos){
        return produtoRepository.saveAll(produtos);
    }

    public Page<Produto> listarTodosOsProdutos(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    public Produto buscarProdutoPorId(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public void checarDisponibilidadeDosProduto(Produto produto, Integer quantidadeASerComprada){
        if((produto.getQtdEstoque() - quantidadeASerComprada) < 0){
            throw new EstoqueDeProdutoInsuficienteParaCompraException(produto.getDescricao(), "Estoque insuficiente");
        }
    }

    public void excluirProduto(Produto produto){
        produtoRepository.delete(produto);
    }
}
