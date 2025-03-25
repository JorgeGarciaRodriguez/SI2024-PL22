package subrevisores;

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
        vista.getComboRevisoresPrincipales().removeAllItems();
        vista.getComboRevisoresPrincipales().addItem(""); // Opción vacía inicial
        revisores.forEach(vista.getComboRevisoresPrincipales()::addItem);
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
                    actualizarSubrevisores(idRevisor, idTrack);
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
                    actualizarSubrevisores(idRevisor, idTrack);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        vista.getBtnEnviarInvitacion().addActionListener(e -> enviarInvitacion());
    }

    private void actualizarSubrevisores(int idRevisor, int idTrack) {
        try {
            List<String> subrevisores = model.obtenerRevisoresMismoTrack(idRevisor, idTrack);
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

        if (revisorPrincipal != null && subrevisor != null && track != null 
            && !revisorPrincipal.isEmpty() && !subrevisor.isEmpty() && !track.isEmpty()) {
            
            try {
                int idRevisorPrincipal = Integer.parseInt(revisorPrincipal.split(" - ")[0]);
                int idSubrevisor = Integer.parseInt(subrevisor.split(" - ")[0]);
                int idTrack = Integer.parseInt(track.split(" - ")[0]);

                if (model.enviarInvitacion(idRevisorPrincipal, idSubrevisor, idTrack)) {
                    JOptionPane.showMessageDialog(vista.getFrame(), "Invitación enviada correctamente");
                    actualizarSubrevisores(idRevisorPrincipal, idTrack);
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