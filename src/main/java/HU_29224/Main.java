package HU_29224;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import HU_29224.Controller;
import HU_29224.Model;
import HU_29224.View;

public class Main {

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View vista = new View();
                    vista.getFrame().setVisible(true);
                    Model modelo = new Model();
                    Controller controlador = new Controller(modelo, vista);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
  
 
}}
	
