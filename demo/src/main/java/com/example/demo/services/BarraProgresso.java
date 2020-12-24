package com.example.demo.services;
import javax.swing.*;  

@SuppressWarnings("serial")
public class BarraProgresso extends JFrame{  
	    
	public  JProgressBar jb; 
	public BarraProgresso (int min, int max){
		this.setResizable(false);
		jb=new JProgressBar(min,max); 
		jb.setBounds(150,25,300,50);  //Posizionamento e grandezza della barra
		setSize(600,150);  		//Grandezza finestra.
		setLocationRelativeTo(null);
		jb.setValue(0);  //Imposta il valore di partenza
		jb.setStringPainted(true);  
		add(jb);   
		setLayout(null);
		}	 
	}

