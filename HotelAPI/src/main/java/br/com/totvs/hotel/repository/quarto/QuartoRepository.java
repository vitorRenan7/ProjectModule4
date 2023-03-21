package br.com.totvs.hotel.repository.quarto;

import br.com.totvs.hotel.model.quarto.QuartoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<QuartoModel, Long> {

}
