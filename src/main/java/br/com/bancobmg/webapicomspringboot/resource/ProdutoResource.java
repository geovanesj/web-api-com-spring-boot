package br.com.bancobmg.webapicomspringboot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancobmg.webapicomspringboot.model.Produto;
import br.com.bancobmg.webapicomspringboot.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<Produto> produtos = produtoRepository.findAll();
		ResponseEntity<List<Produto>> response = new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Produto produto = produtoRepository.findOne(id);
		
		if (produto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ResponseEntity<Produto> response = new ResponseEntity<Produto>(produto, HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			produtoRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Produto produto) {
		if (produto.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		produto = produtoRepository.save(produto);
		
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Produto produto) {
		if (produto.getId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (produtoRepository.findOne(produto.getId()) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		produtoRepository.save(produto);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
