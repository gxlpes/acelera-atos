package br.com.atos.projetojsf.core.service;

import java.util.List;

import br.com.atos.projetojsf.core.dao.TarefaDAO;
import br.com.atos.projetojsf.core.entity.Tarefa;

public class TarefaService {

    private TarefaDAO tarefaDAO;

    public TarefaService() {
        this.tarefaDAO = new TarefaDAO();
    }

    public void cadastrarTarefa(Tarefa tarefa) {
        tarefaDAO.cadastrarTarefa(tarefa);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaDAO.listarTodasTarefas();
    }

    public void excluirTarefa(Long i) {
        tarefaDAO.excluirTarefa(i);
    }

    public void alterarTarefa(Tarefa tarefa) {
        tarefaDAO.alterarTarefa(tarefa);
    }
}
