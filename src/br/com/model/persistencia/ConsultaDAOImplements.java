package br.com.model.persistencia;

import br.com.model.paciente.Consulta;
import br.com.model.paciente.Paciente;
import br.com.model.persistencia.dao.ConsultaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ederson
 */
public class ConsultaDAOImplements implements ConsultaDAO {

    private static final String INSERT = "insert into consulta(data_consulta, descricao, "
            + "tipo_consulta, horario, codigo_paciente) values (?, ?, ?, ?, ?);";//ORIGINAL
    //private static final String INSERT = "insert into consulta(data_consulta, descricao) values (?,?);";
    private static final String UPDATE = "update consulta set data_consulta = ?, descricao = ?, tipo_consulta = ?, horario = ?, codigo_paciente = ? where codigo = ?;";
    private static final String REMOVE = "select *from consulta, "
            + "paciente where consulta.codigo_paciente = "
            + "paciente.codigo and consulta.codigo = ?";
    private static final String LIST = "SELECT consulta.data_consulta, consulta.horario, paciente.nome, paciente.codigo ,consulta.codigo,consulta.tipo_consulta, consulta.descricao FROM consulta left JOIN paciente ON consulta.codigo_paciente=paciente.codigo ;";
    private static final String LISTBYNOME = "select *from consulta, paciente where consulta.codigo_paciente = paciente.codigo and paciente.nome like ?;";
    private static final String LISTBYIDINSERT = "select *from consulta, "
            + "paciente where consulta.codigo_paciente = "
            + "paciente.codigo OR consulta.codigo = ?";
    private static final String LISTBYIDUPDATE = "select *from consulta, "
            + "paciente where consulta.codigo_paciente = "
            + "paciente.codigo and consulta.codigo = ?";
    private static final String LISTBYDATE = "SELECT consulta.data_consulta, consulta.horario, paciente.nome, paciente.codigo ,consulta.codigo,consulta.tipo_consulta, consulta.descricao FROM consulta left JOIN paciente ON consulta.codigo_paciente=paciente.codigo where consulta.data_consulta like ?;";
    private static final String LISTALL = " insert into CONSULTA (data_consulta,horario,data_horario) values (?,?,?)";

    @Override
    public int salvar(Consulta p) {
        if (p.getCodigo() == 0) {
            return insert(p);
        } else {
            return update(p);
        }
    }

    
    public int remove(Consulta p) {
        Connection con = null;
        PreparedStatement pstm = null;
        int retorno = -1;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(UPDATE);

            pstm.setDate(1, new java.sql.Date(p.getDataDaConsulta().getTime()));
            pstm.setString(2, "");
            pstm.setString(3, "");
            pstm.setTime(4, p.getHorario());
            pstm.setInt(5, 0);
            pstm.setInt(6, p.getCodigo());
            pstm.execute();
            retorno = p.getCodigo();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir consulta: " + e.getMessage());
        } finally {
            try {

                ConnectionFactory.closeConnection(con, pstm);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao finalizar conexão em inserir paciente: " + e.getMessage());
            }
        }
        return retorno;
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
    public Consulta listByIdInsert(int codigo) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Consulta c = new Consulta();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYIDINSERT);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario

                c.setCodigo(rs.getInt("consulta.codigo"));
                c.setDataDaConsulta(rs.getDate("data_consulta"));
                c.setDescricao(rs.getString("descricao"));
                c.setTipoConsulta(rs.getString("tipo_consulta"));
                c.setHorario(rs.getTime("horario"));
                Paciente p = new Paciente();
                p.setCodigo(rs.getInt("paciente.codigo"));
                p.setNome(rs.getString("paciente.nome"));
                c.setPaciente(p);
            }
            System.out.println("Paciente: " + c.getPaciente().getNome());
            if (!(c.getPaciente().getNome().equals("")) && !(c.getDescricao().equals("") || !(c.getDescricao().equals(null)))) {
                JOptionPane.showMessageDialog(null, "Já tem alguém nesse dia(ListByInsert)...");
                return null;
            }

            System.out.println(c.getCodigo() + "  " + c.getDescricao());
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

    @Override
    public List<Consulta> listPorDate(Date data) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        List<Consulta> consultas = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYDATE);
            pstm.setString(1, "%" + new java.sql.Date(data.getTime()) + "%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario
                Consulta c = new Consulta();
                c.setCodigo(rs.getInt("consulta.codigo"));
                c.setDataDaConsulta(rs.getDate("consulta.data_consulta"));
                c.setDescricao(rs.getString("consulta.descricao"));
                c.setTipoConsulta(rs.getString("consulta.tipo_consulta"));
                c.setHorario(rs.getTime("consulta.horario"));
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

    public void gerarConsultaTudo(Date data) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTALL);

            //Inserindo 8h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("08:00:00"));
            //Começo da atualização da data
            long time = System.currentTimeMillis();
            java.sql.Timestamp datahora = new Timestamp(time);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            int diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 8h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("08:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("09:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("09:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 10h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("10:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 10h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("10:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("11:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 11);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 11h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("11:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 11);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("13:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 8h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("13:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("14:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 8h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("14:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("15:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 8h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("15:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("16:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 16);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 8h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("16:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 16);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("17:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 17);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 8h30m
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("17:30:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 17);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

            //inserindo 9h
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            pstm.setTime(2, Time.valueOf("18:00:00"));
            //Começo da atualização da data
            calendar.setTime(datahora);
            calendar.setTime(data);
            //hora 
            calendar.set(Calendar.HOUR_OF_DAY, 18);
            calendar.set(Calendar.MINUTE, 00);
            calendar.set(Calendar.SECOND, 00);
            //converte novamente para data e hora
            datahora = new Timestamp(calendar.getTimeInMillis());
            diaDoMes = calendar.get(Calendar.MONTH);
            pstm.setTimestamp(3, datahora);
            pstm.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao iserir manhã:" + e.getMessage());
        }
    }

    @Override
    public Consulta listByIdUpdate(int codigo) {

        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        Consulta c = new Consulta();
        try {
            con = ConnectionFactory.getConnection();
            pstm = con.prepareStatement(LISTBYIDUPDATE);
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //codigo, data_consulta,descricao, codigo_paciente,tipo_consulta,horario

                c.setCodigo(rs.getInt("consulta.codigo"));
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
}