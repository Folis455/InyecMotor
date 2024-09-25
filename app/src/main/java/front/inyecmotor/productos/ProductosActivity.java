package front.inyecmotor.productos;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import front.inyecmotor.R;

public class ProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AutoCompleteTextView searchBar;
    private Spinner spinnerTipoProducto;
    private List<Producto> allProductos;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos_activity);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        spinnerTipoProducto = findViewById(R.id.spinnerTipoProducto);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allProductos = getIntent().getParcelableArrayListExtra("productos");
        adapter = new ProductoAdapter(allProductos, this);
        recyclerView.setAdapter(adapter);

        setupSearchBar();
        setupSpinner();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupSearchBar() {
        List<String> marcas = allProductos.stream()
                .map(Producto::getMarca)
                .distinct()
                .collect(Collectors.toList());

        ArrayAdapter<String> searchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, marcas);
        searchBar.setAdapter(searchAdapter);

        searchBar.setOnItemClickListener((parent, view, position, id) -> {
            filterProductos();
        });
    }

    private void setupSpinner() {
        List<String> tiposProducto = allProductos.stream()
                .map(Producto::getTipoProducto)
                .filter(tipo -> tipo != null) // Filtrar tipos nulos
                .distinct()
                .collect(Collectors.toList());
        tiposProducto.add(0, "Todos los tipos");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposProducto);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoProducto.setAdapter(spinnerAdapter);

        spinnerTipoProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                filterProductos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void filterProductos() {
        String selectedMarca = searchBar.getText().toString();
        String selectedTipo = spinnerTipoProducto.getSelectedItem().toString();

        List<Producto> filteredProductos = allProductos.stream()
                .filter(p -> selectedMarca.isEmpty() || p.getMarca().equals(selectedMarca))
                .filter(p -> selectedTipo.equals("Todos los tipos") || p.getTipoProducto().equals(selectedTipo))
                .collect(Collectors.toList());

        adapter.updateList(filteredProductos);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}