package br.com.vendas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vendas.DTO.CategoryDTO;
import br.com.vendas.entities.Category;
import br.com.vendas.repository.CategoryRepository;
import br.com.vendas.service.CategoryService;
import br.com.vendas.service.execpiton.DataBaseException;
import br.com.vendas.service.execpiton.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findaAllPaged(PageRequest pageRequest) {
		Page<Category> list = repository.findAll(pageRequest);
		// convertendo ListCategoryDTO em ListCategory
		return list.map(x -> new CategoryDTO(x));
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found!"));
		return new CategoryDTO(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDTO saveCategory(CategoryDTO dto) {
		Category c = new Category();
		c.setName(dto.getName());
		c = repository.save(c);
		return new CategoryDTO(c);
	}

	@Override
	@Transactional
	public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.findById(id).get();
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	};

	@Override
	public void deleteCategory(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			// excessão para deletar um registro que não existe
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			// excessão para deletar um registro que possui associação de classe
			throw new DataBaseException("Integrity  Violation!");
		}
	}

}
