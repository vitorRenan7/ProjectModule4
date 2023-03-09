package br.com.totvs.hotel.repository.recepcionista;

import br.com.totvs.hotel.model.recepcionista.RecepcionistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecepcionistaRepository extends JpaRepository<RecepcionistaModel, Long> {

}
