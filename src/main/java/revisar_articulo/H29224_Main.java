package revisar_articulo;

import java.awt.EventQueue;
import java.util.Arrays;
import java.util.List;

import revisar_articulo.H29224_Controller;
import revisar_articulo.H29224_Model;
import revisar_articulo.H29224_View;

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
	
