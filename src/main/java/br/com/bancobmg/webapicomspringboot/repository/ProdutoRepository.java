package br.com.bancobmg.webapicomspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancobmg.webapicomspringboot.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
