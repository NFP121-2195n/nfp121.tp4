package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        
        add(donnee);
        
        donnee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    int donnee = operande();
                }catch(NumberFormatException nfe){
                    donnee.setText("");
                    JOptionPane.showMessageDialog(null, donnee.getText()+" n'est pas un nombre entier");
                }
            }
        });
        
        
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        
        
        boutons.add(push);  push.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
             try{
                    pile.empiler(operande());
                }catch(Exception e1){}
             actualiserInterface();
            }
        }
        );
        boutons.add(add);   add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    pile.empiler(pile.depiler() + pile.depiler());
                }catch(Exception e2){}
                actualiserInterface();
            }
        }
        );
        boutons.add(sub);   sub.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    int sommet = pile.sommet();
                    pile.depiler();
                    pile.empiler(pile.depiler() - sommet);
                }catch (Exception e3){}
                actualiserInterface();
            }
        }
        );
        boutons.add(mul);   mul.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    pile.empiler(pile.depiler() * pile.depiler());
                }catch(Exception e4){}
                actualiserInterface();
            }
        }
        );
        boutons.add(div);   div.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    int res = divisionParZero();
                    actualiserInterface();
                }catch(Exception e5){}
            }
        }
        );
        boutons.add(clear); clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                donnee.setText("");
                for(int i=0; i<pile.taille(); i++){
                    try{
                        pile.depiler();
                    }catch(Exception e6){}
                }
                actualiserInterface();
            }
        }
        );
        
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
       if(pile.estVide()){
          add.setEnabled(false);
          sub.setEnabled(false);
          mul.setEnabled(false);
          div.setEnabled(false);
          clear.setEnabled(false);
          push.setEnabled(true);
       }
       else if(pile.taille()== 1){
          add.setEnabled(false);
          sub.setEnabled(false);
          mul.setEnabled(false);
          div.setEnabled(false);
          clear.setEnabled(true);
          push.setEnabled(true);
       }
       else if(pile.taille()> 1){
          add.setEnabled(true);
          sub.setEnabled(true);
          mul.setEnabled(true);
          div.setEnabled(true);
          clear.setEnabled(true);
          push.setEnabled(true);
       }
       else if(pile.estPleine()) {
          push.setEnabled(false);
          add.setEnabled(true);
          sub.setEnabled(true);
          mul.setEnabled(true);
          div.setEnabled(true);
          clear.setEnabled(true);
       }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    private Integer divisionParZero() throws ArithmeticException {
        int res = 0;
        try{
            int sommet = this.pile.sommet();
            if(sommet == 0){
                JOptionPane.showMessageDialog(null, "Cannot divide by 0");
                return null;
            }
            this.pile.depiler();
            res = (Integer)this.pile.depiler()/sommet;
            this.pile.empiler(res);
        }catch(Exception e){}
        return res;
    }
    
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)

}
