package br.com.model.app;

import br.com.model.view.JanelaLogin;

/**
 *
 * @author Ederson
 */
public class Principal {
    public static void main(String args[]){
        JanelaLogin jl = new JanelaLogin();        
        jl.setLocationRelativeTo(null);
        jl.setVisible(true);
    }
}
