package front.inyecmotor;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    private int id;
    private int codigo;
    private String nombre;
    private double precioCosto; // Cambiado a double para manejar precisión en precios
    private double precioVenta; // Cambiado a double para manejar precisión en precios
    private int stockActual;
    private int stockMax;
    private int stockMin;

    // Constructor vacío (puede ser útil para algunas operaciones)
    public Producto() {
    }

    // Constructor con parámetros para inicializar todos los campos
    public Producto(int id, int codigo, String nombre, double precioCosto, double precioVenta, int stockActual, int stockMax, int stockMin) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.stockMax = stockMax;
        this.stockMin = stockMin;
    }

    // Constructor para Parcel
    protected Producto(Parcel in) {
        id = in.readInt();
        codigo = in.readInt();
        nombre = in.readString();
        precioCosto = in.readDouble();
        precioVenta = in.readDouble();
        stockActual = in.readInt();
        stockMax = in.readInt();
        stockMin = in.readInt();
    }

    // Getters y setters para todos los campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public int getStockMin() {
        return stockMin;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    // Otros getters y setters si es necesario para otros campos

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precioCosto=" + precioCosto +
                ", precioVenta=" + precioVenta +
                ", stockActual=" + stockActual +
                ", stockMax=" + stockMax +
                ", stockMin=" + stockMin +
                '}';
    }

    // Métodos de Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeInt(codigo);
        parcel.writeString(nombre);
        parcel.writeDouble(precioCosto);
        parcel.writeDouble(precioVenta);
        parcel.writeInt(stockActual);
        parcel.writeInt(stockMax);
        parcel.writeInt(stockMin);
    }

    public static final Creator<Producto> CREATOR = new Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel in) {
            return new Producto(in);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}
