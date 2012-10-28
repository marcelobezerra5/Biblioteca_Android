package dan.tesco.buscabiblioteca.data;

public class Book {
	protected long id;
	protected String autor;
	protected String titulo;
	protected String dispImagen;
	
	public Book(){
		this.autor="";
		this.titulo="";
		this.dispImagen="";
	}
	public Book(long id, String autor, String titulo, String dispImagen){
		this.id=id;
		this.autor=autor;
		this.titulo=titulo;
		this.dispImagen=dispImagen;
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

}
