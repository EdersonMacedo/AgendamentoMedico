package br.com.teste.view.testeabstract;

import br.com.model.paciente.Consulta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ederson
 */
public class ConsultaTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_HORA = 1;
    private static final int COL_DATA = 2;
    private static final int COL_NOME = 3;
    private static final int COL_TIPO = 4;
    List<Consulta> linhas;
    private String[] colunas = new String[]{"ID", "Horário", "Data da consulta", "Nome", "Tipo de Consulta"};


    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Consulta m = linhas.get(row);
        if (column == COL_ID) {
            return m.getCodigo();
        } else if (column == COL_HORA) {
            return m.getHorario();
        } else if (column == COL_DATA) {
            return m.getDataDaConsulta();
        } else if (column == COL_NOME) {
            return m.getPaciente().getNome();
        } else if (column == COL_TIPO) {
            return m.getTipoConsulta();
        }
        return "";
    }

    public ConsultaTableModel(List<Consulta> consultas) {
        this.linhas = new ArrayList<>(consultas);
    }
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return false;
    }
    
    public Consulta getConsulta(int indiceLinha){
        return linhas.get(indiceLinha);
    }
    
    public void addConsulta(Consulta consulta){
        linhas.add(consulta);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
}