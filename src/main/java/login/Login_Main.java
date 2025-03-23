package login;

public class Login_Main {
    public static void main(String[] args) {
        // Crear instancias del modelo y la vista
        Login_Model model = new Login_Model();
        Login_View view = new Login_View();
        
        // Crear el controlador y pasarle el modelo y la vista
        Login_Controller controller = new Login_Controller(model, view);
        
        // Hacer visible la ventana de login
        view.getFrame().setVisible(true);
    }
}
