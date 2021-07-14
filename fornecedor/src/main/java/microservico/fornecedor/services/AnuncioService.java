package microservico.fornecedor.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import microservico.fornecedor.data.vo.AnuncioVO;
import microservico.fornecedor.entity.Anuncio;
import microservico.fornecedor.exception.ResourceNotFoundException;
import microservico.fornecedor.repository.AnuncioRepository;

@Service
public class AnuncioService {
	
	private final AnuncioRepository anuncioRepository;
	
	@Autowired
	public AnuncioService(AnuncioRepository anuncioRepository) {
		this.anuncioRepository = anuncioRepository;
	}
	
	public AnuncioVO create(AnuncioVO anuncioVo) {
		AnuncioVO proVORetorno = AnuncioVO.create(anuncioRepository.save(Anuncio.create(anuncioVo)));
		return proVORetorno;
	}

	public Page<AnuncioVO> findAll(Pageable pageable){
		var page = anuncioRepository.findAll(pageable);
		return page.map(this::convertToUsuarioVO);
	}
	
	public AnuncioVO convertToUsuarioVO(Anuncio anuncio) {
		return AnuncioVO.create(anuncio);
	}
	
	public AnuncioVO findById(Long id) {
		var entity = anuncioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Registro não encontrado"));
		return AnuncioVO.create(entity);
	}
	
	public AnuncioVO update(AnuncioVO anuncioVO) {
		final Optional<Anuncio> optionalUsuario=anuncioRepository.findById(anuncioVO.getId());
		if (!optionalUsuario.isPresent()) {
				new ResourceNotFoundException("Registro não encontrado");
		}
		return AnuncioVO.create(anuncioRepository.save(Anuncio.create(anuncioVO)));
	}
	
	public void delete(Long id) {
		var entity = anuncioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Registro não encontrado"));
		anuncioRepository.delete(entity);
	}
}
