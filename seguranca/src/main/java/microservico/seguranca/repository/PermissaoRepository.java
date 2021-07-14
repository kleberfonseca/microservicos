package microservico.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import microservico.seguranca.entity.Permissao;

@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Long>{
	@Query("select p from permissao p where p.descricao =:descricao")
	Permissao findByDescricao(@Param("descricao") String descricao);
}
