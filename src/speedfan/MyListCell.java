/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedfan;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Nathan
 */
public class MyListCell extends DefaultListCellRenderer {
    
    public MyListCell(){
        
    }
    
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setText(value.toString());
        
        if(index % 2 == 0){
           c.setBackground(new Color(153,204,255));
        }
        else{
            c.setBackground(Color.WHITE);
        }
        
        return c;
    }
    
}
