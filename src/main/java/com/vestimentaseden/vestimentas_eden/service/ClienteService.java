package com.vestimentaseden.vestimentas_eden.service;

import com.vestimentaseden.vestimentas_eden.model.cliente.mapper.ClienteMapper;
import com.vestimentaseden.vestimentas_eden.model.cliente.vo.ClienteVO;
import com.vestimentaseden.vestimentas_eden.persistence.entity.ClienteEntity;
import com.vestimentaseden.vestimentas_eden.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteVO buscarCliente(String clienteId) {

        ClienteEntity clienteEntity = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException(
                        "Cliente não encontrado: " + clienteId
                ));

        return ClienteMapper.INSTANCE.toClienteVO(clienteEntity);
    }

}
