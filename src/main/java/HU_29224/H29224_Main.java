package HU_29224;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import HU_29224.H29224_Controller;
import HU_29224.H29224_Model;
import HU_29224.H29224_View;

public class H29224_Main {

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    H29224_View vista = new H29224_View();
                    vista.getFrame().setVisible(true);
                    H29224_Model modelo = new H29224_Model();
                    H29224_Controller controlador = new H29224_Controller(modelo, vista);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
  
 
}}
	
