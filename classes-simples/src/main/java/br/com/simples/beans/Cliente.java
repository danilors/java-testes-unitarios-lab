package br.com.simples.beans;

import br.com.simples.exceptions.ClienteException;
import br.com.simples.validators.ClienteValidator;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Representa um cliente com informações básicas e validações.
 * A classe garante que os dados fornecidos sejam válidos no momento da criação.
 */
@Getter
public class Cliente {

    // Mensagens de erro para validações
    private static final String ID_INVALIDO = "ID não pode ser nulo ou vazio";
    private static final String NOME_INVALIDO = "Nome não pode ser nulo ou vazio";
    private static final String EMAIL_INVALIDO = "Email não pode ser nulo ou vazio";
    private static final String TELEFONE_INVALIDO = "Telefone deve ser um número positivo";
    private static final String DATA_NASCIMENTO_INVALIDA = "Data de nascimento não pode ser nula";
    private static final String DATA_NASCIMENTO_FUTURA = "Data de nascimento não pode ser futura";
    private static final String MENOR_DE_IDADE = "Data de nascimento deve ser maior que 18 anos";
    private static final String EMAIL_FORMATO_INVALIDO = "Email inválido";
    private static final String TELEFONE_FORMATO_INVALIDO = "Telefone inválido";
    private static final String NOME_TAMANHO_INVALIDO = "Nome deve ter entre 20 e 30 caracteres";
    private static final String NOME_CARACTERES_INVALIDOS = "Nome deve conter apenas letras e espaços";

    private final String id;
    private final String nome;
    private final String email;
    private final int telefone;
    private final LocalDate dataNascimento;

    /**
     * Construtor da classe Cliente.
     *
     * @param id             Identificador único do cliente.
     * @param nome           Nome do cliente.
     * @param email          Email do cliente.
     * @param telefone       Telefone do cliente (deve ser um número positivo).
     * @param dataNascimento Data de nascimento do cliente.
     * @throws ClienteException Se qualquer validação falhar.
     */
    public Cliente(String id, String nome, String email, int telefone, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.validate();
    }

    /**
     * Valida os atributos do cliente.
     * Lança exceções se qualquer atributo for inválido.
     *
     * @throws ClienteException Se qualquer validação falhar.
     */
    private void validate() {
        validateString(this.id, ID_INVALIDO);
        validateString(this.nome, NOME_INVALIDO);
        validateString(this.email, EMAIL_INVALIDO);
        validatePositiveNumber(this.telefone, TELEFONE_INVALIDO);
        validateNotNull(this.dataNascimento, DATA_NASCIMENTO_INVALIDA);

        var errorMessage = "";

        if (this.dataNascimento.isAfter(LocalDate.now())) {
            errorMessage = DATA_NASCIMENTO_FUTURA;
        }
        if (!ClienteValidator.isMaiorIdade(this.dataNascimento)) {
            errorMessage = MENOR_DE_IDADE;
        }
        if (!ClienteValidator.isValidEmail(this.email)) {
            errorMessage = EMAIL_FORMATO_INVALIDO;
        }
        if (!ClienteValidator.isValidTelefone(String.valueOf(this.telefone))) {
            errorMessage = TELEFONE_FORMATO_INVALIDO;
        }
        if (!ClienteValidator.isValidRangeNome(this.nome)) {
            errorMessage = NOME_TAMANHO_INVALIDO;
        }
        if (!ClienteValidator.isValidNomeSemCaracteresEspeciais(this.nome)) {
            errorMessage = NOME_CARACTERES_INVALIDOS;
        }
        if (errorMessage.isEmpty()) {
            return;
        }
        throw new ClienteException(errorMessage);
    }

    /**
     * Valida se uma string não é nula ou vazia.
     *
     * @param value        String a ser validada.
     * @param errorMessage Mensagem de erro a ser lançada em caso de falha.
     * @throws ClienteException Se a string for nula ou vazia.
     */
    private void validateString(String value, String errorMessage) {
        if (isNullOrEmptyValue(value)) {
            throw new ClienteException(errorMessage);
        }
    }

    /**
     * Valida se um número inteiro é positivo.
     *
     * @param value        Número a ser validado.
     * @param errorMessage Mensagem de erro a ser lançada em caso de falha.
     * @throws ClienteException Se o número for menor ou igual a zero.
     */
    private void validatePositiveNumber(int value, String errorMessage) {
        if (value <= 0) {
            throw new ClienteException(errorMessage);
        }
    }

    /**
     * Valida se um objeto não é nulo.
     *
     * @param value        Objeto a ser validado.
     * @param errorMessage Mensagem de erro a ser lançada em caso de falha.
     * @throws ClienteException Se o objeto for nulo.
     */
    private void validateNotNull(Object value, String errorMessage) {
        if (value == null) {
            throw new ClienteException(errorMessage);
        }
    }

    /**
     * Verifica se uma string é nula ou vazia.
     *
     * @param value String a ser verificada.
     * @return {@code true} se a string for nula ou vazia, caso contrário
     *         {@code false}.
     */
    private boolean isNullOrEmptyValue(String value) {
        return value == null || value.isEmpty();
    }
}
