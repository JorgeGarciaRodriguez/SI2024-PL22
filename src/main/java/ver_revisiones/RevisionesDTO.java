package ver_revisiones;

public class RevisionesDTO {
	private int idRevisor;
	private int decision;
	private String coment_autor;
	public RevisionesDTO(int idRevisor, int decision, String coment_autor) {
		this.idRevisor = idRevisor;
		this.decision = decision;
		this.coment_autor = coment_autor;
	}
	public RevisionesDTO() {

	}
	public int getIdRevisor() {
		return idRevisor;
	}
	public void setIdRevisor(int idRevisor) {
		this.idRevisor = idRevisor;
	}
	public int getDecision() {
		return decision;
	}
	public void setDecision(int decision) {
		this.decision = decision;
	}
	public String getComent_autor() {
		return coment_autor;
	}
	public void setComent_autor(String coment_autor) {
		this.coment_autor = coment_autor;
	}
	
	
	
}
