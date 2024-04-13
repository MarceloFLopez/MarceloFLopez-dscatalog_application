package br.com.vendas.resorces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vendas.DTO.CategoryDTO;
import br.com.vendas.service.impl.CategoryServiceImp;

@RestController
@RequestMapping("/categories")
public class CategoryResources {

	@Autowired
	private CategoryServiceImp imp;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		return ResponseEntity.ok().body(imp.findaAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
		CategoryDTO dto= imp.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
