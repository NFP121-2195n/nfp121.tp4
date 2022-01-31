package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IHMQuestion2_2 extends JFrame {

    private JButton boutonA = new JButton("A");
    private JButton boutonB = new JButton("B");
    private JButton boutonC = new JButton("C");

    private TextArea contenu = new TextArea(30, 80);

    private JMouseObserver jmo1 = new JMouseObserver("jmo1", contenu);
    private JMouseObserver jmo2 = new JMouseObserver("jmo2", contenu);
    private JMouseObserver jmo3 = new JMouseObserver("jmo3", contenu);
    
    private JButtonObserver jbo1 = new JButtonObserver("jbo1", contenu);
    private JButtonObserver jbo2 = new JButtonObserver("jbo2", contenu);
    private JButtonObserver jbo3 = new JButtonObserver("jbo3", contenu);
 
    public IHMQuestion2_2() {
        super("IHM Question2_2");
        JPanel enHaut = new JPanel();
        enHaut.add(boutonA);
        enHaut.add(boutonB);
        enHaut.add(boutonC);
        setLayout(new BorderLayout(5, 5));
        add("North", enHaut);
        add("Center", contenu); // contenu sera transmis aux observateurs, voir
                                // la description des constructeurs
        setLocation(150,150);pack();show();
        enHaut.setBackground(Color.magenta);
        

                
        // le bouton A a 1 observateur jmo1
        boutonA.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){}
            public void mouseEntered(MouseEvent e){
                contenu.setText("");
                jmo1.mouseEntered(e);
            }
            public void mouseExited(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        });

        // le bouton A a 3 observateurs jbo1, jbo2 et jbo3        
        boutonA.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jbo3.actionPerformed(e);
                jbo2.actionPerformed(e);
                jbo1.actionPerformed(e);
            }
        });

        
        
        // le bouton B a 1 observateur jmo2
        boutonB.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){}
            public void mouseEntered(MouseEvent e){
                contenu.setText("");
                jmo2.mouseEntered(e);
            }
            public void mouseExited(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        });
                
        // le bouton B a 2 observateurs jbo1 et jbo2
        boutonB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jbo2.actionPerformed(e);
                jbo1.actionPerformed(e);
            }
        });

        
        // le bouton C a 1 observateur jmo3
        boutonC.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e){}
            public void mouseEntered(MouseEvent e){
                contenu.setText("");
                jmo3.mouseEntered(e);
            }
            public void mouseExited(MouseEvent e){}
            public void mousePressed(MouseEvent e){}
            public void mouseReleased(MouseEvent e){}
        });

        // le bouton C a 1 observateur jbo1        
        boutonC.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                jbo1.actionPerformed(e);
            }
        });

        
    }
    
     public static void main(String[] args){
        new IHMQuestion2_1();
        new IHMQuestion2_2();
    }


}
