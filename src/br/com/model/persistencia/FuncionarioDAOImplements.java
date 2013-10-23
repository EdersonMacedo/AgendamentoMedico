package br.com.model.persistencia;

import br.com.model.funcionario.Funcionario;
import br.com.model.persistencia.dao.FuncionarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class FuncionarioDAOImplements implements FuncionarioDAO{
    private static final String INSERT = "inset into funcionario(nome, login, senha, telefone, celular, endereco, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String LIST = "select * from funcionario;";
    private static final String REMOVE = "delete from funcionario where codigo = ?;";
    private static final String UPDATE = "update funcionario set nome = ?, login = ?, senha = ?, telefone = ?, celular = ?, endereco = ?, cidade = ?, estado = ? where codigo = ?";
    
    
    
    @Override
    public int salvar(Funcionario f) {
        if (f.getCodigo() == 0){
            return insert(f);
        }
        else {
            return update(f);
        }
    }
    
    private int insert(Funcionario f){
        Connection con = null;
        PreparedStatement pstm = null;
//        int retorno = -1;
//        try{
//            con = 
//        }
        
        
        
        return retorno;
    }

    @Override
    public boolean remove(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcionario> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario listById(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcionario> listByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
