package gft.LojaAPI.exception;

public class EstoqueDeProdutoInsuficienteParaCompraException extends LojaAPIException{

    private String nomeDoProduto;

    public EstoqueDeProdutoInsuficienteParaCompraException(String nomeDoProduto, String message) {
        super(message);
        this.nomeDoProduto = nomeDoProduto;
    }

    public String getNomeDoProduto(){
        return this.nomeDoProduto;
    }
}
