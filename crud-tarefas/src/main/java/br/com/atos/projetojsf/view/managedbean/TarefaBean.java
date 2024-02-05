package br.com.atos.projetojsf.view.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.atos.projetojsf.core.entity.Tarefa;
import br.com.atos.projetojsf.core.service.TarefaService;

import java.util.List;

@ManagedBean
@SessionScoped
public class TarefaBean {
    @ManagedProperty(value="#{tarefaService}")
    private TarefaService tarefaService;
    private Tarefa novaTarefa = new Tarefa();
    private List<Tarefa> tarefas;

    public void adicionarTarefa() {
        tarefaService.cadastrarTarefa(novaTarefa);
        novaTarefa = new Tarefa(); 
        tarefas = null;
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefaService.excluirTarefa(tarefa.getId());
        tarefas = null;
    }

    public List<Tarefa> getTarefas() {
        if (tarefas == null) {
            tarefas = tarefaService.listarTodasTarefas();
        }
        return tarefas;
    }

}
