package front.inyecmotor;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private List<Producto> productos;
    private Context context;

    public ProductoAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.tvProductoNombre.setText(producto.getNombre());
        holder.tvProductoPrecio.setText("Precio: $" + producto.getPrecioCosto());

        holder.btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoDetalle(producto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductoNombre;
        TextView tvProductoPrecio;
        Button btnVerDetalle;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductoNombre = itemView.findViewById(R.id.tvProductoNombre);
            tvProductoPrecio = itemView.findViewById(R.id.tvProductoPrecio);
            btnVerDetalle = itemView.findViewById(R.id.btnVerDetalle);
        }
    }

    private void mostrarDialogoDetalle(Producto producto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Detalles del Producto");

        View viewInflated = LayoutInflater.from(context).inflate(R.layout.dialogo_detalle_producto, null);
        builder.setView(viewInflated);

        EditText etNombre = viewInflated.findViewById(R.id.etNombre);
        EditText etPrecioCosto = viewInflated.findViewById(R.id.etPrecioCosto);
        EditText etPrecioVenta = viewInflated.findViewById(R.id.etPrecioVenta);
        EditText etStockActual = viewInflated.findViewById(R.id.etStockActual);

        // Setear valores iniciales del producto
        etNombre.setText(producto.getNombre());
        etPrecioCosto.setText(String.valueOf(producto.getPrecioCosto()));
        etPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
        etStockActual.setText(String.valueOf(producto.getStockActual()));

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            // Guardar cambios del producto
            producto.setNombre(etNombre.getText().toString());
            producto.setPrecioCosto(Double.parseDouble(etPrecioCosto.getText().toString()));
            producto.setPrecioVenta(Double.parseDouble(etPrecioVenta.getText().toString()));
            producto.setStockActual(Integer.parseInt(etStockActual.getText().toString()));
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}
