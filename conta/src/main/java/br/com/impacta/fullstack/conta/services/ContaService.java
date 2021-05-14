package br.com.impacta.fullstack.conta.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.conta.domain.Cliente;
import br.com.impacta.fullstack.conta.domain.Conta;
import br.com.impacta.fullstack.conta.dto.ContaDTO;
import br.com.impacta.fullstack.conta.enums.TipoConta;
import br.com.impacta.fullstack.conta.enums.TipoSegmento;
import br.com.impacta.fullstack.conta.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.conta.repository.ClienteRepository;
import br.com.impacta.fullstack.conta.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ClienteRepository repoCli;

	@Autowired
	private ContaRepository repoAcc;

	public Cliente find(Long id) {
		Optional<Cliente> obj = repoCli.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Cliente.class.getName()));
	}

	public Conta findAccount(Long id) {
		Optional<Conta> obj = repoAcc.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("object not found! Id: " + id + ", Type: " + Conta.class.getName()));
	}

	public Cliente insertClient(Cliente obj) {
		return repoCli.save(obj);
	}

	public Conta insertAccount(Conta obj) {
		return repoAcc.save(obj);
	}

	public Conta createCliAccount(@Valid ContaDTO objDto) {

		Cliente cli = this.fromDTOCli(objDto);
		cli = this.insertClient(cli);
		Conta account = new Conta();

		if (objDto.getTipoConta() == TipoConta.CORRENTE) {
			account = this.toDTOaccountCc(objDto, cli);
		} else {
			account = this.toDTOaccountPp(objDto, cli);
		}
		
		account.setCliente(cli);
		return this.insertAccount(account);
	}

	private Conta toDTOaccountCc(@Valid ContaDTO objDto, Cliente cliente) {
		return new Conta(objDto.getTitular(), objDto.getBanco(), objDto.getAgencia(), objDto.getNumero(),
				objDto.getSaldo(), objDto.getLimite(), cliente, TipoSegmento.COMUM);
	}

	private Conta toDTOaccountPp(@Valid ContaDTO objDto, Cliente cliente) {
		return new Conta(objDto.getTitular(), objDto.getBanco(), objDto.getAgencia(), objDto.getNumero(),
				objDto.getSaldo(), objDto.getLimite(), cliente, TipoSegmento.POUPANCA);
	}

	public Cliente fromDTOCli(ContaDTO objDto) {
		return new Cliente(objDto.getCliente().getNome(), objDto.getCliente().getCpfCnpj(),
				objDto.getCliente().getEndereco(), objDto.getCliente().getTipo());
	}

	public Cliente fromDTOCliID(ContaDTO objDto) {
		return new Cliente(objDto.getCliente().getId(), objDto.getCliente().getNome(), objDto.getCliente().getCpfCnpj(),
				objDto.getCliente().getEndereco(), objDto.getCliente().getTipo());
	}

//	private Conta toDTOaccount(ContaDTO objDto) {
//		return new Conta(objDto.getTitular(), objDto.getBanco(), objDto.getAgencia(), objDto.getNumero(), objDto.getSaldo(),
//				objDto.getLimite());
//	}

	private Conta toDTOaccountId(ContaDTO objDto) {
		return new Conta(objDto.getId(), objDto.getTitular(), objDto.getBanco(), objDto.getAgencia(),
				objDto.getNumero(), objDto.getSaldo(), objDto.getLimite());
	}

	public List<Cliente> findAllClients() {
		Optional<List<Cliente>> obj = Optional.of(repoCli.findAll());
		return obj.orElseThrow(() -> new ObjectNotFoundException(Cliente.class.getName()));
	}

	public List<Conta> findAllAccounts() {
		Optional<List<Conta>> obj = Optional.of(repoAcc.findAll());
		return obj.orElseThrow(() -> new ObjectNotFoundException(Conta.class.getName()));
	}

	public Conta updateCliAccount(@Valid ContaDTO objDto) {
		Conta newObj = this.findAccount(Long.valueOf(objDto.getId()));
		this.updateAccout(newObj, objDto);
		return repoAcc.save(newObj);
	}

	private Conta updateAccout(Conta newObj, @Valid ContaDTO objDto) {
		Conta account = this.preparaConta(objDto);

		if (account.equals(newObj)) {
			return account;
		} else {
			this.updateConta(newObj, objDto);
		}
		return newObj;
	}

	private Conta preparaConta(@Valid ContaDTO objDto) {
		Conta account = new Conta();
		Cliente cli = this.fromDTOCliID(objDto);
		account = this.toDTOaccountId(objDto);
		account.setCliente(cli);
		return account;
	}

	private void updateConta(Conta newObj, @Valid ContaDTO objDto) {
		newObj.setAgencia(objDto.getAgencia());
		newObj.setBanco(newObj.getBanco());
		newObj.setNumero(objDto.getNumero());
		newObj.setLimite(newObj.getLimite());
		newObj.getCliente().setTipo(objDto.getCliente().getTipo());
		newObj.getCliente().setNome(objDto.getCliente().getNome());
		newObj.getCliente().setCpfCnpj(objDto.getCliente().getCpfCnpj());
	}
}
