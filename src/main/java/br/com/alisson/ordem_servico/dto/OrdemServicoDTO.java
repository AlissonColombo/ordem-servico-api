package br.com.alisson.ordem_servico.dto;

import java.util.List;

public class OrdemServicoDTO {
    
    private Long representanteId;
    private Long clienteId;
    private Long ordemId;
    private String status;
    private List<ItemOSDTO> itens;

    public List<ItemOSDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemOSDTO> itens) {
        this.itens = itens;
    }

    public Long getRepresentanteId() {
        return representanteId;
    }

    public Long getClienteId() {
        return clienteId;
    }
    public Long getOrdemId() {
        return ordemId;
    }


    public String getStatus() {
        return status;
    }

    public void setRepresentanteId(Long representanteId) {
        this.representanteId = representanteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    
    public void setOrdemId(Long ordemId) {
        this.ordemId = ordemId;
    }


}
