package microservico.seguranca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import microservico.seguranca.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = usuarioRepository.findByUserName(username);
		if (usuario!=null) 
			return usuario;
		else
			throw new UsernameNotFoundException("Usuario: "+usuario+ "não encontrado!");
	}
}
