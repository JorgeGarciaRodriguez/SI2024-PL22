package HU29806;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class SubrevisorController {
    private SubrevisorModel model;
    private SubrevisorView view;
    
    public SubrevisorController(SubrevisorModel model, SubrevisorView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        cargarSubrevisores();
        
        view.getCbSubrevisores().addActionListener(e -> {
            actualizarTracksYInvitaciones();
        });
        
        view.getBtnFiltrar().addActionListener(e -> {
            filtrarInvitaciones();
        });
        
        view.getBtnResponder().addActionListener(e -> {
            responderInvitacion();
        });
    }

    private void cargarSubrevisores() {
        List<String[]> subrevisores = model.obtenerSubrevisores();
        view.cargarSubrevisores(subrevisores);
    }

    private void actualizarTracksYInvitaciones() {
        int idSubrevisor = view.getSelectedSubrevisorId();
        if (idSubrevisor != -1) {
            List<Map<String, Object>> tracks = model.obtenerTracksDisponibles(idSubrevisor);
            view.cargarTracks(tracks);
            cargarInvitaciones(idSubrevisor, null);
        }
    }

    private void filtrarInvitaciones() {
        int idSubrevisor = view.getSelectedSubrevisorId();
        Integer idTrack = view.getSelectedTrackId();
        if (idSubrevisor != -1) {
            cargarInvitaciones(idSubrevisor, idTrack);
        }
    }

    private void cargarInvitaciones(int idSubrevisor, Integer idTrack) {
        List<Map<String, Object>> invitaciones = model.obtenerInvitaciones(idSubrevisor, idTrack);
        view.cargarInvitaciones(invitaciones);
    }

    private void responderInvitacion() {
        int filaSeleccionada = view.getTableInvitaciones().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(view.getFrame(), 
                "Seleccione una invitación para responder", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String estado = (String) view.getTableModel().getValueAt(filaSeleccionada, 4);
        
        if (!"pendiente".equals(estado)) {
            JOptionPane.showMessageDialog(view.getFrame(), 
                "Esta invitación ya ha sido respondida", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int respuesta = JOptionPane.showConfirmDialog(view.getFrame(), 
            "¿Aceptar esta invitación de colaboración?", "Confirmar",
            JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION || respuesta == JOptionPane.NO_OPTION) {
            boolean aceptar = (respuesta == JOptionPane.YES_OPTION);
            
            // Obtener los datos directamente del modelo de datos en lugar de parsear la vista
            int idArticulo = (int) view.getTableModel().getValueAt(filaSeleccionada, 0);
            
            // Obtener el ID del track del modelo subyacente
            int idTrack = -1;
            Object trackObj = view.getTableModel().getValueAt(filaSeleccionada, 2);
            if (trackObj instanceof Map) {
                idTrack = (int) ((Map<?, ?>) trackObj).get("id");
            } else if (trackObj instanceof String) {
                String trackStr = (String) trackObj;
                try {
                    idTrack = Integer.parseInt(trackStr.split(" - ")[0]);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view.getFrame(), 
                        "Error al obtener el ID del track", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            int idSubrevisor = view.getSelectedSubrevisorId();
            
            // Obtener el ID del revisor principal del modelo subyacente
            int idRevisorPrincipal = -1;
            Object revisorObj = view.getTableModel().getValueAt(filaSeleccionada, 3);
            if (revisorObj instanceof Map) {
                idRevisorPrincipal = (int) ((Map<?, ?>) revisorObj).get("id");
            } else if (revisorObj instanceof String) {
                String revisorStr = (String) revisorObj;
                try {
                    idRevisorPrincipal = Integer.parseInt(revisorStr.split(" - ")[0]);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(view.getFrame(), 
                        "Error al obtener el ID del revisor principal", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            boolean resultado = model.responderInvitacion(
                idRevisorPrincipal, 
                idSubrevisor, 
                idTrack, 
                idArticulo, 
                aceptar
            );
            
            if (resultado) {
                JOptionPane.showMessageDialog(view.getFrame(), 
                    "Respuesta registrada correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                filtrarInvitaciones();
            } else {
                JOptionPane.showMessageDialog(view.getFrame(), 
                    "Error al registrar la respuesta", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   }