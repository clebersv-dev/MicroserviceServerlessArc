package br.com.impacta.fullstack.credito.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.credito.domain.Credit;
import br.com.impacta.fullstack.credito.dto.CreditDTO;
import br.com.impacta.fullstack.credito.exceptions.DataIntegrityException;
import br.com.impacta.fullstack.credito.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.credito.repository.CreditRepository;


@Service
public class CreditService {

	@Autowired
	private CreditRepository repo;

	public Credit find(Long id) {
		Optional<Credit> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Credit.class.getName()));
	}
	
	public Credit insert(Credit obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Credit update(Credit obj) {
		Credit newObj = find(Long.valueOf(obj.getId()));
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cannot delete a credit that has products");
		}
	}
	
	public List<Credit> findAll() {
		return repo.findAll();
	}
	
	public Page<Credit> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Credit fromDTO(CreditDTO objDto) {
		return new Credit(objDto.getId(), objDto.getDescription());
	}
	
	private void updateData(Credit newObj, Credit obj) {
		newObj.setDescription(obj.getDescription());
	}
}
