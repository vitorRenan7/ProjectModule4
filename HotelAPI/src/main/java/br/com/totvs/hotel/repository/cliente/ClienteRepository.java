package br.com.totvs.hotel.repository.cliente;

import br.com.totvs.hotel.model.cliente.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
