
package br.com.model.help;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Ederson
 */

public class Sobre {

    public Sobre() {
    
        JOptionPane.showMessageDialog(null, "\nAgendamento Médico"
                + "\n© 2013 EMJ Corporation"
                + "\nTodos os direitos reservados."
                + "\nA Agenda Médica foi desenvolvida pela equipe: Ederson Macedo, Mateus Inácio, José Silvestre,"
                + "                                               "
                + "\ncom a contribuição do SENAI e apresentado na UNESC no projeto interdisciplinar, trabalhando"
                + "\nem conjunto para a organização ao marcar consultas."
                + "\n\nContatos: ederson.mos@gmail.com, mansiba@hotmail.com, mateus.inacio@hotmail.com", 
                "Sobre a Agenda Médica", JOptionPane.PLAIN_MESSAGE, new ImageIcon("G:\\Curso\\PIC I\\AgendaMedica\\src\\br\\com\\model\\images\\LogoTipo.png"));
    }
}