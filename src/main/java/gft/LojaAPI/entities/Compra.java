package gft.LojaAPI.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonManagedReference
    @ManyToOne
    private Usuario usuario;

    @NotNull(message = "Data de venda não pode ser nula.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataVenda;

    @NotNull(message = "Valor de venda não pode ser nulo.")
    @Min(value = 0, message = "Valor de vanda não pode ser menor do que 0")
    private BigDecimal valorVenda;

    @NotNull(message = "CEP para envio não pode ser nulo")
    @NotEmpty(message = "Precisa inserir um CEP para envio")
    @Min(value = 8, message = "CEP inválido, precisa ter 8 digitos")
    private String cepEnvio;

    @JsonBackReference
    @OneToMany(mappedBy = "compra", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ItemCompra> itens;

    public Compra(Long id, Usuario usuario, Date dataVenda, String cepEnvio, List<ItemCompra> itens) {
        this.id = id;

        this.usuario = usuario;
        this.dataVenda = dataVenda;
        this.cepEnvio = cepEnvio;
        this.valorVenda = new BigDecimal("0");
        this.itens = itens;
        for (ItemCompra i:
                itens
             ) {

            BigDecimal valorTotal = i.getValorTotal();
            BigDecimal soma = this.valorVenda.add(valorTotal);
            setValorVenda(soma);
        }
    }

}
