package br.com.simples.validators;

import java.time.LocalDate;

/**
 * Classe utilitária para validação de campos do cliente.
 */
public class ClienteValidator {

    // Constantes para expressões regulares
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String TELEFONE_REGEX = "^[0-9]{10}$";
    private static final String NOME_REGEX = "^[a-zA-Z\\s]+$";

    private static final int TAMANHO_MINIMO_NOME = 20;
    private static final int TAMANHO_MAXIMO_NOME = 30;

    /**
     * Valida o campo de email do cliente.
     *
     * @param email o email a ser validado
     * @return true se o email for válido, caso contrário false
     */
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        return email.matches(EMAIL_REGEX);
    }

    /**
     * Valida o campo de telefone do cliente.
     * O número deve conter exatamente 10 dígitos.
     *
     * @param telefone o telefone a ser validado
     * @return true se o telefone for válido, caso contrário false
     */
    public static boolean isValidTelefone(String telefone) {
        if (isNullOrEmpty(telefone)) {
            return false;
        }
        return telefone.matches(TELEFONE_REGEX);
    }

    /**
     * Valida o campo de data de nascimento do cliente.
     * A diferença entre a data atual e a data de nascimento deve ser maior ou igual
     * a 18 anos.
     *
     * @param dataNascimento a data de nascimento a ser validada
     * @return true se a data de nascimento for válida, caso contrário false
     */
    public static boolean isMaiorIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        return !dataNascimento.isAfter(currentDate.minusYears(18));
    }

    /**
     * Valida o campo de nome do cliente.
     * O nome deve ter no mínimo 20 caracteres e no máximo 30 caracteres.
     *
     * @param nome o nome a ser validado
     * @return true se o nome estiver dentro do intervalo válido, caso contrário
     *         false
     */
    public static boolean isValidRangeNome(String nome) {
        if (isNullOrEmpty(nome)) {
            return false;
        }
        return isBetWeenRange(nome.length(), TAMANHO_MINIMO_NOME, TAMANHO_MAXIMO_NOME);

    }

    /**
     * Valida o campo de nome do cliente.
     * O nome não deve conter caracteres especiais ou números.
     *
     * @param nome o nome a ser validado
     * @return true se o nome não contiver caracteres especiais ou números, caso
     *         contrário false
     */
    public static boolean isValidNomeSemCaracteresEspeciais(String nome) {
        if (isNullOrEmpty(nome)) {
            return false;
        }
        return nome.matches(NOME_REGEX);
    }

    /**
     * Verifica se uma string é nula ou vazia.
     *
     * @param value a string a ser verificada
     * @return true se a string for nula ou vazia, caso contrário false
     */
    private static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Verifica se um valor inteiro está dentro de um intervalo específico.
     *
     * @param value o valor a ser verificado
     * @param min   o valor mínimo do intervalo
     * @param max   o valor máximo do intervalo
     * @return true se o valor estiver dentro do intervalo, caso contrário false
     */
    private static boolean isBetWeenRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}