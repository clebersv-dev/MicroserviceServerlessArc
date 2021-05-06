package br.com.impacta.fullstack.conta.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.conta.domain.Cartao;
import br.com.impacta.fullstack.conta.domain.Cliente;
import br.com.impacta.fullstack.conta.domain.Fatura;
import br.com.impacta.fullstack.conta.dto.CartaoDTO;
import br.com.impacta.fullstack.conta.exceptions.DataIntegrityException;
import br.com.impacta.fullstack.conta.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.conta.repository.CartaoRepository;
import br.com.impacta.fullstack.conta.repository.ClienteRepository;
import br.com.impacta.fullstack.conta.repository.FaturaRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository repository;
	
	@Autowired
	private ClienteRepository repositoryCliente;
	
	@Autowired
	private FaturaRepository repositoryFatura;

	public Cartao find(Long id) {
		Optional<Cartao> obj = repository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("object not found! Id: " + id + ", Type: " + Cartao.class.getName()));
	}

	public Cartao insert(Cartao obj) {
		return repository.save(obj);
	}
	
	public Fatura insert(Fatura obj) {
		return repositoryFatura.save(obj);
	}

	public Cartao update(Cartao obj) {
		Cartao newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cannot delete a Cartao that has products");
		}
	}

	public List<Cartao> findAll() {
		return repository.findAll();
	}

	public Page<Cartao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cartao fromDTO(CartaoDTO objDto) throws ObjectNotFoundException {
		Optional<Cliente> obj = repositoryCliente.findById(objDto.getIdCliente());
		Cliente cliente = obj.orElseThrow(
				() -> new ObjectNotFoundException("object not found! Id: " + objDto.getIdCliente() + ", Type: " + Cliente.class.getName()));
		
		return new Cartao(objDto.getTitular(), objDto.getNumero(), objDto.getBandeira(),
				objDto.getLimite(), objDto.getTipo(), cliente);
	}

	private void updateData(Cartao newObj, Cartao obj) {
		newObj.setLimite(obj.getLimite());
	}
}
