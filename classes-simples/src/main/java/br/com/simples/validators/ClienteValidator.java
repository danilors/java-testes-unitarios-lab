package br.com.simples.validators;

import java.time.LocalDate;

/**
 * Classe utilitária para validação de campos do cliente.
 */
public class ClienteValidator {

    /**
     * Valida o campo de email do cliente.
     *
     * @param email o email a ser validado
     * @return true se o email for válido, caso contrário false
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Valida o campo de telefone do cliente.
     * O número deve conter exatamente 10 dígitos.
     *
     * @param telefone o telefone a ser validado
     * @return true se o telefone for válido, caso contrário false
     */
    public static boolean isValidTelefone(String telefone) {
        if (telefone == null || telefone.isEmpty()) {
            return false;
        }
        String telefoneRegex = "^[0-9]{10}$";
        return telefone.matches(telefoneRegex);
    }

    /**
     * Valida o campo de data de nascimento do cliente.
     * A diferença entre a data atual e a data de nascimento deve ser maior que 18 anos.
     *
     * @param dataNascimento a data de nascimento a ser validada
     * @return true se a data de nascimento for válida, caso contrário false
     */
    public static boolean isMaiorIdade(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            return false;
        }
        LocalDate currentDate = LocalDate.now();
        return dataNascimento.isAfter(currentDate.minusYears(18));
    }

    /**
     * Valida o campo de nome do cliente.
     * O nome deve ter no mínimo 20 caracteres e no máximo 30 caracteres.
     *
     * @param nome o nome a ser validado
     * @return true se o nome estiver dentro do intervalo válido, caso contrário false
     */
    public static boolean isValidRangeNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        return nome.length() >= 20 && nome.length() <= 30;
    }

    /**
     * Valida o campo de nome do cliente.
     * O nome não deve conter caracteres especiais ou números.
     *
     * @param nome o nome a ser validado
     * @return true se o nome não contiver caracteres especiais ou números, caso contrário false
     */
    public static boolean isValidNomeSemCaracteresEspeciais(String nome) {
        if (nome == null || nome.isEmpty()) {
            return false;
        }
        String nomeRegex = "^[a-zA-Z\\s]+$";
        return nome.matches(nomeRegex);
    }
}