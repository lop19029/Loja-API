package gft.LojaAPI.exception;

import gft.LojaAPI.dto.APIErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ EstoqueDeProdutoInsuficienteParaCompraException.class })
    public ResponseEntity<APIErrorDTO> handlePartidoAPIException(EstoqueDeProdutoInsuficienteParaCompraException ex, WebRequest request) {

        String error = "Estoque disponivel insuficiente para realizar a compra";

        String message = "Erro ao tentar realizar compra de " + ex.getNomeDoProduto() + " - " + ex.getMessage();

        APIErrorDTO apiErrorDTO = new APIErrorDTO(message, error, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<APIErrorDTO>(apiErrorDTO, new HttpHeaders(),apiErrorDTO.getStatus());
    }

}

