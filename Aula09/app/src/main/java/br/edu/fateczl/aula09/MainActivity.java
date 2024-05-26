package br.edu.fateczl.aula09;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            carregaFragment(bundle);
        }
    }

    private void carregaFragment(Bundle bundle) {
        String tipoAtleta = bundle.getString("tipoAtleta");
        if (tipoAtleta != null) {
            if (tipoAtleta.equals("Juvenil")) {
                fragment = new JuvenilFragment();
            } else if (tipoAtleta.equals("Senior")) {
                fragment = new SeniorFragment();
            } else {
                fragment = new OutroFragment();
            }
            //Carregando fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment);
            fragmentTransaction.commit();
        }else {
            Log.e("TypeError", "Tipo de atleta é nulo.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this, MainActivity.class);

        if(id == R.id.item_juvenil){
            bundle.putString("tipoAtleta", "Juvenil");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }

        if(id == R.id.item_senior){
            bundle.putString("tipoAtleta", "Senior");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }

        if(id == R.id.item_outro){
            bundle.putString("tipoAtleta", "Outro");
            intent.putExtras(bundle);
            this.startActivity(intent);
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}