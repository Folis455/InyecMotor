package front.inyecmotor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProveedoresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtén la lista de proveedores de la intención
        List<Proveedor> proveedores = getIntent().getParcelableArrayListExtra("proveedores");

        // Configura el adaptador
        ProveedorAdapter adapter = new ProveedorAdapter(proveedores, this);

        recyclerView.setAdapter(adapter);
    }
}
