package br.com.model.persistencia;

import br.com.model.paciente.Convenio;
import br.com.model.paciente.Paciente;
import br.com.model.persistencia.dao.PacienteDAO;
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
 * @author Ederson
 */
public class PacienteDAOImplements implements PacienteDAO {

    private static final String INSERT = "insert into paciente(nome, telefone, celular, data_nascimento, rg, "
            + "endereco, cidade, estado, codigo_convenio) values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "update paciente set nome = ?, telefone = ?, celular = ?, data_nascimento = ?,"
            + " rg = ?, endereco = ?, cidade = ?, estado = ?,codigo_convenio = ? where codigo = ?;";
    private static final String REMOVE = "delete from paciente where codigo = ?";
    private static final String LIST = "select * from paciente, convenio "
            + "where paciente.codigo_convenio = convenio.codigo";
    private static final String LISTBYNOME = "select * from paciente where nome like ?;";
    private static final String LISTBYID = "select * from "
    + "paciente, convenio where paciente.codigo_convenio"
    +  " = convenio.codigo and paciente.codigo = ?";

    @Override
    public int salvar(Paciente p) {
        if (p.getCodigo() == 0) {
            return insert(p);
        } else {
            return update(p);
        }
    }

    @Override
    public boolean remove(int codigo) {
        boolean status = false;
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(REMOVE);
            pstm.setInt(1, codigo);
            pstm.execute();
            status = true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente: " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão do remove:" + e.getMessage());
            }
        }
        return status;
    }

    @Override
    public List<Paciente> listAll() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Paciente> pacientes = new ArrayList<>();

        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //nome, telefone, celular, data_nascimento, rg, enderecocodigo
                Paciente p = new Paciente();
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getInt("codigo"));
                p.setTelefone(rs.getString("telefone"));
                p.setCelular(rs.getString("celular"));
                p.setDataNascimento(rs.getDate("data_nascimento"));
                p.setRg(rs.getString("rg"));
                p.setEndereco(rs.getString("endereco"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                Convenio c = new Convenio();
                c.setCodigo(rs.getInt("convenio.codigo"));
                c.setNome(rs.getString("convenio.nome"));
                p.setConvenio(c);

                pacientes.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao fechar a conexão"
                        + e.getMessage());
            }
        }
        return pacientes;
    }

    @Override
    public List<Paciente> listByNome(String nome) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Paciente> pacientes = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYNOME);
            pstm.setString(1, "%" + nome + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                //nome, telefone, celular, data_nascimento, rg, enderecocodigo

                Paciente p = new Paciente();

                p.setCodigo(rs.getInt("codigo"));
                p.setNome(rs.getString("nome"));
                p.setDataNascimento(rs.getDate("data_nascimento"));
                p.setTelefone(rs.getString("telefone"));
                p.setCelular(rs.getString("celular"));
                p.setRg(rs.getString("rg"));
                p.setEndereco(rs.getString("endereco"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                Convenio c = new Convenio();
                c.setCodigo(rs.getInt("convenio.codigo"));
                c.setNome(rs.getString("convenio.nome"));
                p.setConvenio(c);

                pacientes.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar paciente: " + e.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão de pesquisar paciente: " + e.getMessage());
            }
        }
        return pacientes;
    }

    private int insert(Paciente p) {
        Connection con = null;
        PreparedStatement pstm = null;
        int retorno = -1;

        try {
            //(nome, telefone, celular, data_nascimento, rg, enderecocodigo)
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getTelefone());
            pstm.setString(3, p.getCelular());
            pstm.setDate(4, new java.sql.Date(p.getDataNascimento().getTime()));
            pstm.setString(5, p.getRg());
            pstm.setString(6, p.getEndereco());
            pstm.setString(7, p.getCidade());
            pstm.setString(8, p.getEstado());
            pstm.setInt(9, p.getConvenio().getCodigo());
            pstm.execute();
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    retorno = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir paciente: " + ex.getMessage());
        } finally {
            try {

                ConnectionFactory.closeConnection(con, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em inserir paciente: " + e.getMessage());
            }
        }
        return retorno;
    }

    private int update(Paciente p) {
        int retorno = -1;
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(UPDATE);
//nome = ?, telefone = ?, celular = ?, data_nascimento = ?, rg = ?, endereco = ?, cidade = ?, estado = ? 
            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getTelefone());
            pstm.setString(3, p.getCelular());
            pstm.setDate(4, new java.sql.Date(p.getDataNascimento().getTime()));
            pstm.setString(5, p.getRg());
            pstm.setString(6, p.getEndereco());
            pstm.setString(7, p.getCidade());
            pstm.setString(8, p.getEstado());
            pstm.setInt(9, p.getConvenio().getCodigo());
            pstm.setInt(10, p.getCodigo());
            pstm.execute();
            retorno = p.getCodigo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar paciente: " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em editar: " + e.getMessage());
            }
        }
        return retorno;
    }

    @Override
    public Paciente listById(int codigo) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Paciente p = new Paciente();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYID);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while (rs.next()) {

                p.setCodigo(rs.getInt("codigo"));
                p.setNome(rs.getString("nome"));
                p.setDataNascimento(rs.getDate("data_nascimento"));
                p.setTelefone(rs.getString("telefone"));
                p.setCelular(rs.getString("celular"));
                p.setRg(rs.getString("rg"));
                p.setEndereco(rs.getString("endereco"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                
                Convenio c = new Convenio();
                c.setCodigo(rs.getInt("convenio.codigo"));
                c.setNome(rs.getString("convenio.nome"));
                p.setConvenio(c);
            
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar(id) paciente: " + e.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão do listar paciente: " + ex.getMessage());
            }
        }
        return p;
    }
}