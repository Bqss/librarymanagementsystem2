package entity;

public class PeminjamanBuku {

    int idBuku;
    int idPeminjaman;
    String idMember;
    String tanggalPeminjaman;
    String statusPeminjaman;
    String namaPeminjam;
    String namaBuku;

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public String getNamaBuku() {
        return namaBuku;
    }


    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(int idBuku) {
        this.idBuku = idBuku;
    }

    public int getIdPeminjaman() {
        return idPeminjaman;
    }
    public PeminjamanBuku(){}

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(String tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public String getStatusPeminjaman() {
        return statusPeminjaman;
    }

    public void setStatusPeminjaman(String statusPeminjaman) {
        this.statusPeminjaman = statusPeminjaman;
    }

    public PeminjamanBuku(int idBuku, int idPeminjaman, String idMember, String tanggalPeminjaman, String statusPeminjaman) {
        this.idBuku = idBuku;
        this.idPeminjaman = idPeminjaman;
        this.idMember = idMember;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.statusPeminjaman = statusPeminjaman;
    }



}
