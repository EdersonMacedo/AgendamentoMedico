/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model.persistencia;

import br.com.model.paciente.Consulta;
import br.com.model.paciente.Paciente;
import br.com.model.persistencia.dao.ConsultaDAO;
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
public class ConsultaDAOImplements implements ConsultaDAO {

    //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario
    private static final String INSERT = "insert into consulta(data_consulta, descricao, "
            + "tipo_consulta, horario, codigo_paciente) values (?, ?, ?, ?, ?);";
    private static final String UPDATE = "update consulta set data_consulta = ?, descricao = ?, tipo_consulta = ?, horario = ?, codigo_paciente = ? where codigo = ?;";
    private static final String REMOVE = "delete from consulta where codigo = ?";
    //private static final String LIST = "select * from consulta";
    private static final String LIST = "select * from consulta, paciente where consulta.codigo_paciente = paciente.codigo OR consulta.codigo_paciente is NULL AND consulta.codigo is NOT NULL;";
    //private static final String LIST = "select * from consulta,paciente where consulta.codigo is NOT NULL;";
    private static final String LISTBYNOME = "select *from consulta like ?;";
    private static final String LISTBYID = "select *from consulta, "
    +"paciente where consulta.codigo_paciente = "
    +"paciente.codigo and consulta.codigo = ?";

    @Override
    public int salvar(Consulta p) {
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
            JOptionPane.showMessageDialog(null, "Erro ao excluir consulta: " + ex.getMessage());
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
    public List<Consulta> listAll() {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Consulta> consultas = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LIST);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario
                Consulta c = new Consulta();
                c.setCodigo(rs.getInt("codigo"));
                c.setDataDaConsulta(rs.getDate("data_consulta"));
                c.setDescricao(rs.getString("descricao"));
                c.setTipoConsulta(rs.getString("tipo_consulta"));
                c.setHorario(rs.getTime("horario"));
                Paciente p = new Paciente();
                p.setCodigo(rs.getInt("paciente.codigo"));
                p.setNome(rs.getString("paciente.nome"));
                c.setPaciente(p);
                
                consultas.add(c);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar(All): " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao fechar a conexão"
                        + e.getMessage());
            }
        }

        return consultas;

    }

    @Override
    public Consulta listById(int codigo) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Consulta c = new Consulta();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYID);            
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario

                c.setCodigo(rs.getInt("codigo"));
                c.setDataDaConsulta(rs.getDate("data_consulta"));
                c.setDescricao(rs.getString("descricao"));
                c.setTipoConsulta(rs.getString("tipo_consulta"));
                c.setHorario(rs.getTime("horario"));
                Paciente p = new Paciente();
                p.setCodigo(rs.getInt("paciente.codigo"));
                p.setNome(rs.getString("paciente.nome"));
                c.setPaciente(p);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar(ID): " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao fechar a conexão"
                        + e.getMessage());
            }
        }

        return c;

    }

    @Override
    public List<Consulta> listByNome(String nome) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Consulta> consultas = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYNOME);
            pstm.setString(1, "%" + nome + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario
                Consulta c = new Consulta();
                c.setCodigo(rs.getInt("codigo"));
                c.setDataDaConsulta(rs.getDate("data_consulta"));
                c.setDescricao(rs.getString("descricao"));
                c.setTipoConsulta(rs.getString("tipo_consulta"));
                c.setHorario(rs.getTime("horario"));

                Paciente p = new Paciente();
                p.setCodigo(rs.getInt("paciente.codigo"));
                p.setNome(rs.getString("paciente.nome"));
                c.setPaciente(p);

                consultas.add(c);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar(Nome): " + ex.getMessage());
        } finally {
            try {
                ConnectionFactory.closeConnection(con, pstm, rs);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao fechar a conexão"
                        + e.getMessage());
            }
        }

        return consultas;

    }

    private int insert(Consulta p) {
        //data_consulta, descricao, tipo_consulta, horario, codigo_paciente
        Connection con = null;
        PreparedStatement pstm = null;
        int retorno = -1;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstm.setDate(1, new java.sql.Date(p.getDataDaConsulta().getTime()));
            pstm.setString(2, p.getDescricao());
            pstm.setString(3, p.getTipoConsulta());
            pstm.setTime(4, p.getHorario());
            pstm.setInt(5, p.getPaciente().getCodigo());
            pstm.execute();
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                if (rs.next()) {
                    retorno = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir consulta: " + e.getMessage());
        } finally {
            try {

                ConnectionFactory.closeConnection(con, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em inserir paciente: " + e.getMessage());
            }
        }
        return retorno;
    }

    private int update(Consulta p) {
        Connection con = null;
        PreparedStatement pstm = null;
        int retorno = -1;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(UPDATE);

            pstm.setDate(1, new java.sql.Date(p.getDataDaConsulta().getTime()));
            pstm.setString(2, p.getDescricao());
            pstm.setString(3, p.getTipoConsulta());
            pstm.setTime(4, p.getHorario());
            pstm.setInt(5, p.getPaciente().getCodigo());
            pstm.setInt(6, p.getCodigo());
            pstm.execute();
            retorno = p.getCodigo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar consulta: " + e.getMessage());
        } finally {
            try {

                ConnectionFactory.closeConnection(con, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em inserir paciente: " + e.getMessage());
            }
        }

        return retorno;
    }
}