package com.example.kosteczki;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText liczbaKostek;
    Button losuj, rosnaco, malejaco;
    GridLayout gridKostek;
    TextView wyniki;

    ArrayList<Integer> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liczbaKostek = findViewById(R.id.liczbaKostek);
        losuj = findViewById(R.id.losuj);
        rosnaco = findViewById(R.id.rosnaco);
        malejaco = findViewById(R.id.malejaco);
        gridKostek = findViewById(R.id.gridKostek);
        wyniki = findViewById(R.id.wyniki);

        losuj.setOnClickListener(v -> {

            lista.clear();

            int ile;

            try {
                ile = Integer.parseInt(liczbaKostek.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this, "Podaj liczbe", Toast.LENGTH_SHORT).show();
                return;
            }

            if (ile < 1 || ile > 100) {
                Toast.makeText(this, "Podaj liczbe od 1 do 100", Toast.LENGTH_SHORT).show();
                return;
            }

            Random r = new Random();

            for (int i = 0; i < ile; i++) {
                lista.add(r.nextInt(6) + 1);
            }

            pokaz();
        });

        rosnaco.setOnClickListener(v -> {
            sortuj(true);
        });

        malejaco.setOnClickListener(v -> {
            sortuj(false);
        });
    }

    void pokaz() {

        gridKostek.removeAllViews();

        String tekst = "";

        for (int i = 0; i < lista.size(); i++) {

            TextView kostka = new TextView(this);
            kostka.setText("🎲 " + lista.get(i));
            kostka.setTextSize(22);
            kostka.setGravity(Gravity.CENTER);

            GridLayout.LayoutParams p = new GridLayout.LayoutParams();
            p.width = 0;
            p.height = 65;
            p.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            p.setMargins(4, 4, 4, 4);

            kostka.setLayoutParams(p);
            gridKostek.addView(kostka);

            tekst += lista.get(i) + " ";
        }

        wyniki.setText(tekst);
    }

    void sortuj(boolean rosnaco) {

        if (lista.isEmpty()) {
            Toast.makeText(this, "Najpierw losuj", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < lista.size(); i++) {

            for (int j = 0; j < lista.size() - 1; j++) {

                if ((rosnaco && lista.get(j) > lista.get(j + 1)) ||
                        (!rosnaco && lista.get(j) < lista.get(j + 1))) {

                    int pomoc = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, pomoc);
                }
            }
        }

        pokaz();
    }
}
