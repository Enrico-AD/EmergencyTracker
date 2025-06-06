package com.emergency.EmergencyTracker.repository;

import com.emergency.EmergencyTracker.enums.TipoDispositivoEnum;
import com.emergency.EmergencyTracker.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {

    @Query("SELECT d FROM Dispositivo d WHERE d.usuario.id = :usuarioId AND d.deleted = false")
    List<Dispositivo> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT d FROM Dispositivo d WHERE d.deleted = false AND d.tipoDispositivo = 'RECEPTOR' " +
            "AND ABS(d.latitude - :latitude) <= 100 AND ABS(d.longitude - :longitude) <= 100")
    List<Dispositivo> findReceptoresWithinLatLongRange(@Param("latitude") Double latitude,
                                                       @Param("longitude") Double longitude);
    List<Dispositivo> findByTipoDispositivo(TipoDispositivoEnum tipoDispositivo);
}
