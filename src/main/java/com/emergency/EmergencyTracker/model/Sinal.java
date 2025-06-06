package com.emergency.EmergencyTracker.model;

import com.emergency.EmergencyTracker.enums.ProcessamentoEnum;
import com.emergency.EmergencyTracker.enums.TipoSinalEnum;
import com.emergency.EmergencyTracker.enums.UnidadeSinalEnum;
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
public class Sinal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sinal", nullable = false)
    private TipoSinalEnum tipoSinal;

    @Column(nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadeSinalEnum unidade;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "processamento", nullable = false)
    private ProcessamentoEnum processamento;

    @Column(name = "id_localizacao", insertable = false, updatable = false)
    private Long idLocalizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_localizacao", referencedColumnName = "id", nullable = false)
    private Localizacao localizacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sinal_dispositivo",
            joinColumns = @JoinColumn(name = "sinal_id"),
            inverseJoinColumns = @JoinColumn(name = "dispositivo_id")
    )
    private List<Dispositivo> dispositivos;

    @CreationTimestamp
    @Column(name = "dh_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "dh_update")
    private LocalDateTime dataAtualizacao;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}