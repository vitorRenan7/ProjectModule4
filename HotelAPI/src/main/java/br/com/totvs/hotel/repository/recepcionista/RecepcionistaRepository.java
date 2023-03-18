package br.com.totvs.hotel.repository.recepcionista;

import br.com.totvs.hotel.model.recepcionista.RecepcionistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecepcionistaRepository extends JpaRepository<RecepcionistaModel, Long> {
    @Query(value = "SELECT * FROM TABLE_RECEPCIONISTA WHERE TABLE_RECEPCIONISTA.NOME LIKE %:nome% OR TABLE_RECEPCIONISTA.SOBRENOME LIKE %:nome%", nativeQuery = true)
    List<RecepcionistaModel> buscarRecepcionistasPorNomeCompleto(@Param("nome") String nome);

}
