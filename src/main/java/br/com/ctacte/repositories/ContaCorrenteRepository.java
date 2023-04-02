package br.com.ctacte.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ctacte.entities.ContaCorrente;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
	
	@Query("from ContaCorrente c JOIN FETCH c.idtributo JOIN FETCH c.idcadastro where c.idcadastro.idcadastro = :idcadastro and c.cancelada is null and c.baixamanual is null and c.codEstadoConta = 1")
	List<ContaCorrente> buscarContaCadastro(Long idcadastro);
	
	@Query("from ContaCorrente c where c.idcadastro.idcadastro = :idcadastro and c.cancelada is null and c.baixamanual is null and c.codEstadoConta = 1")
	Page<ContaCorrente> buscarContaCadastroPag(Long idcadastro, Pageable pageable);

	@Query("select c from ContaCorrente c JOIN FETCH c.idtributo where c in :contas")
	List<ContaCorrente> findContas(List<ContaCorrente> contas);
}
