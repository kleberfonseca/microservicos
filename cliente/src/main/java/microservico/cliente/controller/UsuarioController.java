package microservico.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservico.cliente.data.vo.UsuarioVO;
import microservico.cliente.services.UsuarioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	private final UsuarioService usuarioService;
	private final PagedResourcesAssembler<UsuarioVO> assembler;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, PagedResourcesAssembler<UsuarioVO> assembler) {
		this.usuarioService=usuarioService;
		this.assembler=assembler;
	}
	@GetMapping(value="/{id}", produces= {"application/json","application/xml","application/x-yaml"})
	public UsuarioVO findById(@PathVariable("id") Long id) {
		UsuarioVO usuarioVO = usuarioService.findById(id);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
		return usuarioVO;
	}
	
	@GetMapping(produces= {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="12") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
		
		Page<UsuarioVO> usuarios = usuarioService.findAll(pageable);
		
		usuarios.stream().forEach(p -> p.add(linkTo(methodOn(UsuarioController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<UsuarioVO>> pagedModel=assembler.toModel(usuarios);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping(produces= {"application/json","application/xml","application/x-yaml"},
				 consumes= {"application/json","application/xml","application/x-yaml"})
	public UsuarioVO create(@RequestBody UsuarioVO usuarioVO) {
		UsuarioVO proVO = usuarioService.create(usuarioVO);
		proVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getId())).withSelfRel());
		return proVO;
	}
	
	@PutMapping(produces= {"application/json","application/xml","application/x-yaml"},
			    consumes= {"application/json","application/xml","application/x-yaml"})
	public UsuarioVO update(@RequestBody UsuarioVO usuarioVO) {
		UsuarioVO proVO = usuarioService.update(usuarioVO);
		proVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getId())).withSelfRel());
		return proVO;
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		usuarioService.delete(id);
		return ResponseEntity.ok().build();
	}
}
