/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Auxiliar.Erros;
import DAO.AlunoDAO;
import DAO.CursoDAO;
import DAO.MovimentoDAO;
import Objetos.Aluno;
import Objetos.Curso;
import Objetos.Disciplina;
import Objetos.Movimento;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author asfmegas
 */
public class CadastroMovGUI extends javax.swing.JFrame {
    private int idCurso;
    private int idDisciplina;
    private int idAluno;
    private int x,y = 0;
    //private int idMov;
    private DefaultTableModel tmAluno = new DefaultTableModel(null, new String[]{"ID","NOME","NASCIMENTO"});
    private List<Aluno> listaAluno;
    private ListSelectionModel lsmAluno;
    private DefaultTableModel tmCurso = new DefaultTableModel(null, new String[]{"CURSO","DISCIPLINA"});
    private List<Disciplina> listaCurso;
    private ListSelectionModel lsmCurso;
    private SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");
    private Date hoje = new Date();
    /**
     * Creates new form CadastroMovGUI
     */
    public CadastroMovGUI() {
        setTitle("CRIAR MOVIMENTO - SADA V 1.1");
        initComponents();
        
        setLocationRelativeTo(null);
        setResizable(false);
        informar();
        
        jtNome.setEditable(false);
        jtCurso.setEditable(false);
        jtDisc.setEditable(false);
        jtPesquisa.setDocument(new CustomDocument());
        jtPesquisa.requestFocus();
        jtTabela.getTableHeader().setReorderingAllowed(false); 
        int x = 0;
        jtTabela.getColumnModel().getColumn(x++).setPreferredWidth(10);
        jtTabela.getColumnModel().getColumn(x++).setPreferredWidth(450);
        jtTabela.getColumnModel().getColumn(x++).setPreferredWidth(40);
        
        jtTabela2.getTableHeader().setReorderingAllowed(false); 
        
        jtTurma.setDocument(new CustomDocument());
        
        //carregarDisciplina();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabela = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtPesquisa = new javax.swing.JTextField();
        jbPesquisa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbCadastrar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jcCurso = new javax.swing.JComboBox();
        jtNome = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtTabela2 = new javax.swing.JTable();
        jtCurso = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtDisc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtTurma = new javax.swing.JTextField();
        jcTurma = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtTabela.setModel(tmAluno);
        jtTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmAluno = jtTabela.getSelectionModel();
        lsmAluno.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selecionarLinhaTabela(jtTabela);
                }
            }
        });
        jScrollPane1.setViewportView(jtTabela);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("NOME:");

        jtPesquisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtPesquisaActionPerformed(evt);
            }
        });

        jbPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisa-sada.png"))); // NOI18N
        jbPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbPesquisa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbPesquisa)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jbCadastrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jbCadastrar.setForeground(new java.awt.Color(0, 0, 153));
        jbCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/salvar-sada.png"))); // NOI18N
        jbCadastrar.setText("SALVAR");
        jbCadastrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarActionPerformed(evt);
            }
        });

        jbCancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jbCancelar.setForeground(new java.awt.Color(0, 0, 153));
        jbCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar-sada.png"))); // NOI18N
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCadastrar)
                    .addComponent(jbCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("CURSO:");

        jcCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcCursoActionPerformed(evt);
            }
        });

        jtNome.setBackground(new java.awt.Color(51, 204, 255));
        jtNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jtTabela2.setModel(tmCurso);
        jtTabela2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmCurso = jtTabela2.getSelectionModel();
        lsmCurso.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selecionarLinhaTabelaCurso(jtTabela2);
                }
            }
        });
        jScrollPane2.setViewportView(jtTabela2);

        jtCurso.setBackground(new java.awt.Color(51, 204, 255));
        jtCurso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("NOME:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CURSO:");

        jtDisc.setBackground(new java.awt.Color(51, 204, 255));
        jtDisc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("DISCIPLINA:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("TURMA:");

        jtTurma.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jcTurma.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jcTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcTurmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcCurso, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtCurso)
                            .addComponent(jtNome)
                            .addComponent(jtDisc)
                            .addComponent(jtTurma)
                            .addComponent(jcTurma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtDisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarActionPerformed
        Principal gui = new Principal();
        gui.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jbCancelarActionPerformed

    private void jbPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPesquisaActionPerformed
        try {
            listarAlunos();
        } catch (SQLException ex) {
            Erros.erroSql("Erro ao listar alunos\n"+ex.getMessage());
        }
    }//GEN-LAST:event_jbPesquisaActionPerformed

    private void jbCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarActionPerformed
        if(confirmarSalvarDados()){
            salvarMovimento();
            MovimentoGUI gui = new MovimentoGUI();
            gui.setVisible(true);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Todos os dados precisam ser definidos para se criar um movimento.");
        }
    }//GEN-LAST:event_jbCadastrarActionPerformed

    private void jtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtPesquisaActionPerformed
        try {
            listarAlunos();
        } catch (SQLException ex) {
            Erros.erroSql("Erro ao listar alunos\n"+ex.getMessage());
        }
    }//GEN-LAST:event_jtPesquisaActionPerformed

    private void jcCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCursoActionPerformed
        try {
            if(x==1){
                listarCursos("%"+jcCurso.getSelectedItem().toString()+"%");
            }else{
                x=1;
            }
        } catch (SQLException ex) {
            Erros.erroSql("Erro ao listar cursos\n"+ex.getMessage());
        } 
    }//GEN-LAST:event_jcCursoActionPerformed

    private void jcTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcTurmaActionPerformed
        jtTurma.setText(jcTurma.getSelectedItem().toString());
    }//GEN-LAST:event_jcTurmaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroMovGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroMovGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroMovGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroMovGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroMovGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbCadastrar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbPesquisa;
    private javax.swing.JComboBox jcCurso;
    private javax.swing.JComboBox jcTurma;
    private javax.swing.JTextField jtCurso;
    private javax.swing.JTextField jtDisc;
    private javax.swing.JTextField jtNome;
    private javax.swing.JTextField jtPesquisa;
    private javax.swing.JTable jtTabela;
    private javax.swing.JTable jtTabela2;
    private javax.swing.JTextField jtTurma;
    // End of variables declaration//GEN-END:variables

    //Classe para manter caixa alta no JTextField
    //Referência
    //http://www.guj.com.br/java/199701-jtextfield-com-caixa-altaresolvido
    public class CustomDocument extends PlainDocument {

        @Override
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;
		super.insertString(offset, str.toUpperCase(), attr); // Detalhe no str
	}
    }
    
    private void salvarMovimento() {
        idCurso = getIdCurso();
        MovimentoDAO dao = new MovimentoDAO();
        Movimento mov = new Movimento();
        
        mov.setId(idAluno);
        mov.setIdCurso(idCurso);
        mov.setIdDisc(idDisciplina);
        mov.setDataAlteracao(data.format(hoje));
        mov.setTurma(jtTurma.getText());
        dao.salvar(mov);
    }
    
    private void informar(){
        jbCadastrar.setToolTipText("Realizar cadastramento da movimentação do aluno.");
        jbCancelar.setToolTipText("Cancela a criação de movimento.");
        jbCancelar.setToolTipText("Cancelar criação de movimento");
    }
    
    //pesquisar curso
    private void carregarCursos(){
        CursoDAO dao = new CursoDAO();
        Curso curso = new Curso();
        jcCurso.addItem("");
        for(Curso lista: dao.pesquisar(curso)){
            jcCurso.addItem(lista.getCurso());
        }
    }
    
    private int getIdCurso(){
        CursoDAO dao = new CursoDAO();
        Curso curso = new Curso();
        
        for (Curso lista : dao.pesquisar(curso)) {
            if(lista.getCurso().equals(jcCurso.getSelectedItem())){
                idCurso = lista.getId();
            }
        }
        return idCurso;
    }
    
    //pesquisar por aluno
    private void listarAlunos() throws SQLException{
        AlunoDAO dao = new AlunoDAO();
        listaAluno = dao.pesquisar("%"+jtPesquisa.getText()+"%");
        mostrarPesquisa(listaAluno);
        carregarCursos();
    }

    private void mostrarPesquisa(List<Aluno> alunos) {
        while(tmAluno.getRowCount() > 0){
            tmAluno.removeRow(0);
        }
        if(alunos.isEmpty()){
            JOptionPane.showMessageDialog(null, "Sua lista está vazia!");
        }else{
            String[] linhas = new String[]{null,null,null};
            for(int i=0; i < alunos.size();i++){
                tmAluno.addRow(linhas);
                tmAluno.setValueAt(alunos.get(i).getId(), i, 0);
                tmAluno.setValueAt(alunos.get(i).getNome(), i, 1);
                tmAluno.setValueAt(formatarDataCampo(alunos.get(i).getData()), i, 2);
            }
        }
    }
    
    private void selecionarLinhaTabela(JTable tabela){
        if(jtTabela.getSelectedRow() != -1){
            jtNome.setText(listaAluno.get(tabela.getSelectedRow()).getNome());
            idAluno = listaAluno.get(tabela.getSelectedRow()).getId();
            if(!jtNome.getText().equals("")){
                if(y == 0){
                    listarTurma();
                    y = 1;
                }
            }
        }else{
            jtNome.setText("");
        }
    }
    
    //pesquisar por curso
    private void listarCursos(String curso) throws SQLException{
        MovimentoDAO dao = new MovimentoDAO();
        listaCurso = dao.pesquisarCurDisc(curso);
        mastrarPesquisaCurso(listaCurso);
    }

    private void mastrarPesquisaCurso(List<Disciplina> curso) {
        while(tmCurso.getRowCount() > 0){
            tmCurso.removeRow(0);
        }
        if(curso.isEmpty()){
            JOptionPane.showMessageDialog(null, "Este curso não possui disciplina cadastrada!");
        }else{
            String[] linhas = new String[]{null,null};
            for(int i=0; i < curso.size();i++){
                tmCurso.addRow(linhas);
                tmCurso.setValueAt(curso.get(i).getCurso(), i, 0);
                tmCurso.setValueAt(curso.get(i).getNome(), i, 1);
            }
        }
    }
    
    private void selecionarLinhaTabelaCurso(JTable tabela){
        if(jtTabela2.getSelectedRow() != -1){
            jtCurso.setText(listaCurso.get(tabela.getSelectedRow()).getCurso());
            jtDisc.setText(listaCurso.get(tabela.getSelectedRow()).getNome());
            idCurso = listaCurso.get(tabela.getSelectedRow()).getId();
            idDisciplina = listaCurso.get(tabela.getSelectedRow()).getIdDisc();
        }else{
            jtCurso.setText("");
            jtDisc.setText("");
        }
    }
    
    private boolean confirmarSalvarDados(){
        if(!jtDisc.getText().equals("") && !jtNome.getText().equals("") && !jtCurso.getText().equals("") && !jtTurma.getText().equals("")){
            return true;
        }else{
            return false;
        }
    }
    
    private String formatarDataCampo(String data){
        char[] num = new char[data.length()];
        for(int i=0; i < data.length(); i++){
            num[i] = data.charAt(i);
        }
        String dia = ""+num[8]+num[9];
        String mes = ""+num[5]+num[6];
        String ano = ""+num[0]+num[1]+num[2]+num[3];
        String d = dia+"/"+mes+"/"+ano;
            //1983-02-18
            //0 1 2 3 - 5 6 - 8 9
        return d;
    }
    
    private void listarTurma(){
        jcTurma.removeAllItems();
        MovimentoDAO dao = new MovimentoDAO();
        for(Movimento mov : dao.pesquisarTurma()){
            jcTurma.addItem(mov.getTurma());
        }
    }
    
}
