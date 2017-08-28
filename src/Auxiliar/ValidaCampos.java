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
public class ValidaCampos {
    
    public boolean validar(int tamanho, String texto){
        if(texto == null){
            texto = "";
        }
        int t = texto.length();
        if(texto.length() > tamanho){
            JOptionPane.showMessageDialog(null, "Número de caracteres excede o permitido.\nVocê digitou "
                    +texto.length()+" caracteres.\nO permitido é até "+tamanho+".\nDiminua os caracteres.");
            return false;
        }
        return true;
    }
    
    //tipo 0 = francionário e tipo 1 = inteiros
    public int validarNumeros(int tipo, int tam, String campo){
        int result = 0;
        int x = 0;
        int ponto = 0;
        int virgula = 0;
        char[] num = new char[]{'0','1','2','3','4','5','6','7','8','9','.',','};
        
        if(campo.length() <= tam){
            for(int i = 0; i < campo.length(); i++){
                for(int j = 0; j < 12; j++){
                    if(campo.charAt(i) == num[j]){
                        x++;
                        if(campo.charAt(i) == ',')
                            virgula++;
                        if(campo.charAt(i) == '.')
                            ponto++;
                    }
                }
            }
            if(x == campo.length()){
                if(tipo == 0){
                    if(ponto > 1 || virgula > 1){
                        //Retorna 6 se o número de ponto ou vírgula for maior que 1
                        result = 6; 
                    }else{
                        if(ponto+virgula == 1)
                            //retorn 7 se o número possuir uma vírgula e um ponto
                            result = 7;
                        else
                            //Retorna 8 se o número estiver ok
                            result = 8;
                    }
                }else if(tipo == 1){
                    if(campo.indexOf(',') != -1 || campo.indexOf('.') != -1){
                        //Retorna 4 quando o número não é inteiro
                        result = 4;
                    }else{
                        //Retorna 3 para números inteiros
                        result = 3;                  
                    }
                }
            }else{
               //Retorna 2 quando for detectar um caractere que não seja número, ponto ou vírgula
               result = 2;
            }
        }else{
            //Retorna 1 quando o número de caracteres ultrapassar o permitido
            result = 1;
        }
        return result;  
    }
    
    /*
    public static void main(String[] args){
        ValidaCampos vc = new ValidaCampos();
        System.out.println("Resultado: "+vc.validarNumeros(0, 4, "1,05"));
    }
    */
}
