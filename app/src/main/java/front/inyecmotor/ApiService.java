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

    @PATCH("/producto/editar/{id}")
    Call<Producto> editarProducto(@Path("id") int id, @Body Producto producto);

}

