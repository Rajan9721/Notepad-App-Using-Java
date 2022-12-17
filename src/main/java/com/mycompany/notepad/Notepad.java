/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.notepad;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author rajan
 */
public class Notepad extends JFrame implements ActionListener, WindowListener{
    
    JTextArea jta = new JTextArea();
    File fnameContainer;
    
    public Notepad(){
        Font fnt = new Font("Arial", Font.PLAIN, 15);
        Container con = getContentPane();
        JMenuBar jmb = new JMenuBar();
        JMenu jmfile = new JMenu("File");
        JMenu jmedit = new JMenu("Edit");
        JMenu jmhelp = new JMenu("Help");
        
        con.setLayout(new BorderLayout());
        JScrollPane sbrText = new JScrollPane(jta);
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sbrText.setVisible(true);
        
        jta.setFont(fnt);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        
        con.add(sbrText);
        
        createMenuItem(jmfile,"New");
        createMenuItem(jmfile,"Open");
        createMenuItem(jmfile,"Save");
        jmfile.addSeparator();
        createMenuItem(jmfile,"Exit");
        
        createMenuItem(jmedit,"Cut");
        createMenuItem(jmedit,"Copy");
        createMenuItem(jmedit,"Paste");
        
        createMenuItem(jmhelp,"About Notepad");
        
        jmb.add(jmfile);
        jmb.add(jmedit);
        jmb.add(jmhelp);
        
        setJMenuBar(jmb);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
        addWindowListener(this);
        setSize(500,500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);
           
    }
    public void createMenuItem(JMenu jm, String txt){
        JMenuItem jmi = new JMenuItem(txt);
        jmi.addActionListener(this);
        jm.add(jmi);
        
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser jfc = new JFileChooser();
        if(e.getActionCommand().equals("New")){
            this.setTitle("Untitled.txt-Notepad");
            jta.setText("");
            fnameContainer = null;
        }
        else if(e.getActionCommand().equals("Open")){
            int ret = jfc.showDialog(null, "Open");
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    File fy1 = jfc.getSelectedFile();
                    OpenFile(fy1.getAbsolutePath());
                    this.setTitle(fy1.getName()+" - Notepad");
                    fnameContainer = fy1;
                }
                catch(IOException ers){
                    
                }
            }
        }
        else if(e.getActionCommand().equals("Save")){
            if(fnameContainer!=null){
                jfc.setCurrentDirectory(fnameContainer);
                jfc.setSelectedFile(fnameContainer);
            }
            else{
                jfc.setSelectedFile(new File("Untitled.txt"));
            }
            
            int ret = jfc.showSaveDialog(null);
            if(ret == JFileChooser.APPROVE_OPTION){
                try{
                    File fy1 = jfc.getSelectedFile();
                    SaveFile(fy1.getAbsolutePath());
                    this.setTitle(fy1.getName()+" - Notepad");
                    fnameContainer = fy1;
                }
                catch(Exception ets){}
            }
        }
        else if(e.getActionCommand().equals("Exit")){
            Exiting();
        }
        else if(e.getActionCommand().equals("Copy")){
            jta.copy();
        }
        else if(e.getActionCommand().equals("Paste")){
            jta.paste();
        }
        else if(e.getActionCommand().equals("About Notepad")){
            JOptionPane.showMessageDialog(this,"Created by : Rajan Rajbhar", "Notepad",JOptionPane.INFORMATION_MESSAGE);
            
        }
        else if(e.getActionCommand().equals("Cut")){
            jta.cut();
        }
        else {
            this.setTitle("Untiled.txt - Notepad");
            jta.setText("");
            fnameContainer = null;
        }
        

}
public void OpenFile(String fname) throws IOException{
    BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
    String l;
    jta.setText("");
    setCursor(new Cursor(Cursor.WAIT_CURSOR));
    while((l=d.readLine())!=null){
        jta.setText(jta.getText()+l+"\r\n");
    }
    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    d.close();
}
public void SaveFile(String fname) throws IOException{
    setCursor(new Cursor(Cursor.WAIT_CURSOR)); 
    DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
    o.writeBytes(jta.getText());
    o.close();
    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
}
    @Override
    public void windowDeactivated(WindowEvent e){
    
}
    @Override
    public void windowActivated(WindowEvent e){
    
}
    public void windowDeiconified(WindowEvent e){
    
}
    public void windowIconified(WindowEvent e){
        
    }
    public void windowClosed(WindowEvent e){
        
    }
    public void windowClosing(WindowEvent e){
        Exiting();
    }
    public void windowOpened(WindowEvent e){
        
    }
    public void Exiting(){
            System.exit(0);
    }
}
    