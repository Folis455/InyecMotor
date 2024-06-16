package front.inyecmotor;

import android.os.Parcel;
import android.os.Parcelable;

public class Modelo implements Parcelable {
    private int id;
    private String nombre;
    private int marcaId;

    public Modelo() {
    }

    public Modelo(int id, String nombre, int marcaId) {
        this.id = id;
        this.nombre = nombre;
        this.marcaId = marcaId;
    }

    protected Modelo(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        marcaId = in.readInt();
    }

    public static final Creator<Modelo> CREATOR = new Creator<Modelo>() {
        @Override
        public Modelo createFromParcel(Parcel in) {
            return new Modelo(in);
        }

        @Override
        public Modelo[] newArray(int size) {
            return new Modelo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeInt(marcaId);
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", marcaId=" + marcaId +
                '}';
    }
}
