package GeneralGUI;

import java.util.Date;

import com.univpm.oop.log.Log;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class StartingFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public StartingFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Label1 = new javax.swing.JLabel();
        Label2 = new javax.swing.JLabel();
        SingleCityJoinButton = new javax.swing.JButton();
        MultiCityJoinButton = new javax.swing.JButton();
        Label4 = new javax.swing.JLabel();
        GeoZoneCityJoinButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Label1.setText("Meteo per Città Singola");

        Label2.setText("Meteo per Città Multiple");

        SingleCityJoinButton.setText("Entra");
        SingleCityJoinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SingleCityJoinButtonMouseClicked(evt);
            }
        });

        MultiCityJoinButton.setText("Entra");
        MultiCityJoinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MultiCityJoinButtonMouseClicked(evt);
            }
        });

        Label4.setText("Record delle Città");

        GeoZoneCityJoinButton1.setText("Entra");
        GeoZoneCityJoinButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GeoZoneCityJoinButton1MouseClicked(evt);
            }
        });
        GeoZoneCityJoinButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GeoZoneCityJoinButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(137, 137, 137)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Label4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SingleCityJoinButton)
                    .addComponent(MultiCityJoinButton)
                    .addComponent(GeoZoneCityJoinButton1))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SingleCityJoinButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MultiCityJoinButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Label4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GeoZoneCityJoinButton1))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void SingleCityJoinButtonMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        // TODO add your handling code here:
        
        new SingleCity().setVisible(true);
        dispose();
    }                                                 

    private void MultiCityJoinButtonMouseClicked(java.awt.event.MouseEvent evt) {                                                 
        // TODO add your handling code here:
       
        new MultiCity().setVisible(true);
        dispose();
    }                                                

    private void GeoZoneCityJoinButton1MouseClicked(java.awt.event.MouseEvent evt) {                                                    
        // TODO add your handling code here:
        new MinMax().setVisible(true);
        dispose();
    }                                                   

    private void GeoZoneCityJoinButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                      

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
            java.util.logging.Logger.getLogger(StartingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartingFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton GeoZoneCityJoinButton1;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label4;
    private javax.swing.JButton MultiCityJoinButton;
    private javax.swing.JButton SingleCityJoinButton;
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration                   

    
}
