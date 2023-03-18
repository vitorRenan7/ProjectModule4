package br.com.totvs.hotel.repository.cliente;

import br.com.totvs.hotel.model.cliente.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    @Query(value = "SELECT * FROM TABLE_CLIENTE WHERE TABLE_CLIENTE.NOME LIKE %:nome% OR TABLE_CLIENTE.SOBRENOME LIKE %:nome%", nativeQuery = true)
    List<ClienteModel> buscarClientesPorNomeCompleto(@Param("nome") String nome);

}
