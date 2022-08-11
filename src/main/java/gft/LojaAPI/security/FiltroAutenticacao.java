package gft.LojaAPI.security;

import gft.LojaAPI.entities.Usuario;
import gft.LojaAPI.services.AutenticacaoService;
import gft.LojaAPI.services.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {

    private AutenticacaoService autenticacaoService;
    private UsuarioService usuarioService;

    public FiltroAutenticacao(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //Get Token
        String header = request.getHeader("Authorization");
        String token = null;
        if(header != null && header.startsWith("Bearer ")) {
            token = header.substring(7, header.length());
        }

        //Verify Token
        if(autenticacaoService.verificaToken(token)){
            String idUsuario = autenticacaoService.retornarIdUsuario(token); //Get User Id
            Usuario usuario = usuarioService.buscarUsuarioPorCpf(idUsuario); //Get User by Id
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities())); //Get Spring Security Context
        }
        filterChain.doFilter(request, response);
    }
}
