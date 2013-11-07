package br.com.model.persistencia.dao;

import br.com.model.paciente.Convenio;
import java.util.List;

public interface ConvenioDAO {
    
    int salvar(Convenio c);
    boolean remove(int codigo);
    List<Convenio> listAll();
    Convenio listById(int codigo);
    List<Convenio> listByNome (String nome);
    
}
