package br.com.todotarefas.todotarefas.service;

    import br.com.todotarefas.todotarefas.dto.TarefaDTO;
    import br.com.todotarefas.todotarefas.entity.TarefaEntity;
    import br.com.todotarefas.todotarefas.exception.AtualizaTituloTarefaExistenteException;
    import br.com.todotarefas.todotarefas.exception.TarefaNaoEncontradaException;
    import br.com.todotarefas.todotarefas.repository.TarefaRepository;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class TarefaService {

        private static final Logger logger = LoggerFactory.getLogger(TarefaService.class);

        private final TarefaRepository tarefaRepository;

        public TarefaService(TarefaRepository tarefaRepository) {
            this.tarefaRepository = tarefaRepository;
        }

        public List<TarefaEntity> listarTodas() {
            logger.info("Listando todas as tarefas");
            return tarefaRepository.findAll();
        }

        public TarefaEntity buscarPorId(Long id) {
            logger.info("Buscando tarefa com ID: {}", id);
            return tarefaRepository.findById(id)
                    .orElseThrow(() -> new TarefaNaoEncontradaException(id));
        }

        public TarefaEntity salvar(TarefaDTO tarefaDTO) {
            validarTarefa(tarefaDTO);

            tarefaRepository.findByTitulo(tarefaDTO.titulo()).ifPresent(t -> {
                throw new AtualizaTituloTarefaExistenteException(tarefaDTO.titulo());
            });

            TarefaEntity tarefa = new TarefaEntity();
            tarefa.setTitulo(tarefaDTO.titulo());
            tarefa.setDescricao(tarefaDTO.descricao());
            tarefa.setConcluida(tarefaDTO.concluida());

            logger.info("Salvando nova tarefa: {}", tarefaDTO.titulo());
            return tarefaRepository.save(tarefa);
        }

        public TarefaEntity atualizar(Long id, TarefaDTO tarefaDTO) {
            validarTarefa(tarefaDTO);

            TarefaEntity tarefa = buscarPorId(id);

            tarefaRepository.findByTitulo(tarefaDTO.titulo()).ifPresent(t -> {
                if (!t.getId().equals(id)) {
                    throw new AtualizaTituloTarefaExistenteException(tarefaDTO.titulo());
                }
            });

            tarefa.setTitulo(tarefaDTO.titulo());
            tarefa.setDescricao(tarefaDTO.descricao());
            tarefa.setConcluida(tarefaDTO.concluida());

            logger.info("Atualizando tarefa com ID: {}", id);
            return tarefaRepository.save(tarefa);
        }

        public void deletar(Long id) {
            if (!tarefaRepository.existsById(id)) {
                throw new TarefaNaoEncontradaException(id);
            }

            logger.info("Deletando tarefa com ID: {}", id);
            tarefaRepository.deleteById(id);
        }

        private void validarTarefa(TarefaDTO tarefaDTO) {
            if (tarefaDTO.titulo() == null || tarefaDTO.titulo().isEmpty()) {
                throw new IllegalArgumentException("O título da tarefa não pode ser nulo ou vazio.");
            }
            if (tarefaDTO.descricao() == null || tarefaDTO.descricao().isEmpty()) {
                throw new IllegalArgumentException("A descrição da tarefa não pode ser nula ou vazia.");
            }
            if (tarefaDTO.titulo().length() > 20) {
                throw new IllegalArgumentException("O título da tarefa não pode ter mais de 20 caracteres.");
            }
            if (tarefaDTO.descricao().length() > 60) {
                throw new IllegalArgumentException("A descrição da tarefa não pode ter mais de 60 caracteres.");
            }
        }
    }