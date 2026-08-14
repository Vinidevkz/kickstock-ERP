package com.erp.kicksotck.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_fornecedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome_fornecedor;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 60)
    private String password_fornecedor;
    @Column(nullable = false, unique = true)
    private String cnpj_fornecedor;
    @OneToMany(mappedBy = "id_fornecedor")
    @JsonIgnore
    private List<Lote> lotes;
    @OneToMany(mappedBy = "id_fornecedor")
    @JsonIgnore
    private List<Contrato> contratos;
    @OneToMany(mappedBy = "id_fornecedor")
    @JsonIgnore
    private List<Solicitacoes> solicitacoes;
    @OneToMany(mappedBy = "id_fornecedor")
    @JsonIgnore
    private List<VendasECompras> vendasECompras;
    private LocalDateTime created_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_FORNECEDOR"));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
