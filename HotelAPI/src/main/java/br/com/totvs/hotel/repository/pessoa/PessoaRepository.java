package br.com.totvs.hotel.repository.pessoa;

import br.com.totvs.hotel.model.pessoa.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

}
