package microservico.fornecedor.controller;

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

import microservico.fornecedor.data.vo.AnuncioVO;
import microservico.fornecedor.services.AnuncioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/anuncio")
public class AnuncioController {
	private final AnuncioService anuncioService;
	private final PagedResourcesAssembler<AnuncioVO> assembler;
	
	@Autowired
	public AnuncioController(AnuncioService anuncioService, PagedResourcesAssembler<AnuncioVO> assembler) {
		this.anuncioService=anuncioService;
		this.assembler=assembler;
	}
	@GetMapping(value="/{id}", produces= {"application/json","application/xml","application/x-yaml"})
	public AnuncioVO findById(@PathVariable("id") Long id) {
		AnuncioVO anuncioVO = anuncioService.findById(id);
		anuncioVO.add(linkTo(methodOn(AnuncioController.class).findById(id)).withSelfRel());
		return anuncioVO;
	}
	
	@GetMapping(produces= {"application/json","application/xml","application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="12") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
		
		Page<AnuncioVO> anuncios = anuncioService.findAll(pageable);
		
		anuncios.stream().forEach(p -> p.add(linkTo(methodOn(AnuncioController.class).findById(p.getId())).withSelfRel()));
		
		PagedModel<EntityModel<AnuncioVO>> pagedModel=assembler.toModel(anuncios);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}
	
	@PostMapping(produces= {"application/json","application/xml","application/x-yaml"},
				 consumes= {"application/json","application/xml","application/x-yaml"})
	public AnuncioVO create(@RequestBody AnuncioVO anuncioVO) {
		AnuncioVO proVO = anuncioService.create(anuncioVO);
		proVO.add(linkTo(methodOn(AnuncioController.class).findById(anuncioVO.getId())).withSelfRel());
		return proVO;
	}
	
	@PutMapping(produces= {"application/json","application/xml","application/x-yaml"},
			    consumes= {"application/json","application/xml","application/x-yaml"})
	public AnuncioVO update(@RequestBody AnuncioVO anuncioVO) {
		AnuncioVO proVO = anuncioService.update(anuncioVO);
		proVO.add(linkTo(methodOn(AnuncioController.class).findById(anuncioVO.getId())).withSelfRel());
		return proVO;
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		anuncioService.delete(id);
		return ResponseEntity.ok().build();
	}
}
