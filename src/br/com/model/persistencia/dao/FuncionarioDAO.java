package br.com.model.persistencia.dao;

import br.com.model.funcionario.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    int salvar(Funcionario f);
    boolean remove(int codigo);
    List<Funcionario> listAll();
    Funcionario listById(int codigo);  
    List<Funcionario> listByNome (String nome);
    
}
