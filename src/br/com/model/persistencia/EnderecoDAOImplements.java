package br.com.model.persistencia;

import br.com.model.funcionario.Endereco;
import br.com.model.persistencia.dao.EnderecoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EnderecoDAOImplements implements EnderecoDAO{
    private static final String INSERT = "insert into endereco (endereco, cidade, estado) values( ?,?,?);";

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
            pstm = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, e.getEndereco());
            pstm.setString(2, e.getCidade());
            pstm.setString(3, e.getEstado());
            pstm.execute();
            try(ResultSet rs = pstm.getGeneratedKeys()){
                rs.next();
                status = rs.getInt(1);
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao inserir endereço: " + ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão do endereço: "+ ex.getMessage());
            }
        }
        
        
        
        return status;
    }
    
}
