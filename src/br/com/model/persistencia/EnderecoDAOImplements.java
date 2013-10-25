package br.com.model.persistencia;

import br.com.model.funcionario.Endereco;
import br.com.model.persistencia.dao.EnderecoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDAOImplements implements EnderecoDAO{
    
    @Override
    public int salvar(Endereco e) {
        if(e.getCodigo() == 0){
            return inserir(e);
        }
        return -1;
    }

    private int inserir(Endereco e) {
        int status = -1;
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAOImplements.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return status;
    }
    
}
