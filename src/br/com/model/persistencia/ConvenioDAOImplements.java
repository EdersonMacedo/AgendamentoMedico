/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.persistencia;

import br.com.model.paciente.Convenio;
import br.com.model.persistencia.dao.ConvenioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author guest01
 */
public class ConvenioDAOImplements implements ConvenioDAO {

    
    private static final String INSERT = "insert into convenio(nome) values (?);";
    private static final String LIST = "select * from convenio;";
    private static final String REMOVE = "delete from convenio where codigo = ?;";
    private static final String UPDATE = "update convenio set nome = ? where codigo = ?;";
    private static final String LISTBYID = "select * from convenio where codigo = ?;";
    private static final String LISTBYNOME = "select * from convenio where nome like ?;";
    
    @Override
    public int salvar(Convenio f) {
        if (f.getCodigo() == 0){
            return insert(f);
        }
        else {
            return update(f);
        }
    }
    
    private int insert(Convenio f){
        Connection con = null;
        PreparedStatement pstm = null;
        int retorno = -1;
        try{
            
            //con = guarda a conexão aberta no connectionFactory
            con = ConnectionFactory.getConnection();
            //pstm = manda um sql para o banco
            pstm = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            //(nome, login, senha, telefone, celular, cargo)
            pstm.setString(1, f.getNome());
            pstm.execute();
            try(ResultSet rs = pstm.getGeneratedKeys()){
                if(rs.next()){
                    retorno = rs.getInt(1);
                }
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao inserir: "+e.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em inserir: " + ex.getMessage());
            }
        }
        return retorno;
    }
    
    private int update(Convenio f) {
        Connection con = null;
        PreparedStatement pstm = null;
        int retorno = -1;
        try{
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(UPDATE);
           
            pstm.setString(1, f.getNome());
            pstm.setInt(2, f.getCodigo());
            
            pstm.execute();
            retorno = f.getCodigo();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Editar dados do convenio: "+e.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em inserir: " + ex.getMessage());
            }
        }
        return retorno;
    }

    @Override
    public boolean remove(int codigo) {
        boolean status = false;
        Connection con = null;
        PreparedStatement pstm = null;
        try{
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(REMOVE);
            pstm.setInt(1, codigo);
            pstm.execute();
            status = true;
            
        }catch(Exception e){
            String var = "com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Cannot delete or update a parent row: a foreign key constraint fails (`agendamento_medico`.`paciente`, CONSTRAINT `convenio_fk` FOREIGN KEY (`codigo_convenio`) REFERENCES `convenio` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION)";
            
            if(e.toString().equals(var)){
                JOptionPane.showMessageDialog(null, "Erro ao remover, ele está sendo usado em algum paciente");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir convenio: " + e.getMessage());
                System.out.println("Erro ao remover:" + e + ":");
            }
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão do remove:"+ex.getMessage());
            }
        }
        return status;
    }

    @Override
    public List<Convenio> listAll() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Convenio> convenio = new ArrayList<>();
        
        try{
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while(rs.next()){
            
                Convenio f = new Convenio();
                f.setNome(rs.getString("nome"));
                f.setCodigo(rs.getInt("codigo"));
                convenio.add(f);
            
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao listar convenios(all): " + e.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm, rs);
            }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao fechar conexão de listar convenio: " + e.getMessage());   
            }
        }
        return convenio;
    }
    
    @Override
    public Convenio listById(int codigo) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Convenio f = new Convenio();
        try{
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYID);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while(rs.next()){
                
                f.setCodigo(rs.getInt("codigo"));
                f.setNome(rs.getString("nome"));
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar convenio(id): "+e.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão do listar: "+ex.getMessage());
            }
        }
        return f;
    }
    
    @Override
    public List<Convenio> listByNome(String nome) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        
        List<Convenio> convenio = new ArrayList<>();
        
        try{
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYNOME);
            pstm.setString(1, "%" + nome + "%");
            rs = pstm.executeQuery();
            while(rs.next()){
            //(nome, login, senha, telefone, celular, endereco, cidade, estado)
            
                Convenio f = new Convenio();
                f.setNome(rs.getString("nome"));
                f.setCodigo(rs.getInt("codigo"));
                convenio.add(f);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao pesquisar convenio: " + e.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(con, pstm, rs);
            }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao fechar conexão de pesquisar convenio: " + e.getMessage());   
            }
        }
        return convenio;
    }
}