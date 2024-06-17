package front.inyecmotor.productos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import front.inyecmotor.R;

public class ProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtén la lista de productos de la intención
        List<Producto> productos = getIntent().getParcelableArrayListExtra("productos");

        // Configura el adaptador
        ProductoAdapter adapter = new ProductoAdapter(productos, this); // Paso el contexto this

        recyclerView.setAdapter(adapter);
    }
}
