package br.com.simples.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste de Cliente")
class ClienteTest {

    Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = null;
    }

    @DisplayName("Deve criar um cliente com sucesso")
    @Test
    public void deveCriarBeanClienteComSucesso() {
        cliente = new Cliente(
                "123", // id
                "Nome completo com sucesso", // nome
                "johndoe@example.com", // email
                1234567890, // telefone
                LocalDate.of(2000, 1, 1) // dataNascimento
        );

        assertNotNull(cliente);
    }
}