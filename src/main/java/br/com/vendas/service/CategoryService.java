package br.com.vendas.service;

import java.util.List;

import br.com.vendas.DTO.CategoryDTO;

public interface CategoryService {
	
	public List<CategoryDTO> findaAll();
	public CategoryDTO findById(Long id);
	public CategoryDTO saveCategory(CategoryDTO c);
	public CategoryDTO updateCategory(Long id,CategoryDTO c);
	public void deleteCategory(Long id);

}
