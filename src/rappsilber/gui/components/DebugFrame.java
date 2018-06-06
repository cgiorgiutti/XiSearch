/* 
 * Copyright 2016 Lutz Fischer <l.fischer@ed.ac.uk>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rappsilber.gui.components;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import rappsilber.gui.logging.JTextAreaHandle;
import rappsilber.ui.StatusInterface;
import rappsilber.utils.Util;
import rappsilber.utils.XiVersion;

/**
 *
 * @author Lutz Fischer <l.fischer@ed.ac.uk>
 */
public class DebugFrame extends javax.swing.JFrame implements StatusInterface{

    private JTextAreaHandle loggingHandle;
    /**
     * Creates new form DebugFrame
     */
    public DebugFrame(String titel) {
        initComponents();
        loggingHandle = new JTextAreaHandle(txtOut);
        loggingHandle.setFilter(new Filter() {

            public boolean isLoggable(LogRecord record) {
                return true;
            }
        });


        loggingHandle.setLevel(Level.ALL);
        //Logger.getLogger("rappsilber").addHandler(loggingHandle);
        Logger.getLogger("").addHandler(loggingHandle);
        this.setTitle(titel);
         
    }
    
    @Override
    public void setTitle(String titel) {
        super.setTitle("Xi " + XiVersion.getVersionString() + " - " +  titel);
    }
    
    
    
    protected void getStackTraces() {
        txtOut.append(Util.getStackTraces().toString());
    }
    
    protected void doGC() {
        StringBuffer sb = new StringBuffer();
        double freemem = Runtime.getRuntime().freeMemory();
        double maxmem = Runtime.getRuntime().maxMemory();
        double totalmem = Runtime.getRuntime().totalMemory();
        sb.append("===================\n");
        sb.append("======= GC ========\n");
        sb.append("===================\n");
        sb.append("free mem" + Util.doubleToString(freemem) + "\n");
        sb.append("max mem" + Util.doubleToString(maxmem) + "\n");
        sb.append("total mem" + Util.doubleToString(totalmem) + "\n");
        sb.append("do gc\n");
        txtOut.append(sb.toString());
        for (int i=10;i>0; i--) {
            System.gc();
        }
        sb = new StringBuffer();
        freemem = Runtime.getRuntime().freeMemory();
        maxmem = Runtime.getRuntime().maxMemory();
        totalmem = Runtime.getRuntime().totalMemory();
        sb.append("free mem" + Util.doubleToString(freemem) + "\n");
        sb.append("max mem" + Util.doubleToString(maxmem) + "\n");
        sb.append("total mem" + Util.doubleToString(totalmem) + "\n");
        sb.append("====================\n");
        txtOut.append(sb.toString());
        
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
        txtOut = new javax.swing.JTextArea();
        btnStack = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        memory1 = new rappsilber.gui.components.memory.Memory();
        txtStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtOut.setColumns(20);
        txtOut.setRows(5);
        jScrollPane1.setViewportView(txtOut);

        btnStack.setText("StackTraces");
        btnStack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStackActionPerformed(evt);
            }
        });

        jButton1.setText("exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtStatus.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStack))
                    .addComponent(memory1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStack)
                    .addComponent(jButton1)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(memory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStackActionPerformed
        getStackTraces();
    }//GEN-LAST:event_btnStackActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        System.exit(0);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStack;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private rappsilber.gui.components.memory.Memory memory1;
    private javax.swing.JTextArea txtOut;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables

    public void setStatus(String status) {
        txtStatus.setText(status);
    }

    public String getStatus() {
        return txtStatus.getText();
    }
}
