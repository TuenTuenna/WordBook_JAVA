/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordbook;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jeff_jeong
 */
public class UpdatingForm extends javax.swing.JDialog {

    /**
     * Creates new form UpdatingForm1
     */
    //1.1 단어장을 준비한다.
    WordBook wordBook = new WordBook (2);
    
    public String GetCode() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordBook?useSSL=false", "root", "mysql");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Vocabulary.code FROM Vocabulary ORDER BY code DESC;");
        
        String code = "P0000";
        if(rs.next())
        {
            code = rs.getString("code");
        }
        String[] parts = code.split("P");
        int number = Integer.parseInt(parts[1]);
        code = String.format("P%04d", number+1);
        
        rs.close();
        stmt.close();
        con.close();
        
        return code;
    }
    
    public void Delete(int index) throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordBook?useSSL=false","root","mysql");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Vocabulary.code FROM Vocabulary;");
        String code = "";
        int number = 0;
        while(rs.next() && number<=index)
        {
            code = rs.getString("code");
            number++;
        }
        String sqlText = String.format("DELETE FROM Vocabulary WHERE code= '%s';", code);
        stmt.executeUpdate(sqlText);
        
        rs.close();
        stmt.close();
        con.close();
    }
    
    public void Update(int index)throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordBook?useSSL=false","root","mysql");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Vocabulary.code FROM Vocabulary;");
        
        String code = "";
        int number =0;
        while(rs.next() && number <= index)
        {
            code = (String)rs.getString("code");
            number++;
        }
        Vocabulary vocabulary = this.wordBook.GetAt(index);
        String sqlText = String.format("UPDATE Vocabulary SET partOfSpeech = '%s', meaning = '%s', example = '%s' WHERE code = '%s';"
                ,vocabulary.GetPartOfSpeech(), vocabulary.GetMeaning(),vocabulary.GetExample(),code);
        stmt.executeUpdate(sqlText);
        
        rs.close();
        stmt.close();
        con.close();
    }
    
    public void Insert(int index) throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordBook?useSSL=false","root","mysql");
        Statement stmt = con.createStatement();
        Vocabulary vocabulary = this.wordBook.GetAt(index);
        String sqlText = String.format("INSERT INTO Vocabulary(code, word, partOfSpeech, meaning, example) VALUES('%s','%s','%s','%s','%s');", this.GetCode(), vocabulary.GetWord(),vocabulary.GetPartOfSpeech(),vocabulary.GetMeaning(),vocabulary.GetExample());
        stmt.executeUpdate(sqlText);
        
        stmt.close();
        con.close();
    }
    
    public void Save() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordBook?useSSl=false","root","mysql");
        Statement stmt1 = con.createStatement();
        ResultSet rs = stmt1.executeQuery("SELECT Vocabulary.code FROM Vocabulary;");
        
        Statement stmt2 = con.createStatement(); 
        Statement stmt3 = con.createStatement();
        stmt2.executeUpdate("DELETE FROM Vocabulary;");
        
       
        int i = 0;
        Vocabulary vocabulary;
        while(rs.next())
        {
            vocabulary = this.wordBook.GetAt(i);
            String code = rs.getString("code");
            System.out.println(code);
            String sqlText = String.format("INSERT INTO Vocabulary(code, word, partOfSpeech, meaning, example) VALUES('%s', '%s', '%s', '%s','%s');"
                    , code,vocabulary.GetWord(),vocabulary.GetPartOfSpeech(),vocabulary.GetMeaning(),vocabulary.GetExample());
            stmt3.executeUpdate(sqlText);
            i++;
        }
        rs.close();
        stmt1.close();
        stmt2.close();
        stmt3.close();
        con.close();
    }
    
    public void Load() throws SQLException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordBook?useSSL=false","root","mysql");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Vocabulary.word, Vocabulary.partOfSpeech, Vocabulary.meaning, Vocabulary.example FROM Vocabulary;");
        
        while(rs.next())
        {
            String word = rs.getString("word");
            String partOfSpeech = rs.getString("partOfSpeech");
            String meaning = rs.getString("meaning");
            String example = rs.getString("example");
            this.wordBook.Record(word, partOfSpeech, meaning, example);
        }
        rs.close();
        stmt.close();
        con.close();
        
    }
    
    
    
    
    
    public UpdatingForm(java.awt.Frame parent, boolean modal) throws SQLException 
    {
        super(parent, modal);
        initComponents();
        DefaultTableModel model = (DefaultTableModel)this.VOCABULARIES.getModel();
        
        this.Load();
        int length = this.wordBook.GetLength();
        int index = 0;
        while(index < length)
        {
            Vocabulary vocabulary = this.wordBook.GetAt(index);
            Object row[] = {index + 1, vocabulary.GetWord(), vocabulary.GetPartOfSpeech(), vocabulary.GetMeaning(), vocabulary.GetExample()};
            model.addRow(row);
            
            index++;
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

        STATIC_NAME = new javax.swing.JLabel();
        STATIC_PARTOFSPEECH = new javax.swing.JLabel();
        STATIC_MEANING = new javax.swing.JLabel();
        STATIC_EXAMPLE = new javax.swing.JLabel();
        WORD = new javax.swing.JTextField();
        MEANING = new javax.swing.JTextField();
        EXAMPLE = new javax.swing.JTextField();
        RECORD = new javax.swing.JButton();
        FIND = new javax.swing.JButton();
        CORRECT = new javax.swing.JButton();
        ERASE = new javax.swing.JButton();
        ARRANGE = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        VOCABULARIES = new javax.swing.JTable();
        WordBook = new javax.swing.JLabel();
        COMBO_PARTOFSPEECH = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        STATIC_NAME.setText("단    어 : ");

        STATIC_PARTOFSPEECH.setText("품    사 : ");

        STATIC_MEANING.setText("의    미 : ");

        STATIC_EXAMPLE.setText("예    문 : ");

        RECORD.setText("기재하기");
        RECORD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RECORDMouseClicked(evt);
            }
        });

        FIND.setText("찾    기");
        FIND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FINDMouseClicked(evt);
            }
        });

        CORRECT.setText("고  치  기");
        CORRECT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CORRECTMouseClicked(evt);
            }
        });

        ERASE.setText("지  우  기");
        ERASE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ERASEMouseClicked(evt);
            }
        });

        ARRANGE.setText("정리하기");
        ARRANGE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ARRANGEMouseClicked(evt);
            }
        });

        VOCABULARIES.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "번호", "단어", "품사", "의미", "예문"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        VOCABULARIES.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VOCABULARIESMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(VOCABULARIES);
        if (VOCABULARIES.getColumnModel().getColumnCount() > 0) {
            VOCABULARIES.getColumnModel().getColumn(0).setMinWidth(30);
            VOCABULARIES.getColumnModel().getColumn(0).setMaxWidth(30);
            VOCABULARIES.getColumnModel().getColumn(1).setMinWidth(80);
            VOCABULARIES.getColumnModel().getColumn(1).setMaxWidth(80);
            VOCABULARIES.getColumnModel().getColumn(2).setMinWidth(80);
            VOCABULARIES.getColumnModel().getColumn(2).setMaxWidth(80);
            VOCABULARIES.getColumnModel().getColumn(3).setMinWidth(80);
            VOCABULARIES.getColumnModel().getColumn(3).setMaxWidth(80);
            VOCABULARIES.getColumnModel().getColumn(4).setResizable(false);
        }

        WordBook.setText("단어장");

        COMBO_PARTOFSPEECH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "명사", "동사", "형용사", "부사", "전치사", "접속사", "관사", "대명사", "감탄사" }));
        COMBO_PARTOFSPEECH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_PARTOFSPEECHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(STATIC_MEANING)
                                    .addGap(18, 18, 18)
                                    .addComponent(MEANING))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(STATIC_PARTOFSPEECH)
                                    .addGap(18, 18, 18)
                                    .addComponent(COMBO_PARTOFSPEECH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(STATIC_NAME)
                                    .addGap(18, 18, 18)
                                    .addComponent(WORD, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(STATIC_EXAMPLE)
                                .addGap(18, 18, 18)
                                .addComponent(EXAMPLE, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(WordBook))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(RECORD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FIND, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CORRECT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ERASE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ARRANGE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(WordBook)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(STATIC_NAME)
                            .addComponent(WORD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(STATIC_PARTOFSPEECH)
                            .addComponent(COMBO_PARTOFSPEECH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(STATIC_MEANING)
                            .addComponent(MEANING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(STATIC_EXAMPLE)
                            .addComponent(EXAMPLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RECORD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FIND)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CORRECT)
                        .addGap(3, 3, 3)
                        .addComponent(ERASE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ARRANGE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //2. 기재하기 버튼을 클릭했을때
    private void RECORDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RECORDMouseClicked
        try {
            // TODO add your handling code here:
            DefaultTableModel model = (DefaultTableModel)this.VOCABULARIES.getModel();
            
            //2.1 단어, 품사, 의미, 예문을 읽는다.
            String word = this.WORD.getText();
            String partOfSpeech = (String)this.COMBO_PARTOFSPEECH.getSelectedItem();
            String meaning = this.MEANING.getText();
            String example = this.EXAMPLE.getText();
            
            //2.2 단어장에 기재한다.
            int index = this.wordBook.Record(word, partOfSpeech, meaning, example);
            System.out.println(index);
            Vocabulary vocabulary = this.wordBook.GetAt(index);
            
            
            //2.3 데이터 베이스에 삽입힌다.
            this.Insert(index);
            
            //2.4 리스트뷰에 해당항복을 추가한다.
            Object row[] ={index + 1, vocabulary.GetWord(), vocabulary.GetPartOfSpeech(), vocabulary.GetMeaning(),vocabulary.GetExample()};
            model.addRow(row);
        } catch (SQLException ex) {
            Logger.getLogger(UpdatingForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_RECORDMouseClicked

    //3. 찾기 버튼을 클릭했을때
    private void FINDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FINDMouseClicked
        // TODO add your handling code here:
        FindingForm findingForm = new FindingForm(new javax.swing.JFrame(), true, this);
        findingForm.setVisible(true);
    }//GEN-LAST:event_FINDMouseClicked

    //4. 고치기 버튼을 클릭했을때
    private void CORRECTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CORRECTMouseClicked
        try {
            // TODO add your handling code here:
            
            //4.1 단어장을 읽는다.
            String partOfSpeech = (String)this.COMBO_PARTOFSPEECH.getSelectedItem();
            String meaning = this.MEANING.getText();
            String example = this.EXAMPLE.getText();
            
            //4.2 리스트뷰의 선택된 위치를 읽는다.
            int index = this.VOCABULARIES.getSelectedRow();
            
            //4.3 단어장을 고친다.
            index = this.wordBook.Correct(index, partOfSpeech, meaning, example);
            Vocabulary vocabulary = this.wordBook.GetAt(index);
            
            
            //4.4 데이터 베이스에서 갱신한다.
            this.Update(index);
            
            //4.5 리스트뷰에서 항목을 고쳐적는다.
            this.VOCABULARIES.setValueAt(vocabulary.GetPartOfSpeech(), index, 2);
            this.VOCABULARIES.setValueAt(vocabulary.GetMeaning(), index, 3);
            this.VOCABULARIES.setValueAt(vocabulary.GetExample(), index, 4);
        } catch (SQLException ex) {
            Logger.getLogger(UpdatingForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_CORRECTMouseClicked

    //5. 지우기 버튼을 클릭했을 때
    private void ERASEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ERASEMouseClicked
        try {
            // TODO add your handling code here:
            //5.1 리스트뷰에 선택된 위치를 읽는다.
            int lvIndex = this.VOCABULARIES.getSelectedRow();
            
            //5.2 단어장에서 지운다.
            int index = this.wordBook.Erase(lvIndex);
            
            
            //5.3 데이터 베이스에서 지운다.
            this.Delete(lvIndex);
            DefaultTableModel model = (DefaultTableModel)this.VOCABULARIES.getModel();
            
            int i = this.wordBook.GetLength();
            while(i >= 0){
                model.removeRow(i);
                i--;
            }
            int length = this.wordBook.GetLength();
            index = 0;
            while(index < length){
                Vocabulary vocabulary = this.wordBook.GetAt(index);
                Object row[] = {index + 1,vocabulary.GetWord(),vocabulary.GetPartOfSpeech(),vocabulary.GetMeaning(),vocabulary.GetExample()};
                model.addRow(row);
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdatingForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ERASEMouseClicked

    //6. 정리하기 버튼을 클릭했을때
    private void ARRANGEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ARRANGEMouseClicked
        // TODO add your handling code here:
        //6.1 단어장을 정리한다.
        this.wordBook.Arrange();
        
        //6.2 리스트뷰의 모든 항목을 지운다.
        DefaultTableModel model = (DefaultTableModel)this.VOCABULARIES.getModel();
        int i = this.wordBook.GetLength();
        i = i-1;
        while(i >= 0){
            model.removeRow(i);
            i--;
        }
        
        //6.3 리스트 뷰에 주소록을 추가한다.
        int length = this.wordBook.GetLength();
        int index = 0;
        while(index < length)
        {
            Vocabulary vocabulary = this.wordBook.GetAt(index);
            Object row[] = {index+1,vocabulary.GetWord(),vocabulary.GetPartOfSpeech(),vocabulary.GetMeaning(),vocabulary.GetExample()};
            model.addRow(row);
            index++;
        }
    }//GEN-LAST:event_ARRANGEMouseClicked

    //7. 리스트뷰의 항목을 더블클릭했을 때
    private void VOCABULARIESMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VOCABULARIESMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
            //7.1 리스트 뷰에 선택된 위치를 읽는다.
            int lvIndex = this.VOCABULARIES.getSelectedRow();
            
            //7.2 리스트 뷰의 선택된 항목을 읽는다.
            String word = (String)this.VOCABULARIES.getValueAt(lvIndex, 1);
            String partOfSpeech = (String)this.VOCABULARIES.getValueAt(lvIndex, 2);
            String meaning = (String)this.VOCABULARIES.getValueAt(lvIndex, 3);
            String example = (String)this.VOCABULARIES.getValueAt(lvIndex, 4);
            
            //7.3 개인에 읽은 항목을 적는다.
            this.WORD.setText(word);
            this.COMBO_PARTOFSPEECH.setSelectedItem(partOfSpeech);
            this.MEANING.setText(meaning);
            this.EXAMPLE.setText(example);
        }
    }//GEN-LAST:event_VOCABULARIESMouseClicked

    private void COMBO_PARTOFSPEECHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_PARTOFSPEECHActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_COMBO_PARTOFSPEECHActionPerformed

    
    
    
    
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
            java.util.logging.Logger.getLogger(UpdatingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdatingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdatingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdatingForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdatingForm dialog = new UpdatingForm(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) { 
                            try {
                                dialog.Save();
                            } catch (SQLException ex) {
                                Logger.getLogger(UpdatingForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(UpdatingForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
             
     

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ARRANGE;
    public javax.swing.JComboBox<String> COMBO_PARTOFSPEECH;
    private javax.swing.JButton CORRECT;
    private javax.swing.JButton ERASE;
    public javax.swing.JTextField EXAMPLE;
    private javax.swing.JButton FIND;
    public javax.swing.JTextField MEANING;
    private javax.swing.JButton RECORD;
    private javax.swing.JLabel STATIC_EXAMPLE;
    private javax.swing.JLabel STATIC_MEANING;
    private javax.swing.JLabel STATIC_NAME;
    private javax.swing.JLabel STATIC_PARTOFSPEECH;
    public javax.swing.JTable VOCABULARIES;
    public javax.swing.JTextField WORD;
    private javax.swing.JLabel WordBook;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

  
    }
