package com.trabalho.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.model.Cliente;
import com.trabalho.repositorio.ClienteRepositorio;

@RestController
@RequestMapping("/cliente")
public class clienteController {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@GetMapping
	public List<Cliente> listar(){
		return clienteRepositorio.findAll();

	}
	
	@PostMapping
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	@PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        Optional<Cliente> optionalCliente = clienteRepositorio.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente clienteExistente = optionalCliente.get();
            clienteExistente.setNome(clienteAtualizado.getNome());
            // Atualize outros campos conforme necessário
            return clienteRepositorio.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente não encontrado com o id " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Optional<Cliente> optionalCliente = clienteRepositorio.findById(id);
        if (optionalCliente.isPresent()) {
            clienteRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Cliente não encontrado com o id " + id);
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}