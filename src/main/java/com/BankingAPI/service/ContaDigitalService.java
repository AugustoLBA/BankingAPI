package com.BankingAPI.service;

import com.BankingAPI.dto.ContaDigitalCreateDTO;
import com.BankingAPI.dto.ContaDigitalResponseDTO;
import com.BankingAPI.exceptions.DepositoInvalidoException;
import com.BankingAPI.exceptions.EntityNotFoundException;
import com.BankingAPI.exceptions.SaqueInvalidoException;
import com.BankingAPI.exceptions.UsernameUniqueViolationException;
import com.BankingAPI.models.ContaDigital;
import com.BankingAPI.models.Operacao;
import com.BankingAPI.repositories.ContaDigitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // Injeção de depêndencia via lombok
@Service
public class ContaDigitalService {

    private final ContaDigitalRepository contaDigitalRepository;

    private final AgenciaService agenciaService;

    private final ClienteService clienteService;

    private final OperacaoService operacaoService;


    @Transactional
    public ContaDigital salvar(ContaDigital contaDigital){
        try {
            return contaDigitalRepository.save(contaDigital);
        }catch (DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException(String.format("Conta com NUMERO: {%s} já cadastrado.", contaDigital.getNumero()));
        }
    }

    @Transactional(readOnly = true)
    public ContaDigital buscarPorId(Long id){
        return contaDigitalRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Id {%s} não encontrado !", id)));
    }

    @Transactional(readOnly = true)
    public List<ContaDigital> buscarTodos(){
        return contaDigitalRepository.findAll();
    }
    @Transactional
    public void deletarPorId(Long id){
        ContaDigital contaDigital = buscarPorId(id);
        contaDigitalRepository.delete(contaDigital);
    }

    public ContaDigital deposito(BigDecimal valor, Long id){
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new DepositoInvalidoException("O valor do deposito não pode ser menor ou igual a zero !");
        }
        ContaDigital contaDigital = buscarPorId(id);
        contaDigital.setSaldo(contaDigital.getSaldo().add(valor));

        Operacao operacao = new Operacao();
        operacao.setTipo(Operacao.TipoOperacao.DEPOSITO);
        operacao.setValor(valor);
        operacao.setContaDigital(contaDigital);
        operacaoService.salvar(operacao);

        return contaDigital;

    }

    public ContaDigital sacar(BigDecimal valorSaque, Long id){
        if(valorSaque.compareTo(BigDecimal.ZERO) <= 0){
            throw new SaqueInvalidoException("O valor do saque não pode ser menor ou igual a zero !");
        }
        ContaDigital contaDigital = buscarPorId(id);
        if(valorSaque.compareTo(contaDigital.getSaldo()) > 0){
            throw new SaqueInvalidoException("O valor do saque é maior que o SALDO da conta !");
        }
        contaDigital.setSaldo(contaDigital.getSaldo().subtract(valorSaque));

        Operacao operacao = new Operacao();
        operacao.setContaDigital(contaDigital);
        operacao.setTipo(Operacao.TipoOperacao.SAQUE);
        operacao.setValor(valorSaque);

        operacaoService.salvar(operacao);
        return contaDigital;

    }

    public ContaDigital transferencia(Long idContaOrigem, Long idContaDestino, BigDecimal valorTransferencia){
        if(valorTransferencia.compareTo(BigDecimal.ZERO) <= 0){
            throw new SaqueInvalidoException("O valor da transferencia não pode ser menor ou igual a zero !");
        }
        ContaDigital contaOrigem = buscarPorId(idContaOrigem);
        if(valorTransferencia.compareTo(contaOrigem.getSaldo()) > 0){
            throw new SaqueInvalidoException("O valor do saque é maior que o SALDO da conta !");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valorTransferencia));

        ContaDigital contaDestino = buscarPorId(idContaDestino);
        contaDestino.setSaldo(contaDestino.getSaldo().add(valorTransferencia));

        Operacao operacao = new Operacao();
        operacao.setTipo(Operacao.TipoOperacao.TRANSFERENCIA);
        operacao.setValor(valorTransferencia);
        operacao.setContaOrigem(contaOrigem);
        operacao.setContaDestino(contaDestino);
        operacaoService.salvar(operacao);

        return contaOrigem;
    }
    public ContaDigital toContaDigital(ContaDigitalCreateDTO createDTO){
        ContaDigital contaDigital = new ContaDigital();
        BeanUtils.copyProperties(createDTO,contaDigital);
        contaDigital.setAgencia(agenciaService.buscarPorId(createDTO.getAgenciaId()));
        contaDigital.setCliente(clienteService.buscarPorId(createDTO.getClienteId()));
        return contaDigital;
    }

    public ContaDigitalResponseDTO toDto(ContaDigital contaDigital){
        ContaDigitalResponseDTO responseDTO = new ContaDigitalResponseDTO();
        BeanUtils.copyProperties(contaDigital,responseDTO);
        responseDTO.setAgenciaId(contaDigital.getAgencia().getId());
        responseDTO.setClienteId(contaDigital.getCliente().getId());
        return responseDTO;
    }

    public List<ContaDigitalResponseDTO> toListDto(List<ContaDigital> contaDigitals){
        return contaDigitals.stream().map(responseDto -> toDto(responseDto)).collect(Collectors.toList());
    }
}
