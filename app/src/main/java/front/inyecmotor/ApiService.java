package front.inyecmotor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/producto/all") // La ruta de tu endpoint en el servidor Spring Boot
    Call<List<Producto>> getProductos();


    @GET("/marcas/all")
    Call<List<Marca>> getMarcas(); // Define el nuevo endpoint para obtener las marcas


}

