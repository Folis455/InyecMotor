package front.inyecmotor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/producto/all") // La ruta de tu endpoint en el servidor Spring Boot
    Call<List<Producto>> getProductos();

    @PATCH("/producto/editar")
    Call<Producto> editarProducto(@Body Producto producto);

    @GET("/marca/all")
    Call<List<Marca>> getMarcas();

    @PATCH("/marca/editar")
    Call<Marca> editarMarca(@Body Marca marca);

    @GET("/modelos/all")
    Call<List<Modelo>> getModelos();

    @PATCH("/modelo/editar")
    Call<Modelo> editarModelo(@Body Modelo modelo);

    @GET("/proveedor")
    Call<List<Proveedor>> getProveedores();

    @PATCH("/proveedor/editar")
    Call<Proveedor> editarProveedor(@Body Proveedor proveedor);
}

