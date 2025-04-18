package HU29798;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        // No se necesitan listeners adicionales ya que la vista ya los tiene configurados
    }
}

