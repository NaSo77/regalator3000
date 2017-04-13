/*FUTURO: enganchar calendarPanel aqui, cambiar todo de sitio
 * Se puede llamar con el DbConnector del usuario para crear el calendario, y de paso reestructurar cosas casi seguro.
 * HACER QUE AL APRETAR ENTER EN LA DATA EL CALENDARIO VAYA A LA DATA ESCRITA(11/04)*/
package regalator3000.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import regalator3000.db.DatabaseHandler;
import regalator3000.db.EventoControl;
import regalator3000.db.GUIDataRetriever;
import regalator3000.misc.AuxFunctions;
import regalator3000.misc.EventData;

public class Proposal_GUI extends javax.swing.JFrame implements ActionListener{
	private DatabaseHandler DbConnector;
	private static boolean eventIsSelected = false; //for the GUI lookandfeel (if an event is selected and the user clicks another day it'll remove the data in the GUI, if he wasnt selecting an event and just wants to change the date it wont
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Creates new form Proposal_GUI
     */
    public Proposal_GUI(DatabaseHandler DbConnector) {
    	this.DbConnector = DbConnector;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings({"unchecked","rawtypes", "serial"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
    	
		ArrayList<EventData> userEvents = EventoControl.getEvents(DbConnector);
		myCalendar = new CalendarPanel(this, userEvents, -1, -1);
        jPanel1 = new javax.swing.JPanel();
        fechaLabel = new javax.swing.JLabel();
        descLabel = new javax.swing.JLabel();
        descField = new javax.swing.JTextField();
        marcasList = new javax.swing.JList<String>();
        catComboBoxList = new JComboBox[10];
        avisoLabelInicio = new javax.swing.JLabel();
        diasAvisoField = new javax.swing.JTextField();
        avisoLabelFinal = new javax.swing.JLabel();
        regConcretoRadioButton = new JRadioButton();
        regAleatorioRadioButton = new JRadioButton();
        marcaLabel = new javax.swing.JLabel();
        catLabel = new javax.swing.JLabel();
        fechaFormattedField = new javax.swing.JFormattedTextField((new SimpleDateFormat("yyyy-MM-dd")));
        fechaFormattedField.setText("2017-01-01");
        jSeparator1 = new javax.swing.JSeparator();
        limpiarButton = new javax.swing.JButton();
        goToButton = new javax.swing.JButton("Ir a fecha");
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fechaLabel.setText("Fecha:");
        descLabel.setText("Descripción:");
        descField.setText(""); //Default description      
        diasAvisoField.setText("0"); //Default dias aviso
        
        String[] regalos = GUIDataRetriever.getAllElements(DbConnector,"nombre","regalos",true);
        regalos[0] = "Ninguno";
        regConcretoComboBox = new JComboBox(regalos);
        regConcretoComboBox.setEnabled(false);
        String[] marcas = GUIDataRetriever.getAllElements(DbConnector,"nombre","marcas",true);	
        marcasList.setListData(marcas);
        marcasList.setSelectionModel(new DefaultListSelectionModel() {
			@Override
            public void setSelectionInterval(int index0, int index1) {
                if (index1 == 0){
                	this.clearSelection();
                	this.moveLeadSelectionIndex(0);
                	return;
                }
                if(super.isSelectedIndex(index0)) {
                    super.removeSelectionInterval(index0, index1);
                }
                else {
                    super.addSelectionInterval(index0, index1);
                }
            }
        });
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(marcasList);
        
        String[] categoriasTotal = GUIDataRetriever.getAllElements(DbConnector,"tipo","categorias",true); 
        String[] categoriasUsadas = new String[categoriasTotal.length];
        for(int i = 0; i < categoriasTotal.length; i++){
        	categoriasUsadas[i] = categoriasTotal[i]; //Copia de categoriasTotal que va reduciendo sus elementos a medida que se eligen
        }
        for(int i = 0; i < catComboBoxList.length; i++){
        	catComboBoxList[i] = new javax.swing.JComboBox<String>(categoriasUsadas);
        }
        
        avisoLabelInicio.setText("Avisame");

        avisoLabelFinal.setText("días antes del evento");

        regConcretoRadioButton.setText("Regalo concreto");
        regConcretoRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               JRadioButton src = (JRadioButton)evt.getSource();
               if (src.isSelected()){
            	   regConcretoComboBox.setEnabled(true);
               }
            }
        });


        regAleatorioRadioButton.setText("Regalo aleatorio");
        regAleatorioRadioButton.setSelected(true); //default selection
        regAleatorioRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               JRadioButton src = (JRadioButton)evt.getSource();
               if (src.isSelected()){
            	   regConcretoComboBox.setEnabled(false);
            	   regConcretoComboBox.setSelectedIndex(0);
               }
            }
        });

        marcaLabel.setText("Marca:");

        catLabel.setText("Categoría:");

        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));

        limpiarButton.setText("Limpiar datos");
        limpiarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonAction(evt);
            }
        });
        
        goToButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               String newDate = DialogGenerator.createGoToDialog();
               goToDate(newDate);
            }
        });
        
        fechaFormattedField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String newDate = fechaFormattedField.getText();
                goToDate(newDate);
             }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(myCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descLabel)
                            .addComponent(fechaLabel)
                            .addComponent(avisoLabelInicio))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descField,javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(diasAvisoField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(avisoLabelFinal))
                                    .addComponent(goToButton))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE) //cutre, lo hago grande para que atraviese la GUI
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(marcaLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(catComboBoxList[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(catComboBoxList[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(regConcretoRadioButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(catComboBoxList[6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(catComboBoxList[7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(catComboBoxList[8], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(catComboBoxList[9], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60,60,60)
                                .addComponent(limpiarButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(catLabel)
                                )
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(catComboBoxList[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(catComboBoxList[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(regAleatorioRadioButton)
                                )
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(catComboBoxList[4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(catComboBoxList[5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(regConcretoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                ))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaLabel)
                            .addComponent(fechaFormattedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(descLabel)
                            .addComponent(descField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(avisoLabelInicio)
                            .addComponent(diasAvisoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(avisoLabelFinal)
                            )
                        .addGap(18, 18, 18)
                        .addComponent(goToButton)
                        .addGap(75)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(myCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(marcaLabel)
                    .addComponent(catLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(catComboBoxList[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catComboBoxList[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regAleatorioRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(catComboBoxList[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catComboBoxList[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regConcretoRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(catComboBoxList[4], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catComboBoxList[5], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regConcretoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(catComboBoxList[6], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catComboBoxList[7], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            )
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(catComboBoxList[8], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(catComboBoxList[9], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(limpiarButton)
                            .addGap(40))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
       
		ButtonGroup eleccion = new ButtonGroup();
		eleccion.add(regConcretoRadioButton); //Para que sean exclusivos el uno con el otro
		eleccion.add(regAleatorioRadioButton);

    }                       
    
    /*Llamar para que lea y ponga en pantalla los datos de un evento*/
    public void displayEventData(EventData evento){
    	if(evento == null){ return; }
    	fechaFormattedField.setText(evento.fecha);
    	descField.setText(evento.descripcion);
    	if (evento.marcas != null){
    		marcasList.setSelectedIndices(evento.marcas);
    	}
    	if (evento.categorias != null){
	        for(int i = 0; i < evento.categorias.length; i++){
	        	catComboBoxList[i].setSelectedIndex(evento.categorias[i]);
	        }
    	}
    	diasAvisoField.setText(Integer.toString(evento.diasAviso));
    	if(evento.regaloConcreto == 0) {
            regAleatorioRadioButton.setSelected(true); //regalo aleatorio
    	}
    	else {
    		regConcretoRadioButton.setSelected(true);
    		regConcretoComboBox.setSelectedIndex(evento.regaloConcreto);
    		regConcretoComboBox.setEnabled(true);
    	}
    }
    
    /*Devuelve un objeto eventData con los datos escritos por el usuario, NOTESE que eventID i userID se tienen que conocer de antemano
     * o hacer que como input tenga el evento antiguo con esos datos*/
    public EventData getNewEventData(){ 
    	EventData newEvent = new EventData("0"); //user temporal
    	newEvent.fecha = fechaFormattedField.getText();
    	newEvent.descripcion = descField.getText();
    	int[] marcas = marcasList.getSelectedIndices();
    	newEvent.marcas = marcas;
    	ArrayList<Integer> categorias = new ArrayList<Integer>();
    	for (int i = 0; i < catComboBoxList.length; i++) {
    		if (catComboBoxList[i].getSelectedIndex() != 0 && !categorias.contains(catComboBoxList[i].getSelectedIndex())){
    			categorias.add(catComboBoxList[i].getSelectedIndex());
    		}
    	}
    	int[] catArray = new int[categorias.size()];
    	for (int i = 0; i < categorias.size(); i++) {
    		catArray[i] = categorias.get(i);
    	}
    	newEvent.categorias = catArray;
    	newEvent.diasAviso = Integer.parseInt(diasAvisoField.getText());
    	if (regAleatorioRadioButton.isSelected() || regConcretoComboBox.getSelectedIndex() == 0) {
    		newEvent.regaloConcreto = 0;
    	}
    	else {
    		newEvent.regaloConcreto = regConcretoComboBox.getSelectedIndex();
    	}
    	return newEvent;
    }
    
    /*Funcion para que no se puedan modificar los datos de un evento, solo se pueden mirar*/
    public void freezeAllInput(){
        descField.setFocusable(false);
        diasAvisoField.setFocusable(false);
        fechaFormattedField.setFocusable(false);
        marcasList.setEnabled(false);
        for(int i = 0; i < catComboBoxList.length; i++) {
        	catComboBoxList[i].setEnabled(false);
        }
        regConcretoRadioButton.setEnabled(false);
        regAleatorioRadioButton.setEnabled(false);
        regConcretoComboBox.setEnabled(false);
        limpiarButton.setEnabled(false);
    }                                              

    private void clearButtonAction(java.awt.event.ActionEvent evt) {                                         
        // Limpia el formulario al pulsar.Faltan campos por indicar que limpie; de momento limpia Descripcion y las Checkbox
        descField.setText("");
        diasAvisoField.setText("0");
        fechaFormattedField.setText("2017-01-01");
        marcasList.setSelectedIndices(new int[0]);
        for(int i = 0; i < catComboBoxList.length; i++) {
        	catComboBoxList[i].setSelectedIndex(0);
        }
        regConcretoRadioButton.setSelected(false);
        regAleatorioRadioButton.setSelected(false);
        regConcretoComboBox.setSelectedIndex(0);
        
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
            java.util.logging.Logger.getLogger(Proposal_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proposal_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proposal_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proposal_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	DatabaseHandler DbConnector = new DatabaseHandler();
            	DbConnector.setUserID(1); //Dummy user, change after testing
                new Proposal_GUI(DbConnector).setVisible(true);
            }
        });
        

    }
    
    /*Añadir mas control de errores aqui, por ahora solo comprueba si la fecha tiene evento ya (añadir que todos los valores son correctos etc...*/
    public boolean canCreateEvent(EventData evento){
    	return !(myCalendar.isDayAnEvent(evento.fecha));
    }
    
	public void actionPerformed(ActionEvent evt){
		String command = evt.getActionCommand();
		Object src = evt.getSource();
		JButton buttonPressed = (JButton)src; //Foreground.getBlue == 128 si es gris...
		//System.out.println("mes: " + myCalendar.getMonth() + " , año: " + myCalendar.getYear() + " , dia: " + buttonPressed.getText() + " ,color: " + buttonPressed.getForeground().getRed());
		int month = myCalendar.getMonth();
		CalendarButton eventButton = new CalendarButton("",-1);
		if (src.getClass().equals(eventButton.getClass())){ //El user ha clickado en un dia con evento...
			eventButton = (CalendarButton)src;
			int selectedEventID = eventButton.getEventID();
			EventData actualEvent = EventoControl.getEventData(DbConnector,selectedEventID);
			clearButtonAction(evt);
			displayEventData(actualEvent);
			eventIsSelected = true;
		}
		else {
			if(eventIsSelected){
				clearButtonAction(evt);
				eventIsSelected = false;
			}
		}
		if (buttonPressed.getForeground().getRed() >= 128) { //greyed out or redded out month,  WARNING !!!!!! CHANGE IF YOU CHANGE THE COLORS OF THE CALENDAR (maybe change this so its more universal... hmmm needs a lot of change for that
			if (Integer.parseInt(buttonPressed.getText()) >= 20) { //its past month
				month--;
			}
			else {
				month++;
			}
		}
		fechaFormattedField.setText(getCurrentDate(command,month));
	}
	
	private String getCurrentDate(String day, int month){
		String separator = "-"; //Fer canvis en futur maybe
		return AuxFunctions.formatDateFromValues(myCalendar.getYear(), month+1, Integer.parseInt(day), separator);
	}
	
	private void goToDate(String newDate){
        if (!newDate.equals("")){
     	   myCalendar.setMonth(AuxFunctions.getFieldFromDate(newDate, 1)-1);
     	   myCalendar.setYear(AuxFunctions.getFieldFromDate(newDate, 0));
     	   myCalendar.redraw();
     	   fechaFormattedField.setText(newDate);
        }
	}
    // Variables declaration - do not modify                     
    private javax.swing.JButton limpiarButton;
    private javax.swing.JButton goToButton;
    private JComboBox<String>[] catComboBoxList;
    private JComboBox<String> regConcretoComboBox;
    private javax.swing.JFormattedTextField fechaFormattedField;
    private javax.swing.JLabel fechaLabel;
    private javax.swing.JLabel descLabel;
    private javax.swing.JLabel avisoLabelInicio;
    private javax.swing.JLabel avisoLabelFinal;
    private javax.swing.JLabel marcaLabel;
    private javax.swing.JLabel catLabel;
    private javax.swing.JList<String> marcasList;
    private javax.swing.JPanel jPanel1;
    private JRadioButton regConcretoRadioButton;
    private JRadioButton regAleatorioRadioButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField descField;
    private javax.swing.JTextField diasAvisoField;
    private JScrollPane scrollPane;
    private CalendarPanel myCalendar;

    // End of variables declaration                   
}
