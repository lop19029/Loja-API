package gft.LojaAPI.services;

import gft.LojaAPI.entities.Compra;
import gft.LojaAPI.repositories.CompraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CompraService {

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public Compra salvarCompra (Compra compra){
        return compraRepository.save(compra);
    }

    public Page<Compra> listarTodasAsCompras(Pageable pageable){
        return compraRepository.findAll(pageable);
    }

    public Compra buscarCompraPorId(Long id){
        Optional<Compra> compra = compraRepository.findById(id);
        return compra.orElseThrow(() -> new EntityNotFoundException("Compra não encontrada"));
    }

    public Compra editarCompra(Compra compra, Long id){
        Compra compraOriginal = this.buscarCompraPorId(id);
        compra.setId(compraOriginal.getId());
        return compraRepository.save(compra);
    }

    public void excluirCompra(Long id){
        Compra compra = this.buscarCompraPorId(id);
        compraRepository.delete(compra);
    }
}
