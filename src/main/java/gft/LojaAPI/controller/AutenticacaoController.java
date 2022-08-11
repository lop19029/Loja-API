package gft.LojaAPI.controller;

import gft.LojaAPI.dto.auth.AutenticacaoDTO;
import gft.LojaAPI.dto.auth.TokenDTO;
import gft.LojaAPI.services.AutenticacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    public AutenticacaoController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }


    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO autenticacaoDTO){

        try {
            return ResponseEntity.ok(autenticacaoService.autenticar(autenticacaoDTO));

        }catch (AuthenticationException ae) {
            ae.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
