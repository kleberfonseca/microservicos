package microservico.seguranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import microservico.seguranca.entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	@Query("select u from usuario p where p.username =:username")
	Usuario findByUserName(@Param("username") String username);
}
