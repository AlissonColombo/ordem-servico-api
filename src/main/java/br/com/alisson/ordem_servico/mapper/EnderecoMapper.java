package br.com.alisson.ordem_servico.mapper;

import br.com.alisson.ordem_servico.dto.EnderecoDTO;
import br.com.alisson.ordem_servico.model.Endereco;
import org.springframework.stereotype.Component;
/*Esta classe pode ser utilizada para centralizar a lógica de conversão entre Endereco e EnderecoDTO,
 evitando duplicação de código nos serviços ClienteService e RepresentanteService.*/


@Component // Para o Spring conseguir injetar essa classe onde for necessário
public class EnderecoMapper {
    
    public Endereco converterEnderecoDTOParaEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) {
            return null;
        }
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setUf(enderecoDTO.getUf());
        endereco.setCep(enderecoDTO.getCep());
        return endereco;
    }
}
