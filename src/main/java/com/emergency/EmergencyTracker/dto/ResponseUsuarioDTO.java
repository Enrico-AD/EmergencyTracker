package com.emergency.EmergencyTracker.dto;

import com.emergency.EmergencyTracker.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1534244992322023678L;

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public ResponseUsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
    }
}