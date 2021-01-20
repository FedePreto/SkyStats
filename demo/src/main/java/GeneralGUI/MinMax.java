package GeneralGUI;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.JsonObject;
import com.univpm.oop.log.Log;
import com.univpm.oop.model.Citta;
import com.univpm.oop.services.Convertitore;
import com.univpm.oop.services.Favoriti;
import com.univpm.oop.statistiche.Stat;
import com.univpm.oop.statistiche.Tempo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class MinMax extends javax.swing.JFrame {

    /**
     * Creates new form SingleCity
     */
    public MinMax() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        MenùReturn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        MaxCity = new javax.swing.JPanel();
        MaxHumCity = new javax.swing.JLabel();
        MaxHumValue = new javax.swing.JLabel();
        MaxVarHumCity = new javax.swing.JLabel();
        MaxVarHumValue = new javax.swing.JLabel();
        MaxPressCity = new javax.swing.JLabel();
        MaxPressValue = new javax.swing.JLabel();
        MaxVarPressCity = new javax.swing.JLabel();
        MaxVarPressValue = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        MinCity = new javax.swing.JPanel();
        MinHumCity = new javax.swing.JLabel();
        MinHumValue = new javax.swing.JLabel();
        MinVarHumCity = new javax.swing.JLabel();
        MinVarHumValue = new javax.swing.JLabel();
        MinPressCity = new javax.swing.JLabel();
        MinPressValue = new javax.swing.JLabel();
        MinVarPressCity = new javax.swing.JLabel();
        MinVarPressValue = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TimeSpan = new javax.swing.JComboBox<>();
        CercaMeteo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Record dei Massimi e Minimi delle città");

        MenùReturn.setText("Ritorna al menù");
        MenùReturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MenùReturnMouseClicked(evt);
            }
        });
        MenùReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenùReturnActionPerformed(evt);
            }
        });

        jLabel4.setText("Massima");

        jLabel5.setText("Minima");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(jLabel5))
        );

        jLabel10.setText("Umidità");

        jLabel11.setText("Pressione");

        jLabel3.setText("Varianza Umidità");

        jLabel12.setText("Varianza Pressione");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel12))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        MaxHumCity.setText(" ");

        MaxHumValue.setText(" ");

        MaxVarHumCity.setText(" ");

        MaxVarHumValue.setText(" ");

        MaxPressCity.setText(" ");

        MaxPressValue.setText("        ");

        MaxVarPressCity.setText(" ");

        MaxVarPressValue.setText("  ");

        jLabel6.setText("Città");

        jLabel7.setText("Valore");

        javax.swing.GroupLayout MaxCityLayout = new javax.swing.GroupLayout(MaxCity);
        MaxCity.setLayout(MaxCityLayout);
        MaxCityLayout.setHorizontalGroup(
            MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaxCityLayout.createSequentialGroup()
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(MaxVarHumCity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(MaxPressCity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MaxVarPressCity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MaxHumCity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MaxCityLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(MaxHumValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MaxVarHumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MaxPressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MaxVarPressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        MaxCityLayout.setVerticalGroup(
            MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaxCityLayout.createSequentialGroup()
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxHumCity)
                    .addComponent(MaxHumValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxVarHumCity)
                    .addComponent(MaxVarHumValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxPressCity)
                    .addComponent(MaxPressValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MaxCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxVarPressCity)
                    .addComponent(MaxVarPressValue))
                .addGap(67, 67, 67))
        );

        MinHumCity.setText("  ");

        MinHumValue.setText("  ");

        MinVarHumCity.setText("  ");

        MinVarHumValue.setText(" ");

        MinPressCity.setText("  ");

        MinPressValue.setText(" ");

        MinVarPressCity.setText("  ");

        MinVarPressValue.setText(" ");

        jLabel8.setText("Città");

        jLabel9.setText("Valore");

        javax.swing.GroupLayout MinCityLayout = new javax.swing.GroupLayout(MinCity);
        MinCity.setLayout(MinCityLayout);
        MinCityLayout.setHorizontalGroup(
            MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MinCityLayout.createSequentialGroup()
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MinVarPressCity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinPressCity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinVarHumCity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinHumCity, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MinHumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MinPressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MinVarPressValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(MinVarHumValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MinCityLayout.setVerticalGroup(
            MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MinCityLayout.createSequentialGroup()
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MinHumCity)
                    .addComponent(MinHumValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MinVarHumCity)
                    .addComponent(MinVarHumValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MinPressCity)
                    .addComponent(MinPressValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MinCityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MinVarPressCity)
                    .addComponent(MinVarPressValue))
                .addGap(31, 31, 31))
        );

        jLabel2.setText("Periodo di Tempo");

        TimeSpan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giornaliero", "Settimanale", "Mensile", "Annuale" }));
        TimeSpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeSpanActionPerformed(evt);
            }
        });

        CercaMeteo.setText("Cerca");
        CercaMeteo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CercaMeteoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TimeSpan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(CercaMeteo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(MenùReturn)
                            .addGap(32, 32, 32))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(127, 127, 127)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MaxCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(MinCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TimeSpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CercaMeteo))
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaxCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MinCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenùReturn)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>                        

    private void MenùReturnMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
        new StartingFrame().setVisible(true);
        dispose();
    }                                       

    private void MenùReturnActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void TimeSpanActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void CercaMeteoMouseClicked(java.awt.event.MouseEvent evt) {                                        
    	//Si prende la data che ci interessa dal selettore
    	Date[] date = new Date[2];
    	date=Tempo.getDateFromString((String)TimeSpan.getSelectedItem());
    	System.out.println(date[0]+" "+ date[1]);
    	Convertitore conv = new Convertitore();
    	ArrayList<Citta> citta = conv.JsonToCitta(date[0], date[1]);
    	Stat stat= new Stat();
    	/*
  	  Array volto alla memorizzazione dei vari valori massimi con la seguente logica di indici:
  		0-Umidità massima/minima
  		1-Pressione massima/minima
  		2-Varianza di umidità massima/minima
  		3-Varianza di pressione massima/minima
  	*/
  		double min_val[] = new double[4];
  		double max_val[] = new double[4];
  	/*
  	   Array volto alla memorizzazione dei vari indici dell'array citta contenenti le citta cercate
  	   (Sfrutta la stessa logica di posizionamento dell'arrey precedente)	
  	 */
  		int min_index[] = new int[4];
  		int max_index[] = new int[4];
   //Inizializza i minimi al primo valore letto nell'array poi procederà con i confronti
  		 min_val[0] = citta.get(0).getUmidita();
  	     min_val[1] = citta.get(0).getPressione();
  	     min_val[2] = stat.getVarianza(stat.getValues(citta, citta.get(0).getNome()));
  		 min_val[3] = stat.getVarianza(stat.getValues(citta, citta.get(0).getNome()));
  		 max_val[0] = citta.get(0).getUmidita();
  	     max_val[1] = citta.get(0).getPressione();
  	     max_val[2] = stat.getVarianza(stat.getValues(citta, citta.get(0).getNome()));
  		 max_val[3] = stat.getVarianza(stat.getValues(citta, citta.get(0).getNome()));
  		 //Ciclo for che permette di analizzare tutte le citta presenti nell'arrayList
  		for (int i = 1; i < citta.size(); i++) {
  		   /*
  			   L'istanziamento delle seguenti variabili è volto alla memorizzazione
  			   dei valori permettendo così di non dover svolgere gli stessi calcoli due volte 
  			   con il conseguente risparmio notevole di tempo in fase di esecuzione			 
  			 */				
  				//Permette di memorizzare il valore minimo di umidità e l'indice della città che lo contiene
  				if ( citta.get(i).getUmidita() < min_val[0]) {
  					min_val[0] = citta.get(i).getUmidita();
  					min_index[0] = i;
  				}
  			//Permette di memorizzare il valore massimo di umidità e l'indice della città che lo contiene
  				if ( citta.get(i).getUmidita() > max_val[0]) {
  					max_val[0] = citta.get(i).getUmidita();
  					max_index[0] = i;
  				}
  				//Permette di memorizzare il valore minimo di pressione e l'indice della citta che lo contiene
  				if (citta.get(i).getPressione() < min_val[1]) {
  					min_val[1] = citta.get(i).getPressione();
  					min_index[1] = i;
  				}
  			//Permette di memorizzare il valore massimo di pressione e l'indice della citta che lo contiene
  				if (citta.get(i).getPressione() > max_val[1]) {
  					max_val[1] = citta.get(i).getPressione();
  					max_index[1] = i;
  				}
  		}
  		Favoriti fav = new Favoriti();
  		ArrayList<String> favoriti = fav.getFavoriti();
  		double getVarU=0;
  		double getVarP=0;
  		//Crea una barra che permette all'utente di visualizzare lo stato di avanzamento
  			
  			
  		    
  		for(int i=1; i<favoriti.size(); i++) {
  			
  			for(int j=1; j<citta.size(); j++) {
  				if(favoriti.get(i).equals(citta.get(j).getNome())) {					
  					getVarU= stat.getVarianza(stat.getValues(citta, citta.get(j).getNome(), false));
  					//getVarP memorizza la varianza della pressione per una determinata citta
  					getVarP=stat.getVarianza(stat.getValues(citta, citta.get(j).getNome(), true));
  					break;
  			   }
  			}
  				//Permette di memorizzare il valore minimo di varianza di Umidita e l'indice della città su cui è stato calcolato
  				if (getVarU < min_val[2]) {					
  					min_val[2] = getVarU;
  					min_index[2] = i;
  				}
  			//Permette di memorizzare il valore massimo di varianza di Umidita e l'indice della città su cui è stato calcolato
  				if (getVarU > max_val[2]) {					
  					max_val[2] = getVarU;
  					max_index[2] = i;
  				}
  				//Permette di memorizzare il valore minimo di varianza di pressione e l'indice della città su cui è stato calcolato
  				if (getVarP < min_val[3]) {
  					min_val[3] = getVarP;
  					min_index[3] = i;
  				}	
  			//Permette di memorizzare il valore massimo di varianza di pressione e l'indice della città su cui è stato calcolato
  				if (getVarP > max_val[3]) {
  					max_val[3] = getVarP;
  					max_index[3] = i;
  				}	
  			}
  		
	
		
    	
        MaxHumCity.setText(citta.get(max_index[0]).getNome());
        MaxHumValue.setText(new DecimalFormat("#.##").format(max_val[0])+"%");
        MaxPressCity.setText(citta.get(max_index [1]).getNome());
        MaxPressValue.setText(new DecimalFormat("#.##").format(max_val[1])+" hPa");
        MaxVarHumCity.setText(citta.get(max_index [2]).getNome());
        MaxVarHumValue.setText(new DecimalFormat("#.##").format(max_val[2]));
        MaxVarPressCity.setText(citta.get(max_index [3]).getNome());
        MaxVarPressValue.setText(new DecimalFormat("#.##").format(max_val[3]));
        
        MinHumCity.setText(citta.get(min_index[0]).getNome());
        MinHumValue.setText(new DecimalFormat("#.##").format(min_val[0])+"%");
        MinPressCity.setText(citta.get(min_index [1]).getNome());
        MinPressValue.setText(new DecimalFormat("#.##").format(min_val[1])+" hPa");
        MinVarHumCity.setText(citta.get(min_index [2]).getNome());
        MinVarHumValue.setText(new DecimalFormat("#.##").format(min_val[2]));
        MinVarPressCity.setText(citta.get(min_index [3]).getNome());
        MinVarPressValue.setText(new DecimalFormat("#.##").format(min_val[3]));
                
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
            java.util.logging.Logger.getLogger(SingleCity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SingleCity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SingleCity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SingleCity.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            Log.report(String.valueOf(new Date()),ex.getMessage());
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SingleCity().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton CercaMeteo;
    private javax.swing.JPanel MaxCity;
    private javax.swing.JLabel MaxHumCity;
    private javax.swing.JLabel MaxHumValue;
    private javax.swing.JLabel MaxPressCity;
    private javax.swing.JLabel MaxPressValue;
    private javax.swing.JLabel MaxVarHumCity;
    private javax.swing.JLabel MaxVarHumValue;
    private javax.swing.JLabel MaxVarPressCity;
    private javax.swing.JLabel MaxVarPressValue;
    private javax.swing.JButton MenùReturn;
    private javax.swing.JPanel MinCity;
    private javax.swing.JLabel MinHumCity;
    private javax.swing.JLabel MinHumValue;
    private javax.swing.JLabel MinPressCity;
    private javax.swing.JLabel MinPressValue;
    private javax.swing.JLabel MinVarHumCity;
    private javax.swing.JLabel MinVarHumValue;
    private javax.swing.JLabel MinVarPressCity;
    private javax.swing.JLabel MinVarPressValue;
    private javax.swing.JComboBox<String> TimeSpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration                   
}
