package com.emergency.EmergencyTracker.model;

import com.emergency.EmergencyTracker.enums.NivelSolicitacaoAjudaEnum;
import com.emergency.EmergencyTracker.enums.StatusSolicitacaoAjudaEnum;
import com.emergency.EmergencyTracker.enums.TipoSolicitacaoAjudaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "solicitacao_ajuda")
public class SolicitacaoAjuda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_solicitacao", nullable = false)
    private TipoSolicitacaoAjudaEnum tipoSolicitacao;

    @Lob
    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacaoAjudaEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelSolicitacaoAjudaEnum nivel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @CreationTimestamp
    @Column(name = "dh_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "dh_update")
    private LocalDateTime dataAtualizacao;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}
