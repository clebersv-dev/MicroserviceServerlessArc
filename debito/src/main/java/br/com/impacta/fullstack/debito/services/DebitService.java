package br.com.impacta.fullstack.debito.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.debito.domain.Debit;
import br.com.impacta.fullstack.debito.dto.DebitDTO;
import br.com.impacta.fullstack.debito.exceptions.DataIntegrityException;
import br.com.impacta.fullstack.debito.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.debito.repository.DebitRepository;


@Service
public class DebitService {

	@Autowired
	private DebitRepository repo;

	public Debit find(Integer id) {
		Optional<Debit> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Debit.class.getName()));
	}
	
	public Debit insert(Debit obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Debit update(Debit obj) {
		Debit newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cannot delete a debit that has products");
		}
	}
	
	public List<Debit> findAll() {
		return repo.findAll();
	}
	
	public Page<Debit> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Debit fromDTO(DebitDTO objDto) {
		return new Debit(objDto.getId(), objDto.getDescription());
	}
	
	private void updateData(Debit newObj, Debit obj) {
		newObj.setDescription(obj.getDescription());
	}
}
