package br.com.simples.beans;

import br.com.simples.exceptions.ClienteException;
import br.com.simples.validators.ClienteValidator;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Cliente {

    private final String id;
    private final String nome;
    private final String email;
    private final int telefone;
    private final LocalDate dataNascimento;

    public Cliente(String id, String nome, String email, int telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.validate();
    }

    private void validate() {
        if (this.id == null || this.id.isEmpty()) {
            throw new ClienteException("ID não pode ser nulo ou vazio");
        }
        if (this.nome == null || this.nome.isEmpty()) {
            throw new ClienteException("Nome não pode ser nulo ou vazio");
        }
        if (this.email == null || this.email.isEmpty()) {
            throw new ClienteException("Email não pode ser nulo ou vazio");
        }
        if (this.telefone <= 0) {
            throw new ClienteException("Telefone deve ser um número positivo");
        }
        if (this.dataNascimento == null) {
            throw new ClienteException("Data de nascimento não pode ser nula");
        }
        if (this.dataNascimento.isAfter(LocalDate.now())) {
            throw new ClienteException("Data de nascimento não pode ser futura");
        }
        if (!ClienteValidator.isMaiorIdade(this.dataNascimento)){
            throw new ClienteException("Data de nascimento deve ser maior que 18 anos");
        }
        if (!ClienteValidator.isValidEmail(this.email)) {
            throw new ClienteException("Email inválido");
        }
        if (!ClienteValidator.isValidTelefone(String.valueOf(this.telefone))) {
            throw new ClienteException("Telefone inválido");
        }
        if (!ClienteValidator.isValidRangeNome(this.nome)) {
            throw new ClienteException("Nome deve ter entre 20 e 30 caracteres");
        }
        if (!ClienteValidator.isValidNomeSemCaracteresEspeciais(this.nome)) {
            throw new ClienteException("Nome deve conter apenas letras e espaços");
        }
    }
}
