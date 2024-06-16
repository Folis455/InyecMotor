package front.inyecmotor;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProveedorAdapter extends RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder> {
    private List<Proveedor> proveedores;
    private Context context;
    private static final String BASE_URL = "http://192.168.0.8:8080";
    private static final String TAG = "ProveedorAdapter";

    public ProveedorAdapter(List<Proveedor> proveedores, Context context) {
        this.proveedores = proveedores;
        this.context = context;
    }

    @NonNull
    @Override
    public ProveedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proveedor, parent, false);
        return new ProveedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProveedorViewHolder holder, int position) {
        Proveedor proveedor = proveedores.get(position);
        holder.tvProveedorNombre.setText(proveedor.getNombre());
        holder.tvProveedorDireccion.setText(proveedor.getDireccion());

        holder.btnVerDetalle.setOnClickListener(v -> mostrarDialogoDetalle(proveedor));
    }

    @Override
    public int getItemCount() {
        return proveedores.size();
    }

    public class ProveedorViewHolder extends RecyclerView.ViewHolder {
        TextView tvProveedorNombre;
        TextView tvProveedorDireccion;
        Button btnVerDetalle;

        public ProveedorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProveedorNombre = itemView.findViewById(R.id.tvProveedorNombre);
            tvProveedorDireccion = itemView.findViewById(R.id.tvProveedorDireccion);
            btnVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
        }
    }

    private void mostrarDialogoDetalle(Proveedor proveedor) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Detalles del Proveedor");

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.dialogo_detalle_proveedor, null);
        builder.setView(viewInflated);

        EditText etNombre = viewInflated.findViewById(R.id.etNombre);
        EditText etDireccion = viewInflated.findViewById(R.id.etDireccion);

        etNombre.setText(proveedor.getNombre());
        etDireccion.setText(proveedor.getDireccion());

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            proveedor.setNombre(etNombre.getText().toString());
            proveedor.setDireccion(etDireccion.getText().toString());

            enviarDatosProveedor(proveedor);
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void enviarDatosProveedor(Proveedor proveedor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Proveedor> call = apiService.editarProveedor(proveedor);

        call.enqueue(new Callback<Proveedor>() {
            @Override
            public void onResponse(Call<Proveedor> call, Response<Proveedor> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Proveedor actualizado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al actualizar el proveedor", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Response Code: " + response.code());
                    Log.d(TAG, "Response Message: " + response.message());
                    if (response.errorBody() != null) {
                        try {
                            Log.d(TAG, "Error Body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Proveedor> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
