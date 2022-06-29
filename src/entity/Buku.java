package entity;

public class Buku {
    public Buku(String isbn, String judul, String namaPenulis, String publisher, String kategori, int stock, String tanggalterbit,int tersisa) {
        this.isbn = isbn;
        this.judul = judul;
        this.namaPenulis = namaPenulis;
        this.publisher = publisher;
        this.kategori = kategori;
        this.stock = stock;
        this.tanggalterbit = tanggalterbit;
        this.tersisa = tersisa;
    }
    public Buku(){}

    public int getTersisa() {
        return tersisa;
    }

    public void setTersisa(int tersisa) {
        this.tersisa = tersisa;
    }

    private String isbn,judul,namaPenulis,publisher,kategori;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int stock,tersisa,id;

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setNamaPenulis(String namaPenulis) {
        this.namaPenulis = namaPenulis;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setTanggalterbit(String tanggalterbit) {
        this.tanggalterbit = tanggalterbit;
    }

    private String tanggalterbit;

    public String getIsbn() {
        return isbn;
    }

    public String getJudul() {
        return judul;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStock() {
        return stock;
    }

    public  String getTanggalterbit() {
        return tanggalterbit;
    }
}
