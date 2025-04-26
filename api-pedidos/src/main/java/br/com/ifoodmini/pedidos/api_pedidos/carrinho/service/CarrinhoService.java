package br.com.ifoodmini.pedidos.api_pedidos.carrinho.service;

import br.com.ifoodmini.pedidos.api_pedidos.carrinho.entity.CarrinhoEntity;
import br.com.ifoodmini.pedidos.api_pedidos.carrinho.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Optional<CarrinhoEntity> findById(Long id) {
        return carrinhoRepository.findById(id);
    }

    public CarrinhoEntity save(CarrinhoEntity carrinho) {
        return carrinhoRepository.save(carrinho);
    }

    public void deleteById(Long id) {
        carrinhoRepository.deleteById(id);
    }
}