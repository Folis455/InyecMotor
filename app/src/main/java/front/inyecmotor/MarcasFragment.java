package front.inyecmotor;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarcasFragment extends Fragment {

    private static final String BASE_URL = "http://192.168.0.8:8080"; // Cambia a la URL de tu servidor

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.marcas_fragment, container, false);

        Button getMarcasButton = view.findViewById(R.id.getMarcas);

        if (getMarcasButton != null) {
            getMarcasButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fetchMarcas();
                }
            });
        }

        return view;
    }

    private void fetchMarcas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Marca>> call = apiService.getMarcas();

        call.enqueue(new Callback<List<Marca>>() {
            @Override
            public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Marca> marcas = response.body();
                    StringBuilder toastMessage = new StringBuilder();
                    for (Marca marca : marcas) {
                        toastMessage.append("ID: ").append(marca.getId())
                                .append(", Nombre: ").append(marca.getNombre())
                                .append("\n");
                    }
                    Toast.makeText(getContext(), toastMessage.toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Respuesta no exitosa", Toast.LENGTH_SHORT).show();
                    Log.d("HTTP Status Code", "Code: " + response.code());
                    Log.d("HTTP Response", "Response Code: " + response.code() + ", Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Marca>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
