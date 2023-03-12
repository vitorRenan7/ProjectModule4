package br.com.totvs.hotel.repository.estadia;

import br.com.totvs.hotel.model.estadia.EstadiaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadiaRepository extends JpaRepository<EstadiaModel, Long> {

}
