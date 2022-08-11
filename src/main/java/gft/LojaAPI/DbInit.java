package gft.LojaAPI;

import gft.LojaAPI.entities.ItemCompra;
import gft.LojaAPI.entities.Perfil;
import gft.LojaAPI.entities.Produto;
import gft.LojaAPI.entities.Usuario;
import gft.LojaAPI.imagesUpload.FileUploadManager;
import gft.LojaAPI.services.PerfilService;
import gft.LojaAPI.services.ProdutoService;
import gft.LojaAPI.services.UsuarioService;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private final PerfilService perfilService;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    public DbInit(PerfilService perfilService, UsuarioService usuarioService, ProdutoService produtoService) {
        this.perfilService = perfilService;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Populating database...");

        //Criar perfis
        Perfil admin = new Perfil(null, "ADMIN");
        Perfil user = new Perfil(null, "USER");

        perfilService.salvarPerfil(admin);
        perfilService.salvarPerfil(user);

        //Criar usuarios
        Usuario usuarioAdmin = new Usuario("48067379092", "Luís Rodrigues Lima", "LuisRodriguesLima@dayrep.com", new BCryptPasswordEncoder().encode("Gft2022"), admin);
        Usuario usuarioComum = new Usuario("12281159078", "Kaua Sousa Carvalho", "KauaSousaCarvalho@jourrapide.com", new BCryptPasswordEncoder().encode("Gft2022"), user);

        usuarioService.salvarUsuario(usuarioAdmin);
        usuarioService.salvarUsuario(usuarioComum);

        //Criar produtos

        BufferedImage pngDefault = ImageIO.read(new File("imagens/default.png"));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(pngDefault,"png", os);
        InputStream fis = new ByteArrayInputStream(os.toByteArray());
        MultipartFile multipartFile = new MockMultipartFile("imagem",
                "default.png", "image/png", IOUtils.toByteArray(fis));

        String pastaDeFotos = "C:/LojaAPI/produtos-fotos/";
        FileUploadManager.saveFile(pastaDeFotos, "default.png", multipartFile);

        BufferedImage nike = ImageIO.read(new File("imagens/nike.jpg"));
        ByteArrayOutputStream os2 = new ByteArrayOutputStream();
        ImageIO.write(nike,"jpg", os2);
        InputStream fis2 = new ByteArrayInputStream(os2.toByteArray());
        MultipartFile multipartFile2 = new MockMultipartFile("imagem",
                "nike.jpg", "image/jpg", IOUtils.toByteArray(fis2));

        String pastaDeFotos1 = "C:/LojaAPI/produtos-fotos/1/";
        FileUploadManager.saveFile(pastaDeFotos1, "nike.jpg", multipartFile2);

        BufferedImage fila = ImageIO.read(new File("imagens/fila.jpg"));
        ByteArrayOutputStream os3 = new ByteArrayOutputStream();
        ImageIO.write(fila,"jpg", os3);
        InputStream fis3 = new ByteArrayInputStream(os3.toByteArray());
        MultipartFile multipartFile3 = new MockMultipartFile("imagem",
                "fila.jpg", "image/jpg", IOUtils.toByteArray(fis3));

        String pastaDeFotos2 = "C:/LojaAPI/produtos-fotos/2/";
        FileUploadManager.saveFile(pastaDeFotos2, "fila.jpg", multipartFile3);

        List<ItemCompra> listaVazia = new ArrayList<>();

        Produto tenisNike = new Produto(null, "Tênis Nike", "caixa", "nike.jpg", new BigDecimal(199.90), 56);
        Produto tenisFila = new Produto(null, "Tênis Fila", "caixa", "fila.jpg", new BigDecimal(229.95), 34);

        produtoService.salvarProduto(tenisNike);
        produtoService.salvarProduto(tenisFila);

        System.out.println("Database successfully populated!");


    }
}
