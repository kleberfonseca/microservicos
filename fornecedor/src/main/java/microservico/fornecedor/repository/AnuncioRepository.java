package microservico.fornecedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservico.fornecedor.entity.Anuncio;

@Repository
public interface AnuncioRepository  extends JpaRepository<Anuncio, Long>{

}
