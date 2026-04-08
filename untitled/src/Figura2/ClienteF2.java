public class ClienteF2 {
    private String email;

    public Cliente(IdPersona id, Nombre nom ,String email) {
        this.id = id;
        this.nombre = nom;
        this.email = email;
    }
    public String getEmail(){
     return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void addVenta(Venta venta){
        this.venta=venta;
    }
    public Venta[] getVenta();
}
