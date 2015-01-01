/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Auxiliar;

import javax.swing.JOptionPane;

/**
 *
 * @author asfmegas
 */
public class Erros {
    
    
    public static void erroSql(String frase){
        JOptionPane.showMessageDialog(null, frase, "Erro detectado em execusão",JOptionPane.ERROR_MESSAGE);
    }
}
