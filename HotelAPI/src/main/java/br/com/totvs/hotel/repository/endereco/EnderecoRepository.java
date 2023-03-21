package br.com.totvs.hotel.repository.endereco;

import br.com.totvs.hotel.model.endereco.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

}
