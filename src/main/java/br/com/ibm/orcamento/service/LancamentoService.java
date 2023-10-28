package br.com.ibm.orcamento.service;

import br.com.ibm.orcamento.repository.LancamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    @Autowired
    LancamentoRepository lancamentoRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<LancamentoDto> ObterTodos(){
        try {
            List<ConsultaLancamento> consultaLancamentoList = lancamentoRepository.findLancamentos();

            return consultaLancamentoList.stream()
                    .map(lancamento -> modelMapper.map(lancamento, LancamentoDto.class))
                    .collect(Collectors.toList());
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Não é possível consultar os Lançamentos!");
        }
    }
}