package gft.LojaAPI.controller;

import gft.LojaAPI.dto.produto.ProdutoMapper;
import gft.LojaAPI.dto.produto.RegistroProdutoDTO;
import gft.LojaAPI.entities.Produto;
import gft.LojaAPI.imagesUpload.FileUploadManager;
import gft.LojaAPI.services.ProdutoService;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/v1/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    public final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto cadastrarProduto(RegistroProdutoDTO dto,
                                    @RequestParam MultipartFile imagem) throws IOException {
        Produto produto = produtoService.salvarProduto(ProdutoMapper.fromDTO(dto));

        //Carregar imagem ou definir default.png
        if (!(imagem.isEmpty())){
            //Set imagem path
            String nomeDoArquivo = StringUtils.cleanPath(imagem.getOriginalFilename());
            produto.setFoto(nomeDoArquivo);

            //Save imagem in directory
            String pastaDeFotos = "C:/LojaAPI/produtos-fotos/" + produto.getId();
            FileUploadManager.saveFile(pastaDeFotos, nomeDoArquivo, imagem);
            return produtoService.salvarProduto(produto);
        }
        else {
            //Set default image
            produto.setFoto("default.png");
            return produtoService.salvarProduto(produto);
        }
    }

    @GetMapping(value = "/get-foto", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getFoto(@RequestParam Long id) throws IOException {
        Produto produto = produtoService.buscarProdutoPorId(id);
        Path imagePath = Path.of(produto.getFotoPath());
        return Files.readAllBytes(imagePath);
    }

    @GetMapping
    public Page<Produto> listarTodosOsProdutos(@PageableDefault Pageable pageable){
        return produtoService.listarTodosOsProdutos(pageable);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable Long id){

        Produto produto = produtoService.buscarProdutoPorId(id);

        //Apagar foto do produto em disco
        try {
            FileUtils.deleteDirectory(new File("C:/LojaAPI/produtos-fotos/" + id));
        } catch (IOException e) {
            System.out.println("Não foi possivel apagar a foto "+ e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

        produtoService.excluirProduto(produto);
        return ResponseEntity.ok().build();
    }

}
