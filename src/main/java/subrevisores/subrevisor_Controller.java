package subrevisores;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class subrevisor_Controller {
    private subrevisor_Model model;
    private subrevisor_View vista;

    public subrevisor_Controller(subrevisor_Model m, subrevisor_View v) {
        this.model = m;
        this.vista = v;
        inicializar();
    }

    private void inicializar() {
        cargarRevisoresPrincipales();
        configurarListeners();
    }

    private void cargarRevisoresPrincipales() {
        List<String> revisores = model.obtenerTodosRevisores();
        vista.mostrarRevisoresPrincipales(revisores);
        vista.setControlesHabilitados(false);
    }

    private void configurarListeners() {
        vista.getComboRevisoresPrincipales().addActionListener(e -> {
            String seleccion = (String) vista.getComboRevisoresPrincipales().getSelectedItem();
            
            if (seleccion == null || seleccion.isEmpty()) {
                vista.setControlesHabilitados(false);
                return;
            }

            try {
                int idRevisor = Integer.parseInt(seleccion.split(" - ")[0]);
                
                List<String> tracks = model.obtenerTracksDeRevisor(idRevisor);
                vista.mostrarTracks(tracks);
                vista.getComboTracks().setEnabled(!tracks.isEmpty());
                
                if (!tracks.isEmpty()) {
                    int idTrack = Integer.parseInt(tracks.get(0).split(" - ")[0]);
                    actualizarArticulos(idRevisor, idTrack);
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
                vista.setControlesHabilitados(false);
            }
        });

        vista.getComboTracks().addActionListener(e -> {
            String revisorSel = (String) vista.getComboRevisoresPrincipales().getSelectedItem();
            String trackSel = (String) vista.getComboTracks().getSelectedItem();
            
            if (revisorSel != null && trackSel != null && !revisorSel.isEmpty()) {
                try {
                    int idRevisor = Integer.parseInt(revisorSel.split(" - ")[0]);
                    int idTrack = Integer.parseInt(trackSel.split(" - ")[0]);
                    actualizarArticulos(idRevisor, idTrack);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        vista.getComboArticulos().addActionListener(e -> {
            String revisorSel = (String) vista.getComboRevisoresPrincipales().getSelectedItem();
            String trackSel = (String) vista.getComboTracks().getSelectedItem();
            String articuloSel = (String) vista.getComboArticulos().getSelectedItem();
            
            if (revisorSel != null && trackSel != null && articuloSel != null && 
                !revisorSel.isEmpty() && !trackSel.isEmpty() && !articuloSel.isEmpty()) {
                try {
                    int idRevisor = Integer.parseInt(revisorSel.split(" - ")[0]);
                    int idTrack = Integer.parseInt(trackSel.split(" - ")[0]);
                    int idArticulo = Integer.parseInt(articuloSel.split(" - ")[0]);
                    actualizarSubrevisores(idRevisor, idTrack, idArticulo);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        vista.getBtnEnviarInvitacion().addActionListener(e -> enviarInvitacion());
    }
    
    private void actualizarArticulos(int idRevisor, int idTrack) {
        try {
            List<String> articulos = model.obtenerArticulosDeRevisorTrack(idRevisor, idTrack);
            vista.mostrarArticulos(articulos);
            
            if (!articulos.isEmpty()) {
                int idArticulo = Integer.parseInt(articulos.get(0).split(" - ")[0]);
                actualizarSubrevisores(idRevisor, idTrack, idArticulo);
            } else {
                vista.mostrarRevisores(new ArrayList<>());
                vista.getBtnEnviarInvitacion().setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarSubrevisores(int idRevisorPrincipal, int idTrack, int idArticulo) {
        try {
            List<String> subrevisores = model.obtenerRevisoresMismoTrack(idRevisorPrincipal, idTrack, idArticulo);
            vista.mostrarRevisores(subrevisores);
            vista.getComboRevisores().setEnabled(!subrevisores.isEmpty());
            vista.getBtnEnviarInvitacion().setEnabled(!subrevisores.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarInvitacion() {
        String revisorPrincipal = (String) vista.getComboRevisoresPrincipales().getSelectedItem();
        String subrevisor = (String) vista.getComboRevisores().getSelectedItem();
        String track = (String) vista.getComboTracks().getSelectedItem();
        String articulo = (String) vista.getComboArticulos().getSelectedItem();

        if (revisorPrincipal != null && subrevisor != null && track != null && articulo != null 
            && !revisorPrincipal.isEmpty() && !subrevisor.isEmpty() && !track.isEmpty() && !articulo.isEmpty()) {
            
            try {
                int idRevisorPrincipal = Integer.parseInt(revisorPrincipal.split(" - ")[0]);
                int idSubrevisor = Integer.parseInt(subrevisor.split(" - ")[0]);
                int idTrack = Integer.parseInt(track.split(" - ")[0]);
                int idArticulo = Integer.parseInt(articulo.split(" - ")[0]);

                if (model.enviarInvitacion(idRevisorPrincipal, idSubrevisor, idTrack, idArticulo)) {
                    JOptionPane.showMessageDialog(vista.getFrame(), "Invitación enviada correctamente");
                    actualizarSubrevisores(idRevisorPrincipal, idTrack, idArticulo);
                } else {
                    JOptionPane.showMessageDialog(vista.getFrame(), "Error al enviar invitación");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista.getFrame(), "Error en los datos seleccionados");
            }
        } else {
            JOptionPane.showMessageDialog(vista.getFrame(), "Seleccione todos los campos");
        }
    }
}