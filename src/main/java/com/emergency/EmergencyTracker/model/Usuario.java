package com.emergency.EmergencyTracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String telefone;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<SolicitacaoAjuda> solicitacoesAjuda;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Dispositivo> dispositivos;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
    private List<Alerta> alertas;
}