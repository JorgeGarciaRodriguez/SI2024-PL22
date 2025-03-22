package rechazar_aceptarconcambios;

public class Main {

	public static void main(String[] args) {
		Rechazar_aceptarView v=new Rechazar_aceptarView();
		Rechazar_aceptarModel m=new Rechazar_aceptarModel();
		Rechazar_aceptarController c=new Rechazar_aceptarController(m,v);
		v.getFrame().setVisible(true);
	}

}
