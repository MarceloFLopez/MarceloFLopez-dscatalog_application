package br.com.vendas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vendas.DTO.CategoryDTO;
import br.com.vendas.entities.Category;
import br.com.vendas.repository.CategoryRepository;
import br.com.vendas.service.CategoryService;
import br.com.vendas.service.execpiton.EntityNotFoundException;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDTO> findaAll() {
		List<Category> list = repository.findAll();
		// convertendo ListCategoryDTO em ListCategory
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(()-> new EntityNotFoundException("Entity Not Found!"));
		return new CategoryDTO(entity);
	}

	@Override
	public void saveCategory(CategoryDTO dto) {
		Category c = new Category();
		c.setId(dto.getId());
		c.setName(dto.getName());
		if (c != null) {
			repository.save(c);
		}
	}

	@Override
	public void deleteCategory(Long id) {
	}

}
