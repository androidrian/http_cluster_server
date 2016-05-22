package UI;

import Configuration.Configuration;
import Controllers.ConfigurationController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import Controllers.ServerController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainUI extends javax.swing.JFrame {

    private int xPos, yPos;
    private Dimension screenSize;
    private final ServerController serverManager;

    public MainUI(ServerController serverManager) {
        this.serverManager = serverManager;

    }

    public void run() {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
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
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        xPos = (int) (screenSize.getWidth() / 2) - (this.getWidth() / 2);
        yPos = (int) (screenSize.getHeight() / 2) - (this.getHeight() / 2);

        definirFonte(new FontUIResource(new Font("Arial", 0, 11)), new ColorUIResource(new Color(255, 255, 255)));

        this.setLayout(new BorderLayout());
        this.setSize(this.getWidth(), this.getHeight());
        this.setLocation(xPos, yPos);

        this.setResizable(false);
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.black));
        this.setVisible(true);

        jTextFieldUDP.setText("" + Configuration.getUDP_Port());
        jTextFieldTCP.setText("" + Configuration.getTCP_Port());
        jTextFieldFolder.setText(Configuration.getFilesLocation());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonIniciarServidor = new javax.swing.JButton();
        jButtonTerminarServidor = new javax.swing.JButton();
        jButtonAbrirPagina = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonGuardar = new javax.swing.JButton();
        jTextFieldUDP = new javax.swing.JTextField();
        jTextFieldTCP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldFolder = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonIniciarServidor.setText("Iniciar Servidor");
        jButtonIniciarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarServidorActionPerformed(evt);
            }
        });

        jButtonTerminarServidor.setText("Terminar Servidor");
        jButtonTerminarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTerminarServidorActionPerformed(evt);
            }
        });

        jButtonAbrirPagina.setText("Abrir Página HTML");
        jButtonAbrirPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirPaginaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Configuração"));

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jLabel1.setText("UDP Port");

        jLabel2.setText("TCP Port");

        jLabel3.setText("Pasta Partilhada");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldFolder)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUDP)
                            .addComponent(jTextFieldTCP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 32, Short.MAX_VALUE)
                                .addComponent(jButtonGuardar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUDP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonGuardar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonIniciarServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAbrirPagina, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(jButtonTerminarServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonIniciarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAbrirPagina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonTerminarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTerminarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTerminarServidorActionPerformed
        boolean resposta = this.serverManager.stopServer();
        if (resposta) {
            JOptionPane.showMessageDialog(this,
                    "Servidor encerrado com sucesso!!", "RCOMP 2016", 1);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Servidor já se encontra desligado !!",
                    "RCOMP 2016", 0);
        }

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

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        ConfigurationController controller = new ConfigurationController();

        String tcpPortText = jTextFieldTCP.getText();

        try {
            Integer.parseInt(tcpPortText);

            String udpPortText = jTextFieldUDP.getText();

            try {
                Integer.parseInt(udpPortText);

                String userName = new com.sun.security.auth.module.NTSystem().getName();
                String folderLocation = jTextFieldFolder.getText().replaceAll("@utilizador", userName);
            
                File file = new File(folderLocation);

                if (file.exists()) {
                    controller.setUDPPort(udpPortText);
                    controller.setTCPPort(tcpPortText);
                    controller.setFiles_Location(jTextFieldFolder.getText());

                    JOptionPane.showMessageDialog(this,
                            "Configuração guardada com sucesso!!\nDeverá reiniciar o servidor para a aplicar a nova configuração.", "RCOMP 2016", 1);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Localização da pasta não encontrada !!",
                            "RCOMP 2016", 0);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Porta UDP é inválida !!",
                        "RCOMP 2016", 0);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Porta TCP é inválida !!",
                    "RCOMP 2016", 0);
        }


    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonIniciarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarServidorActionPerformed
        boolean resposta = this.serverManager.startServer();
        if (resposta) {
            JOptionPane.showMessageDialog(this,
                    "Servidor iniciado com sucesso!!", "RCOMP 2016", 1);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Servidor já se encontra ligado !!",
                    "RCOMP 2016", 0);
        }
    }//GEN-LAST:event_jButtonIniciarServidorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAbrirPagina;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonIniciarServidor;
    private javax.swing.JButton jButtonTerminarServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldFolder;
    private javax.swing.JTextField jTextFieldTCP;
    private javax.swing.JTextField jTextFieldUDP;
    // End of variables declaration//GEN-END:variables

}
