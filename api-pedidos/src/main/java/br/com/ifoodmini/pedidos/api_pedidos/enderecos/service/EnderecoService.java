package br.com.ifoodmini.pedidos.api_pedidos.enderecos.service;

import br.com.ifoodmini.pedidos.api_pedidos.enderecos.entity.EnderecoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.enderecos.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<EnderecoEntity> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<EnderecoEntity> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public EnderecoEntity save(EnderecoEntity endereco) {
        return enderecoRepository.save(endereco);
    }

    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}