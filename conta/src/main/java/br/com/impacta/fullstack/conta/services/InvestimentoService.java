package br.com.impacta.fullstack.conta.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.impacta.fullstack.conta.domain.Cliente;
import br.com.impacta.fullstack.conta.domain.Conta;
import br.com.impacta.fullstack.conta.domain.Extrato;
import br.com.impacta.fullstack.conta.domain.Investimento;
import br.com.impacta.fullstack.conta.dto.InvestimentoDTO;
import br.com.impacta.fullstack.conta.dto.RegasteDTO;
import br.com.impacta.fullstack.conta.enums.TipoOperacao;
import br.com.impacta.fullstack.conta.exceptions.ObjectNotFoundException;
import br.com.impacta.fullstack.conta.exceptions.SaldoInsuficiente;
import br.com.impacta.fullstack.conta.repository.ContaRepository;
import br.com.impacta.fullstack.conta.repository.ExtratoRepository;
import br.com.impacta.fullstack.conta.repository.InvestimentoRepository;

@Service
public class InvestimentoService {

	@Autowired
	private ContaRepository repoAcc;

	@Autowired
	private ContaService cliService;

	@Autowired
	private ExtratoRepository repoExt;

	@Autowired
	private InvestimentoRepository repoInv;

	public Conta insertAccount(Conta obj) {
		return repoAcc.save(obj);
	}

	public Investimento insert(@Valid InvestimentoDTO objDto) {
		Cliente cli = cliService.find(objDto.getIdCliente());

		Conta cc = cliService.findAccount(objDto.getIdCliente());
		
		objDto.setValorUnitario(objDto.getTipo().getValue());

		cc = validaSaldo(cc, objDto.getQuantidade(), objDto.getValorUnitario());

		Investimento inv = this.fromDto(objDto, cli);

		Investimento invSaved = repoInv.save(inv);

		cc.setInvestimentos(invSaved);

		cliService.insertAccount(cc);

		return invSaved;
	}

	private Conta validaSaldo(Conta cc, Integer quantidade, BigDecimal valorUnitario) {

		BigDecimal valorTotal = valorUnitario.multiply(new BigDecimal(quantidade));
		BigDecimal saldoComLimite = cc.getSaldo().add(cc.getLimite());
		if (valorTotal.compareTo(saldoComLimite) <= -1) {
			Extrato extrato = repoExt.save(new Extrato(TipoOperacao.DEBITO, valorTotal));
			cc.withDraw(valorTotal);
			cc.setExtrato(extrato);
			return repoAcc.save(cc);
		}

		throw new SaldoInsuficiente("Saldo insuficiente");
	}

	private Investimento fromDto(@Valid InvestimentoDTO objDto, Cliente cli) {
		BigDecimal saldoInvestimento = objDto.getValorUnitario().multiply(new BigDecimal(objDto.getQuantidade()));
		return new Investimento(objDto.getTipo(), objDto.getValorUnitario(), objDto.getQuantidade(), saldoInvestimento,
				cli);
	}

	public Investimento findOneInvestiments(Long id) {
		Optional<Investimento> obj = repoInv.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(Investimento.class.getName()));
	}


	public Investimento findById(Long id, List<Investimento> investimentos) {
		return investimentos.stream().filter(c -> c.getId() == id).findFirst().orElseThrow(() -> new ObjectNotFoundException(
				"object not found! Id: " + id + ", Type: " + Investimento.class.getName()));

	}

	public void resgateInvestimento(Conta account, @Valid RegasteDTO objDto) {
		
		Investimento investimento = this.findById(objDto.getIdInvestiments(), account.getInvestimentos());
		account.deposit(investimento.getSaldo());
		investimento.setDataRegaste(Calendar.getInstance());
		investimento.setSaldo(BigDecimal.ZERO);
		
		account.getInvestimentos().remove(investimento);
		account.setInvestimentos(investimento);
		
		repoAcc.save(account);
		
	}
}
