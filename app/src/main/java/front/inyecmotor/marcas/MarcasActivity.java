package front.inyecmotor.marcas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import front.inyecmotor.R;

public class MarcasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marcas_activity);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtén la lista de marcas de la intención
        List<Marca> marcas = getIntent().getParcelableArrayListExtra("marcas");

        // Configura el adaptador
        MarcaAdapter adapter = new MarcaAdapter(marcas, this);

        recyclerView.setAdapter(adapter);
    }
}
