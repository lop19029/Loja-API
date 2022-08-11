package gft.LojaAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @NotNull(message = "Unidade de medida não pode ser nula.")
    @NotEmpty(message = "É preciso digitar uma unidade de medida.")
    private String unidadeMedida;

    private String foto;

    @NotNull(message = "Preço não pode ser nulo.")
    @Min(value = 0, message = "Preço não pode ser negativo.")
    private BigDecimal preco;

    @NotNull(message = "Quantidade de estoque não pode ser nula.")
    @Min(value = 0, message = "Quantidade de estoque não pode ser menor do que 0.")
    private Integer qtdEstoque;

    @JsonBackReference
    @OneToMany(mappedBy = "produto", cascade = CascadeType.REMOVE)
    private List<ItemCompra> itens;

    private boolean foraDeEstoque;

    public Produto(Long produtoId) {
        this.id = produtoId;
    }

    public Produto(Long id, String descricao, String unidadeMedida, String foto, BigDecimal preco, Integer qtdEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.foto = foto;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.itens = new ArrayList<>();
        if(qtdEstoque <= 0){
            this.foraDeEstoque = true;
        } else {this.foraDeEstoque = false;}
    }

    @Transient
    public String getFotoPath() {
        if (foto == null || id == null) {
            return null;
        }
        else if (foto.equals("default.png")){
            return "C:/LojaAPI/produtos-fotos/default.png";
        }
        else { return "C:/LojaAPI/produtos-fotos/" + id + "/" + foto;}
    }
}
