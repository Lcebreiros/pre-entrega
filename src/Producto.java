public class Producto {
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private String coleccion;

    public Producto(int id, String marca, String modelo, double precio, String coleccion) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        this.coleccion = coleccion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getColeccion() { return coleccion; }
    public void setColeccion(String coleccion) { this.coleccion = coleccion; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s | $%.2f | %s",
                id, marca, modelo, precio, coleccion);
    }
}
