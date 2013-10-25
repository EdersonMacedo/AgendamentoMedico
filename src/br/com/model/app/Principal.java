package br.com.model.app;

import br.com.model.view.FuncionarioInserirGUI;

/**
 *
 * @author Ederson
 */
public class Principal {
    public static void main(String args[]){
        FuncionarioInserirGUI figui = new FuncionarioInserirGUI(null);
        figui.setLocationRelativeTo(null);
        figui.dispose();
        figui.setVisible(true);
    }
}
