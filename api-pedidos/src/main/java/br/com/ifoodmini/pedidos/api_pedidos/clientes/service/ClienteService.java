package br.com.ifoodmini.pedidos.api_pedidos.clientes.service;

import br.com.ifoodmini.pedidos.api_pedidos.clientes.entity.ClienteEntity;
import br.com.ifoodmini.pedidos.api_pedidos.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteEntity save(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}