package front.inyecmotor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtén la lista de productos de la intención
        List<Producto> productos = getIntent().getParcelableArrayListExtra("productos");

        // Configura el adaptador
        ProductoAdapter adapter = new ProductoAdapter(productos, this); // Paso el contexto this

        recyclerView.setAdapter(adapter);
    }
}
