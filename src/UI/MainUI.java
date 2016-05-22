/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Configuration.Configuration;
import SharedFolders.SharedFolder;
import SharedFolders.SharedFoldersManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import servertest.ServerManager;

/**
 *
 * @author Filipe
 */
public class MainUI extends javax.swing.JFrame implements Observer {

    private int xPos, yPos;
    private Dimension screenSize;
    private final ServerManager serverManager;

    /**
     * Creates new form ServerGUI
     *
     * @param serverManager
     */
    public MainUI(ServerManager serverManager) {
        this.serverManager = serverManager;
        observe(serverManager.getSFManager());
    }

    public void run() {
        initComponents();
        editComponents();
    }

    public void observe(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        jTextAreaServidoresAtivos.setText("");
        String activeServers = "";
        List<SharedFolder> sfManager = this.serverManager.getSFManager().getlistSharedFolders();
        if (!sfManager.isEmpty()) {
            for (SharedFolder sf : sfManager) {
                activeServers += sf.getMachineName();
                activeServers += "\r\n";
            
                System.out.println("teste");}
            System.out.println("teste2");
        }
        
//   
//        System.out.println("All is flux!  Some variable is now "+activeServers);  
        jTextAreaServidoresAtivos.append("teste \n teste");
    }

//    public void definirFonte(FontUIResource f, ColorUIResource color) {
//        try {
//            for (UIManager.LookAndFeelInfo info
//                    : UIManager.getInstalledLookAndFeels()) {
//
//                if ("Windows".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//        }
//
//        Enumeration objectos = UIManager.getDefaults().keys();
//        while (objectos.hasMoreElements()) {
//            Object key = objectos.nextElement();
//            Object value = UIManager.get(key);
//
//            if (value instanceof FontUIResource) {
//                FontUIResource orig = (FontUIResource) value;
//                Font fonte = new Font(f.getFontName(), orig.getStyle(), f.getSize());
//                UIManager.put(key, new FontUIResource(fonte));
//            }
//
//            if (value instanceof ColorUIResource) {
//                if (key.toString().contains("Label") && key.toString().contains("foreground")) {
//                    ColorUIResource orig1 = (ColorUIResource) value;
//                    Color cor = new Color(color.getRed(), color.getGreen(), color.getBlue());
//                    UIManager.put(key, new ColorUIResource(cor));
//                }
//            }
//        }
//    }

    private void editComponents() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xPos = (int) (screenSize.getWidth() / 2) - (this.getWidth() / 2);
        yPos = (int) (screenSize.getHeight() / 2) - (this.getHeight() / 2);

////        definirFonte(new FontUIResource(new Font("Arial", 0, 11)), new ColorUIResource(new Color(255, 255, 255)));

        this.setLayout(new BorderLayout());
        this.setSize(this.getWidth(), this.getHeight());
        this.setLocation(xPos, yPos);

        this.setResizable(false);
        // this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon1.png"));
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
        this.setVisible(true);
        
        jTextAreaServidoresAtivos.setEditable(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonIniciarServidor = new javax.swing.JButton();
        jButtonTerminarServidor = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaServidoresAtivos = new javax.swing.JTextArea();
        jButtonAbrirPagina = new javax.swing.JButton();
        jButtonConfiguracao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonIniciarServidor.setBackground(new java.awt.Color(153, 204, 0));
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

        jButtonAbrirPagina.setBackground(new java.awt.Color(0, 51, 204));
        jButtonAbrirPagina.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAbrirPagina.setText("Abrir Página HTML");
        jButtonAbrirPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirPaginaActionPerformed(evt);
            }
        });

        jButtonConfiguracao.setText("Configuração");
        jButtonConfiguracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfiguracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonIniciarServidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                        .addComponent(jButtonAbrirPagina, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonTerminarServidor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jButtonConfiguracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonIniciarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAbrirPagina)
                        .addGap(223, 223, 223)
                        .addComponent(jButtonTerminarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConfiguracao)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarServidorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonIniciarServidorMousePressed
        boolean resposta = this.serverManager.startServer();
        if (resposta) {
            JOptionPane.showMessageDialog(this,
                    "Servidor iniciado com sucesso!!", "RCOMP 2016", 1);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Servidor já se encontra ligado !!",
                    "RCOMP 2016", 0);
        }

    }//GEN-LAST:event_jButtonIniciarServidorMousePressed

    private void jButtonTerminarServidorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTerminarServidorMousePressed
        boolean resposta = this.serverManager.stopServer();
        if (resposta) {
            JOptionPane.showMessageDialog(this,
                    "Servidor encerrado com sucesso!!", "RCOMP 2016", 1);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Servidor já se encontra desligado !!",
                    "RCOMP 2016", 0);
        }
    }//GEN-LAST:event_jButtonTerminarServidorMousePressed

    private void jButtonTerminarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarServidorActionPerformed


    }//GEN-LAST:event_jButtonTerminarServidorActionPerformed

    private void jButtonAbrirPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirPaginaActionPerformed
        try {

            URI uri = new URI("http://localhost:" + Configuration.getTCP_Port() + "/index.html");
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);

        } catch (URISyntaxException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAbrirPaginaActionPerformed

    private void jButtonConfiguracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfiguracaoActionPerformed
        ConfigurationUI configurationUI = new ConfigurationUI();
        configurationUI.run();

    }//GEN-LAST:event_jButtonConfiguracaoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrirPagina;
    private javax.swing.JButton jButtonConfiguracao;
    private javax.swing.JButton jButtonIniciarServidor;
    private javax.swing.JButton jButtonTerminarServidor;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaServidoresAtivos;
    // End of variables declaration//GEN-END:variables

}
