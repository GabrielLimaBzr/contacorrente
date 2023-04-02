package br.com.ctacte.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ctacte.entities.Cadastro;

public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

	@Query("from Cadastro c where (:numero is null or c.numero = :numero)" + " and c.tipocadastro = :tipoCadastro"
			+ " and (:nome is null or lower(c.nome) like lower(concat('%', :nome,'%')))"
			+ " and (:idmunicipio is null or c.idmunicipio = :idmunicipio)")
	Page<Cadastro> consultarCadastro(@Param("nome") String nome, @Param("numero") String numero,
			@Param("tipoCadastro") Long tipoCadastro, @Param("idmunicipio") Long idmunicipio, Pageable pageable);

}
