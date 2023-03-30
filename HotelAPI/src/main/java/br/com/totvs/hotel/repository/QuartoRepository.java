package br.com.totvs.hotel.repository;

import br.com.totvs.hotel.model.QuartoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface QuartoRepository extends JpaRepository<QuartoModel, Long>, QueryByExampleExecutor<QuartoModel> {

}
