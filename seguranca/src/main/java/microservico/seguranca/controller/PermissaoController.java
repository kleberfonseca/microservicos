package microservico.seguranca.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservico.seguranca.data.vo.UsuarioVO;
import microservico.seguranca.jwt.JwtTokenProvider;
import microservico.seguranca.repository.UsuarioRepository;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/login")
public class PermissaoController {

	private final AuthenticationManager authenticationManager = null;
	private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
	private final UsuarioRepository usuarioRepository = null;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UsuarioRepository usuarioRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.usuarioRepository = usuarioRepository;
	}
	
	@RequestMapping("/testeSecurity")
	public String teste() {
		return "testado";
	}

	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> login(@RequestBody UsuarioVO usuarioVO) {
		try {
			var username = usuarioVO.getUserName();
			var password = usuarioVO.getPassWord();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			var user = usuarioRepository.findByUserName(username);
			var token = "";
			
			if(user != null) {
				token = jwtTokenProvider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("User name not found");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token",token);
			return ok(model);
			
		}catch(AuthenticationException e) {
			throw new BadCredentialsException("Ivalid username/password");
		}
	}
}