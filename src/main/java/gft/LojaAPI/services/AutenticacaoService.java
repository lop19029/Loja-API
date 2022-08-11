package gft.LojaAPI.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gft.LojaAPI.dto.auth.AutenticacaoDTO;
import gft.LojaAPI.dto.auth.TokenDTO;
import gft.LojaAPI.entities.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;

@Service
public class AutenticacaoService {

    private final AuthenticationManager authenticationManager;

    @Value("${loja.jwt.expiration}")
    private String expiration;

    @Value("${loja.jwt.secret}")
    private String secret;

    @Value("${loja.jwt.issuer}")
    private String issuer;

    public AutenticacaoService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public TokenDTO autenticar(AutenticacaoDTO autenticacaoDTO) throws AuthenticationException {

        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(autenticacaoDTO.getCpf(), autenticacaoDTO.getSenha()));

        String token = gerarToken(authenticate);

        return new TokenDTO(token);
    }

    private Algorithm criarAlgoritmo(){
        return Algorithm.HMAC256(secret);
    }

    private String gerarToken(Authentication authenticate) {
        Usuario principal = (Usuario)authenticate.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(dataExpiracao)
                .withSubject(principal.getCpf())
                .sign(this.criarAlgoritmo());
    }

    public boolean verificaToken(String token) {

        try{
            if(token==null)
                return false;
            JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
            return true;

        } catch (JWTVerificationException exception) {

            return false;
        }
    }

    public String retornarIdUsuario(String token) {
        String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();
        return subject;
    }
}
