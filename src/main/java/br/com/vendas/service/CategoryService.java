package br.com.vendas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.vendas.DTO.CategoryDTO;

public interface CategoryService {
	
	public Page<CategoryDTO> findaAllPaged(PageRequest pageRequest);
	public CategoryDTO findById(Long id);
	public CategoryDTO saveCategory(CategoryDTO c);
	public CategoryDTO updateCategory(Long id,CategoryDTO c);
	public void deleteCategory(Long id);

}
