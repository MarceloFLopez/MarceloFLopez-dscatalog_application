package br.com.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vendas.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
