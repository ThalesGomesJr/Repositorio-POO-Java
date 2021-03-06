package herança.project1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.ArrayList;

//Classe Tela grafica
public class TelaGraficaHeranca extends JFrame{
	private static final long serialVersionUID = -6061103332L;
	
	//Declarando os botoes
	JButton btInsere, btExibe;
    JRadioButton rdPessoa, rdEstudante;
    ButtonGroup grupo;
    JLabel lnome,ldia,lmes,lano;
    JTextField tnome,tdia,tmes,tano;
    JLabel lmatricula, lnota1, lnota2;
    JTextField tmatricula, tnota1,tnota2;
    JPanel painelPessoa;
    JPanel painelEstudante ;
    List <Pessoa> lista = new ArrayList<Pessoa>();
    
    //Construtror
    public TelaGraficaHeranca(){
        super("Herança Pessoa e Estudante");
        setLayout(new BorderLayout());
        //Sul
        btInsere = new JButton("Insere");
        btExibe = new JButton("Exibe");
        JPanel sul= new JPanel(new FlowLayout());
        sul.add(btInsere);
        sul.add(btExibe);
        add(sul,BorderLayout.SOUTH);
        //Norte
        rdPessoa = new JRadioButton("Pessoa");
        rdEstudante = new JRadioButton("Estudante");
        grupo = new ButtonGroup();
        grupo.add(rdPessoa);
        grupo.add(rdEstudante);
        JPanel norte= new JPanel(new FlowLayout());
        norte.add(rdPessoa);
        norte.add(rdEstudante);
        add(norte,BorderLayout.NORTH);
        
        //Centro
        painelPessoa = new JPanel(new GridLayout(3,2,5,5));
        painelPessoa.setVisible(false);
        painelEstudante = new JPanel(new GridLayout(3,2,5,5));
        JPanel painelCentro = new JPanel(new GridLayout(2,1,5,5));
        painelCentro.add(painelPessoa);
        painelCentro.add(painelEstudante);
        add(painelCentro,BorderLayout.CENTER);
        //Painel Pessoa
        lnome = new JLabel("Nome:");
        tnome = new JTextField();
        ldia = new JLabel("Dia:");
        tdia = new JTextField();
        lmes = new JLabel("Mês:");
        tmes = new JTextField();
        painelPessoa.add(lnome);
        painelPessoa.add(tnome);
        painelPessoa.add(ldia);
        painelPessoa.add(tdia);
        painelPessoa.add(lmes);
        painelPessoa.add(tmes);
        //Painel Estudante
        lmatricula = new JLabel("Matricula:");
        tmatricula = new JTextField();
        lnota1 = new JLabel("Nota 1:");
        tnota1 = new JTextField();
        lnota2 = new JLabel("Nota 2:");
        tnota2 = new JTextField();
        painelEstudante.add(lmatricula);
        painelEstudante.add(tmatricula);
        painelEstudante.add(lnota1);
        painelEstudante.add(tnota1);
        painelEstudante.add(lnota2);
        painelEstudante.add(tnota2);
        painelEstudante.setVisible(false);
        
        //Adcionando funcionalidade ao botão exibe
        btExibe.addActionListener(
          new ActionListener(){
              public void actionPerformed(ActionEvent e){
                String saida = "-----|Lista de Pessoa e Estudantes|-----";
                int i=0;
                //Percorrendo o array list para exibir
                for(Pessoa p:lista){
                    saida +="\nPessoa: "+i+p.toString()+"\n\n";
                    i++;
                    }
                //caixa de texo grande para exibir
                JTextArea area = new JTextArea(saida,11,10);
                JOptionPane.showMessageDialog(null,area,"Lista de Pessoas e Estudantes",JOptionPane.INFORMATION_MESSAGE);
                } 
            });
        
      //Adcionando funcionalidade ao botão insere
        btInsere.addActionListener(
          new ActionListener(){
              public void actionPerformed(ActionEvent e){
                int dia,mes;
                String nome = tnome.getText();
                String d= tdia.getText();
                String m = tmes.getText();
                
              //excessão de erro para transformar os tipos primitivos
                try{
                    dia= Integer.parseInt(d);
                    mes= Integer.parseInt(m);
                }catch(NumberFormatException erro){
                    dia=1;
                    mes=1;
                }
                int mat=1;
                float n1=0;
                float n2=0;
                
              //se o radio button Estudante estiver selecionado
                if(rdEstudante.isSelected()){
                    String smat= tmatricula.getText();
                    String sn1= tnota1.getText();
                    String sn2= tnota2.getText();
                    
                    //excessão de erro para transformar os tipos primitivos
                    try{
                        mat = Integer.parseInt(smat);
                        n1 = Float.parseFloat(sn1);
                        n2 = Float.parseFloat(sn2);
                    }catch(NumberFormatException erro){
                        mat=1;
                        n1=0;
                        n2=0; 
                    }
                }
              //se o radio button Pessoa estiver selecionado
                if(rdPessoa.isSelected()){ //cria pessoa com dados preencidos direto 
                    Pessoa p = new Pessoa(nome,dia,mes);
                    lista.add(p);
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");

                }
                //se o radio button Estudante estiver selecionado
                if(rdEstudante.isSelected()){
                    Estudante x= new Estudante(nome,dia,mes,mat,n1,n2);
                    lista.add(x);
                    JOptionPane.showMessageDialog(null,"Estudante cadastrado com sucesso!");
                }
            }
        });
       
        Botoesradio tarefa = new Botoesradio();
        rdPessoa.addItemListener(tarefa);
        rdEstudante.addItemListener(tarefa);    
    }
  //Adcionando funcionalidade aos radios buttons (Pessoa e Estudante)
    private class Botoesradio implements ItemListener{
            public void itemStateChanged(ItemEvent evento){
                if(evento.getSource()==rdPessoa) {
                    painelEstudante.setVisible(false);
                	painelPessoa.setVisible(true);
                }
                else { 
                    painelEstudante.setVisible(true);
                	painelPessoa.setVisible(true);
                }
            }
        }

    //principal
    public static void main(String[] args) {
        TelaGraficaHeranca h = new TelaGraficaHeranca();
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        h.setSize(400,300);
        h.setLocation(300,100);
        h.setVisible(true);
    }
    
}

