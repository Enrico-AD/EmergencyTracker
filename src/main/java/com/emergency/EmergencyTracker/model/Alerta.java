package com.emergency.EmergencyTracker.model;

import com.emergency.EmergencyTracker.enums.NivelAlertaEnum;
import com.emergency.EmergencyTracker.enums.StatusAlertaEnum;
import com.emergency.EmergencyTracker.enums.TipoAlertaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Alerta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta", nullable = false)
    private TipoAlertaEnum tipoAlerta;

    @Lob
    private String conteudo;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAlertaEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelAlertaEnum nivel;

    @Column(name = "id_sinal", insertable = false, updatable = false)
    private Long idSinal;

    @Column(name = "id_localizacao", insertable = false, updatable = false)
    private Long idLocalizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sinal", referencedColumnName = "id")
    private Sinal sinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localizacao", referencedColumnName = "id", nullable = false)
    private Localizacao localizacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "alerta_usuario",
            joinColumns = @JoinColumn(name = "id_alerta"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Usuario> usuarios;

    @CreationTimestamp
    @Column(name = "dh_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "dh_update")
    private LocalDateTime dataAtualizacao;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}