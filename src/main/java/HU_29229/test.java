package HU_29229;

public class test {
	public static void main(String[] args) {
		Model a = new Model();
		System.out.println("Obteniendo lista de artículos...");
		for (Object[] b : a.getAutores("Inteligencia Artificial en la Educación")) {
			if (b != null && b.length > 0) {
				String nombre = (String) b[0];
				System.out.println("Título: " + nombre);
			} else {
				System.out.println("No se encontró ningún artículo.");
			}
		}
		System.out.print(a.getResumen("Inteligencia Artificial en la Educación"));
	}
}
