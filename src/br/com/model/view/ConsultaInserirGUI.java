package br.com.model.view;

import br.com.model.controller.ConsultaController;
import br.com.model.controller.PacienteController;
import br.com.model.paciente.Consulta;
import br.com.model.paciente.Paciente;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ederson
 */
public class ConsultaInserirGUI extends javax.swing.JFrame {

    private DefaultTableModel modelo;
    private int linhaSelecionada;
    private int idEndereco;

    public ConsultaInserirGUI(DefaultTableModel modelo) {
        initComponents();
        this.modelo = modelo;
        carregarCombo();

    }

    //---INSERT---
    public ConsultaInserirGUI(DefaultTableModel modelo, int linhaSelecionada, int idConsulta) {
        this.modelo = modelo;
        this.linhaSelecionada = linhaSelecionada;
        System.out.println("linhaSelecionada: " + linhaSelecionada);
        System.out.println("idConsulta: " + idConsulta);
        initComponents();
        ConsultaController cc = new ConsultaController();
        Consulta p = cc.listByIdInsert(idConsulta);
        System.out.println("Valor do p: " + p);
        if ((p.equals(null)) || (p.equals(""))) {
            System.out.println("Entrou no setVisible false");
            setVisible(false);
            return;
        }

        try {
            txCodigo.setText(Integer.toString(p.getCodigo()));
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(codigo)" + e.getMessage());
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            String data = sdf.format(p.getDataDaConsulta());
            txDataConsulta.setText(data);
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(data) " + e.getMessage());
        }
        try {
            SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
            String hora = sdfh.format(p.getHorario());
            txHorario.setText(hora);
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(horas) " + e.getMessage());
        }
        try {
            if (p.getTipoConsulta().equals("Consulta")) {
                cbTipoConsulta.setSelectedIndex(0);
            } else if (p.getTipoConsulta().equals("Encaixe")) {
                cbTipoConsulta.setSelectedIndex(1);
            } else if (p.getTipoConsulta().equals("Retorno")) {
                cbTipoConsulta.setSelectedIndex(2);
            } else {
                cbTipoConsulta.setSelectedIndex(0);
            }
            txDescricao.setText(p.getDescricao());

        } catch (Exception e) {
            System.out.println("Entrou na excessão de cbTipoConsulta");
            cbTipoConsulta.setSelectedIndex(0);
            System.out.println("Caiu em uma exceção(tipo de consulta) " + e.getMessage());
        }

        try {
            carregarCombo(p.getPaciente());
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(Combo) " + e.getMessage());
        } finally {
            if ((txDescricao.getText().equals(null)) || (txDescricao.getText().equals(""))) {
                System.out.println("txDescricao:" + txDescricao.getText() + ":");
                System.out.println("entrou no txDescricao");
                cbPaciente.setSelectedIndex(0);
                setVisible(true);
            }
        }
    }
    //---UPDATE---
    public ConsultaInserirGUI(int linhaSelecionada, int idConsulta, DefaultTableModel modelo) {
        this.modelo = modelo;
        this.linhaSelecionada = linhaSelecionada;
        System.out.println("linhaSelecionada: " + linhaSelecionada);
        System.out.println("idConsulta: " + idConsulta);
        initComponents();
        ConsultaController cc = new ConsultaController();
        Consulta p = cc.listByIdUpdate(idConsulta);
        System.out.println("Valor do p: " + p);
        if ((p.equals(null)) || (p.equals(""))) {
            System.out.println("Entrou no setVisible false");
            setVisible(false);
            return;
        }

        try {
            txCodigo.setText(Integer.toString(p.getCodigo()));
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(codigo)" + e.getMessage());
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            String data = sdf.format(p.getDataDaConsulta());
            txDataConsulta.setText(data);
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(data) " + e.getMessage());
        }
        try {
            SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
            String hora = sdfh.format(p.getHorario());
            txHorario.setText(hora);
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(horas) " + e.getMessage());
        }
        try {
            if (p.getTipoConsulta().equals("Consulta")) {
                cbTipoConsulta.setSelectedIndex(0);
            } else if (p.getTipoConsulta().equals("Encaixe")) {
                cbTipoConsulta.setSelectedIndex(1);
            } else if (p.getTipoConsulta().equals("Retorno")) {
                cbTipoConsulta.setSelectedIndex(2);
            } else {
                cbTipoConsulta.setSelectedIndex(0);
            }
            txDescricao.setText(p.getDescricao());

            System.out.println("Entrou na excessão de ");
            cbTipoConsulta.setSelectedIndex(0);

        } catch (Exception e) {

            System.out.println("Caiu em uma exceção(tipo de consulta) " + e.getMessage());
        }

        try {
            carregarCombo(p.getPaciente());
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(Combo) " + e.getMessage());
        } finally {
            System.out.println("txCodigo:" + txCodigo.getText() + ":");

            if (!(txCodigo.getText().equals(0))&&(Integer.parseInt(txCodigo.getText())>0)) {
                System.out.println("txCodigo:" + txCodigo.getText() + ":");
                System.out.println("entrou no txCodigo(Up)");
                setVisible(true);
                return;
            }
            System.out.println("NÃO entrou no txCodigo(Up):"+txCodigo.getText()+":");
        }
    }
    //---Excluir---
    public ConsultaInserirGUI(int linhaSelecionada, DefaultTableModel modelo, int idConsulta) {
        this.modelo = modelo;
        this.linhaSelecionada = linhaSelecionada;
        System.out.println("linhaSelecionada: " + linhaSelecionada);
        System.out.println("idConsulta: " + idConsulta);
        initComponents();
        ConsultaController cc = new ConsultaController();
        Consulta p = cc.listByIdUpdate(idConsulta);
        System.out.println("Valor do p: " + p);
        if ((p.equals(null)) || (p.equals(""))) {
            System.out.println("Entrou no setVisible false");
            setVisible(false);
            return;
        }

        try {
            txCodigo.setText(Integer.toString(p.getCodigo()));
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(codigo)" + e.getMessage());
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            String data = sdf.format(p.getDataDaConsulta());
            txDataConsulta.setText(data);
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(data) " + e.getMessage());
        }
        try {
            SimpleDateFormat sdfh = new SimpleDateFormat("hh:mm:ss");
            String hora = sdfh.format(p.getHorario());
            txHorario.setText(hora);
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(horas) " + e.getMessage());
        }
        try {
            if (p.getTipoConsulta().equals("Consulta")) {
                cbTipoConsulta.setSelectedIndex(0);
            } else if (p.getTipoConsulta().equals("Encaixe")) {
                cbTipoConsulta.setSelectedIndex(1);
            } else if (p.getTipoConsulta().equals("Retorno")) {
                cbTipoConsulta.setSelectedIndex(2);
            } else {
                cbTipoConsulta.setSelectedIndex(0);
            }
            txDescricao.setText(p.getDescricao());

            System.out.println("Entrou na excessão de ");
            cbTipoConsulta.setSelectedIndex(0);

        } catch (Exception e) {

            System.out.println("Caiu em uma exceção(tipo de consulta) " + e.getMessage());
        }

        try {
            carregarCombo(p.getPaciente());
        } catch (Exception e) {
            System.out.println("Caiu em uma exceção(Combo) " + e.getMessage());
        } finally {
            System.out.println("txCodigo:" + txCodigo.getText() + ":");

            if (!(txCodigo.getText().equals(0))&&(Integer.parseInt(txCodigo.getText())>0)) {
                System.out.println("txCodigo:" + txCodigo.getText() + ":");
                System.out.println("entrou no txCodigo(Up)");
                int id = cc.remove(p);
                if(id>0){
                    System.out.println("Conferir exclusão...");
                }
                return;
            }
            System.out.println("NÃO entrou no txCodigo(Up):"+txCodigo.getText()+":");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txCodigo = new javax.swing.JTextField();
        PainelSuperior = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        PainelInferior = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btSalvar = new javax.swing.JButton();
        btDeletar = new javax.swing.JButton();
        cbPaciente = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbTipoConsulta = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txDataConsulta = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        txHorario = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txDescricao = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Inserir Consulta[EMJ]");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Paciente.:");

        jLabel2.setText("Codigo.:");

        txCodigo.setEditable(false);
        txCodigo.setEnabled(false);

        PainelSuperior.setBackground(new java.awt.Color(102, 255, 255));

        jLabel7.setText("Consulta");

        javax.swing.GroupLayout PainelSuperiorLayout = new javax.swing.GroupLayout(PainelSuperior);
        PainelSuperior.setLayout(PainelSuperiorLayout);
        PainelSuperiorLayout.setHorizontalGroup(
            PainelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelSuperiorLayout.setVerticalGroup(
            PainelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        PainelInferior.setBackground(new java.awt.Color(102, 255, 255));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/model/images/Java.png"))); // NOI18N

        javax.swing.GroupLayout PainelInferiorLayout = new javax.swing.GroupLayout(PainelInferior);
        PainelInferior.setLayout(PainelInferiorLayout);
        PainelInferiorLayout.setHorizontalGroup(
            PainelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelInferiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );
        PainelInferiorLayout.setVerticalGroup(
            PainelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelInferiorLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        btSalvar.setText("Salvar");
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo da consulta.:");

        cbTipoConsulta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Consulta", "Encaixe", "Retorno" }));

        jLabel4.setText("Data da consulta.:");

        txDataConsulta.setEditable(false);
        try {
            txDataConsulta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txDataConsulta.setEnabled(false);
        txDataConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDataConsultaActionPerformed(evt);
            }
        });

        jLabel5.setText("Horário.:");

        txHorario.setEditable(false);
        try {
            txHorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txHorario.setEnabled(false);

        txDescricao.setColumns(20);
        txDescricao.setRows(5);
        jScrollPane1.setViewportView(txDescricao);

        jLabel6.setText("Descrição.:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PainelInferior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(btSalvar)
                        .addGap(29, 29, 29)
                        .addComponent(btDeletar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PainelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbTipoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txDataConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(116, 116, 116)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSalvar)
                    .addComponent(btDeletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        // TODO add your handling code here:
        
        cbPaciente.setSelectedIndex(0);
    }//GEN-LAST:event_btDeletarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        Consulta c = new Consulta();
        if (!(txCodigo.getText().equals("")) || (txCodigo.getText().equals(null))) {
            c.setCodigo(Integer.parseInt(txCodigo.getText()));
        }
        try {
            String data = txDataConsulta.getText();
            c.setDataDaConsulta(new SimpleDateFormat("dd/MM/yyyy").parse(data));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter a data: "
                    + ex.getMessage());
        }
        try {
            String str = txHorario.getText();
            SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
            Date data;
            data = formatador.parse(str);
            Time time = new Time(data.getTime());
            c.setHorario(time);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro na conversão da hora: " + ex.getMessage());
        }
        c.setPaciente((Paciente) cbPaciente.getSelectedItem());
        c.setDescricao(txDescricao.getText());
        if (cbTipoConsulta.getSelectedIndex() == 0) {
            c.setTipoConsulta("Consulta");
        } else if (cbTipoConsulta.getSelectedIndex() == 1) {
            c.setTipoConsulta("Encaixe");
        } else if (cbTipoConsulta.getSelectedIndex() == 2) {
            c.setTipoConsulta("Retorno");
        }
        ConsultaController pc = new ConsultaController();
        if (c.getCodigo() == 0) {
            int id = pc.salvar(c);
            if (id > 0) {
                JOptionPane.showMessageDialog(this, "Salvo com sucesso... Voltando ao menu Pirncipal");

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String hora = sdf.format(c.getHorario());
                SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yyyy");
                String data = sdfd.format(c.getDataDaConsulta());
                modelo.addRow(new Object[]{id, hora, data, c.getPaciente().getNome(), c.getTipoConsulta()});
            }
        } else {
            int id = pc.salvar(c);
            if (id > 0) {

                modelo.removeRow(linhaSelecionada);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String hora = sdf.format(c.getHorario());
                SimpleDateFormat sdfd = new SimpleDateFormat("dd/MM/yyyy");
                String data = sdfd.format(c.getDataDaConsulta());
                modelo.addRow(new Object[]{id, hora, data, c.getPaciente().getNome(), c.getTipoConsulta()});
            }
        }
        ConsultaListaGUI cl = new ConsultaListaGUI();
        cl.carregarJTable();
        dispose();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void txDataConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDataConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDataConsultaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelInferior;
    private javax.swing.JPanel PainelSuperior;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbPaciente;
    private javax.swing.JComboBox cbTipoConsulta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txCodigo;
    private javax.swing.JFormattedTextField txDataConsulta;
    private javax.swing.JTextArea txDescricao;
    private javax.swing.JFormattedTextField txHorario;
    // End of variables declaration//GEN-END:variables

    private void carregarCombo(Paciente convenio) {
        //Declarar uma variável do tipo comboDefault para poder alterar os dados
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) cbPaciente.getModel();
        //remover todos os itens do combo
        comboModel.removeAllElements();
        //Buscar na base de dados os convenios cadastrados
        List<Paciente> convenios = new ArrayList<>();
        PacienteController cc = new PacienteController();
        convenios = cc.listarTodos();
        //Preencher o combo com os convenios que estão na lista
        for (int linha = 0; linha < convenios.size(); linha++) {
            Paciente c = convenios.get(linha);
            comboModel.addElement(c);
            if (convenio.getCodigo() == c.getCodigo()) {
                comboModel.setSelectedItem(c);
            }
        }

    }

    private void carregarCombo() {
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) cbPaciente.getModel();
        comboModel.removeAllElements();

        List<Paciente> convenio = new ArrayList<>();
        PacienteController cc = new PacienteController();
        convenio = cc.listarTodos();

        for (int linha = 0; linha < convenio.size(); linha++) {
            Paciente c = convenio.get(linha);
            comboModel.addElement(c);
        }
    }
}
