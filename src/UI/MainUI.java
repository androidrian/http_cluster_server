/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import servertest.Main;
import servertest.Main;
import servertest.Main;

/**
 *
 * @author Filipe
 */
public class MainUI extends javax.swing.JFrame {
 private int xPos, yPos;
    private Dimension screenSize;
    /**
     * Creates new form ServerGUI
     */
    public MainUI() {
        initComponents();
        editComponents();
        
    }
    
    public void definirFonte(FontUIResource f, ColorUIResource color) {
        try {
            for (UIManager.LookAndFeelInfo info
                    : UIManager.getInstalledLookAndFeels()) {

                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Enumeration objectos = UIManager.getDefaults().keys();
        while (objectos.hasMoreElements()) {
            Object key = objectos.nextElement();
            Object value = UIManager.get(key);

            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font fonte = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(fonte));
            }

            if (value instanceof ColorUIResource) {
                if (key.toString().contains("Label") && key.toString().contains("foreground")) {
                    ColorUIResource orig1 = (ColorUIResource) value;
                    Color cor = new Color(color.getRed(), color.getGreen(), color.getBlue());
                    UIManager.put(key, new ColorUIResource(cor));
                }
            }
        }
    }
    private void editComponents() {

//         try {
//            BufferedImage fundo = ImageIO.read(new File("img/fundo.png"));
//            this.setContentPane(new Imagem(fundo));
//        } catch (IOException ie) {
//        }
//    
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xPos = (int) (screenSize.getWidth() / 2) - (this.getWidth() / 2);
        yPos = (int) (screenSize.getHeight() / 2) - (this.getHeight() / 2);

        definirFonte(new FontUIResource(new Font("Arial", 0, 11)), new ColorUIResource(new Color(255, 255, 255)));

        this.setLayout(new BorderLayout());
        this.setSize(this.getWidth(), this.getHeight());
        this.setLocation(xPos, yPos);

        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
       // this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon1.png"));
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
        this.setVisible(true);

   
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonIniciarServidor = new javax.swing.JButton();
        jButtonTerminarServidor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaServidoresAtivos = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaConsola = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonAbrirPagina = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonIniciarServidor.setBackground(new java.awt.Color(0, 255, 0));
        jButtonIniciarServidor.setText("Iniciar Servidor");
        jButtonIniciarServidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonIniciarServidorMousePressed(evt);
            }
        });

        jButtonTerminarServidor.setBackground(new java.awt.Color(255, 51, 51));
        jButtonTerminarServidor.setText("Terminar Servidor");
        jButtonTerminarServidor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonTerminarServidorMousePressed(evt);
            }
        });
        jButtonTerminarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarServidorActionPerformed(evt);
            }
        });

        jTextAreaServidoresAtivos.setColumns(20);
        jTextAreaServidoresAtivos.setRows(5);
        jTextAreaServidoresAtivos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Servidores Ativos"));
        jScrollPane2.setViewportView(jTextAreaServidoresAtivos);

        jTextAreaConsola.setColumns(20);
        jTextAreaConsola.setRows(5);
        jTextAreaConsola.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Consola"));
        jScrollPane1.setViewportView(jTextAreaConsola);

        jButtonAbrirPagina.setBackground(new java.awt.Color(0, 51, 204));
        jButtonAbrirPagina.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAbrirPagina.setText("Abrir Página HTML");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonTerminarServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIniciarServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAbrirPagina, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonIniciarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTerminarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(jButtonAbrirPagina))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarServidorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonIniciarServidorMousePressed
        try {
            new Main().runServer();
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonIniciarServidorMousePressed

    private void jButtonTerminarServidorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTerminarServidorMousePressed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jButtonTerminarServidorMousePressed

    private void jButtonTerminarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarServidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTerminarServidorActionPerformed

    private void LogTextArea(String str){
        jTextAreaConsola.append(str);
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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
     

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrirPagina;
    private javax.swing.JButton jButtonIniciarServidor;
    private javax.swing.JButton jButtonTerminarServidor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaConsola;
    private javax.swing.JTextArea jTextAreaServidoresAtivos;
    // End of variables declaration//GEN-END:variables
}
