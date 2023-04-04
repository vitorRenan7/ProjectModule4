package br.com.totvs.hotel.repository;

import br.com.totvs.hotel.model.EstadiaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EstadiaRepository extends JpaRepository<EstadiaModel, Long> {
    @Query(value = "SELECT * FROM TABLE_ESTADIA WHERE TABLE_ESTADIA.INICIO >= :dataInicio AND TABLE_ESTADIA.FIM <= :dataFim", nativeQuery = true)
    List<EstadiaModel> buscarEstadiasPorData(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);

}
