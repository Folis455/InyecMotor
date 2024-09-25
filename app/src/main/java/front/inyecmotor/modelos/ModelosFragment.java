package front.inyecmotor.modelos;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import front.inyecmotor.ApiService;
import front.inyecmotor.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelosFragment extends Fragment {

    private static final String BASE_URL = "http://192.168.0.8:8080"; // Cambia a la URL de tu servidor

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modelos_fragment, container, false);

        Button getModelosButton = view.findViewById(R.id.getModelos);
        if (getModelosButton != null) {
            getModelosButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fetchModelos();
                }
            });
        }

        return view;
    }

    private void fetchModelos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Modelo>> call = apiService.getModelos();

        call.enqueue(new Callback<List<Modelo>>() {
            @Override
            public void onResponse(Call<List<Modelo>> call, Response<List<Modelo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Modelo> modelos = response.body();

                    // Abre la actividad de modelos y pasa la lista de modelos
                    Intent intent = new Intent(getActivity(), ModelosActivity.class);
                    intent.putParcelableArrayListExtra("modelos", new ArrayList<>(modelos));
                    startActivity(intent);

                } else {
                    Toast.makeText(getContext(), "Respuesta no exitosa", Toast.LENGTH_SHORT).show();
                    Log.d("HTTP Status Code", "Code: " + response.code());
                    Log.d("HTTP Response", "Response Code: " + response.code() + ", Message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Modelo>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
