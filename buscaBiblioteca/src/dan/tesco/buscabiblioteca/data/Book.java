package dan.tesco.buscabiblioteca.data;

public class Book {
	protected long id;
	protected String autor;
	protected String titulo;
	protected String dispImagen;
	protected String dispLibro;//agregado para disponibilidad de libro
	
	public Book(){
		this.autor="";
		this.titulo="";
		this.dispImagen="";
		this.dispLibro="";//
	}
	public Book(long id, String autor, String titulo, String dispImagen, String dispLibro){
		this.id=id;
		this.autor=autor;
		this.titulo=titulo;
		this.dispImagen=dispImagen;
		this.dispLibro=dispLibro;//
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDispImagen() {
		return dispImagen;
	}
	public void setDispImagen(String dispImagen) {
		this.dispImagen = dispImagen;
	}
	public String getDispLibro() {//
		return dispLibro;
	}
	public void setDispLibro(String dispLibro) {//
		this.dispLibro = dispLibro;
	}

}
