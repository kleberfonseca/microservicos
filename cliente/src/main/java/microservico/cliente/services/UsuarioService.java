package microservico.cliente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import microservico.cliente.data.vo.UsuarioVO;
import microservico.cliente.entity.Usuario;
import microservico.cliente.exception.ResourceNotFoundException;
import microservico.cliente.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public UsuarioVO create(UsuarioVO usuarioVo) {
		UsuarioVO proVORetorno = UsuarioVO.create(usuarioRepository.save(Usuario.create(usuarioVo)));
		return proVORetorno;
	}

	public Page<UsuarioVO> findAll(Pageable pageable){
		var page = usuarioRepository.findAll(pageable);
		return page.map(this::convertToUsuarioVO);
	}
	
	public UsuarioVO convertToUsuarioVO(Usuario usuario) {
		return UsuarioVO.create(usuario);
	}
	
	public UsuarioVO findById(Long id) {
		var entity = usuarioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Registro não encontrado"));
		return UsuarioVO.create(entity);
	}
	
	public UsuarioVO update(UsuarioVO usuarioVO) {
		final Optional<Usuario> optionalUsuario=usuarioRepository.findById(usuarioVO.getId());
		if (!optionalUsuario.isPresent()) {
				new ResourceNotFoundException("Registro não encontrado");
		}
		return UsuarioVO.create(usuarioRepository.save(Usuario.create(usuarioVO)));
	}
	
	public void delete(Long id) {
		var entity = usuarioRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Registro não encontrado"));
		usuarioRepository.delete(entity);
	}
}
