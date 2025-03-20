package rechazar_articulos;

public class Main {

	public static void main(String[] args) {
		View v=new View();
		Model m=new Model();
		Controller c=new Controller(m,v);
		v.getFrame().setVisible(true);
	}

}
