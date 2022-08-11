package gft.LojaAPI.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    private String cpf;

    @NotNull(message = "Nome não pode ser nulo.")
    @NotEmpty(message = "Nome não pode ser vazio.")
    @Size(min = 2, message = "Nome não pode ser menor a 2 carateres.")
    @Size(max = 255, message = "Nome não pode ser maior a 255 carateres.")
    private String nome;

    @Email
    @NotNull(message = "Email não pode ser nulo.")
    @NotEmpty(message = "Email não pode ser vazio.")
    @Size(min = 2, message = "Email não pode ser menor a 2 carateres.")
    @Size(max = 255, message = "Email não pode ser maior a 255 carateres.")
    private String email;

    @NotNull(message = "Senha não pode ser nulo.")
    @NotEmpty(message = "Senha não pode ser vazia.")
    private String senha;

    @ManyToOne
    private Perfil perfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.perfil);
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public String getUsername() {
        return getCpf();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", perfil=" + perfil +
                '}';
    }
}
