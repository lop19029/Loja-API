package gft.LojaAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ItemCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    private Compra compra;

    @JsonManagedReference
    @ManyToOne
    private Produto produto;

    @NotNull(message = "Quantidade não pode ser nula.")
    @Min(value = 1, message = "Quantidade não pode menor do que 1.")
    private Integer qtdProduto;

    @NotNull(message = "Valor unitario não pode ser nulo.")
    @Min(value = 0, message = "Valor unitario não pode ser negativo.")
    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;


    public ItemCompra(Long id, Compra compra, Produto produto, Integer qtdProduto, BigDecimal valorUnitario) {
        this.id = id;
        this.compra = compra;
        this.produto = produto;
        this.qtdProduto = qtdProduto;
        this.valorUnitario = valorUnitario;
        this.valorTotal = this.valorUnitario.multiply(new BigDecimal(this.qtdProduto));
    }
}
