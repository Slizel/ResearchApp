package faridnet.com.pesquisaapp.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.models.Concorrente;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.persistence.PesquisaDao;
import faridnet.com.pesquisaapp.persistence.PesquisaRepository;

public class ConcorrenteSpinnerActivity extends AppCompatActivity
        implements View.OnClickListener {


    private static final String TAG = "ConcorrenteSpinnerActiv";

    private long[] getPesquisaId;
    private PesquisaDao PesquisaId;

    private String[] concorrenteNome = new String[]{"Supermercado BH", "Supermercado EPA", "Supermercado Dia"};
    private int[] concorrenteImg = {R.drawable.bh_supermercado, R.drawable.epa_supermercado, R.drawable.dia_supermercado};
    private Spinner sp;
    private ImageView iv;

    // Id do concorrente
    private Concorrente mConcorrente = new Concorrente(1);
    private Concorrente mConcorrenteNome = new Concorrente("placeholder");

    // DB
    private PesquisaRepository mPesquisaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concorrente_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, concorrenteNome);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //BD instanciando o repositorio
        mPesquisaRepository = new PesquisaRepository(this);


        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_spinner));
        //setTitle("Concorrente");
        getSupportActionBar().setIcon(R.drawable.mylogo);

        findViewById(R.id.fab_spinner).setOnClickListener(this);

        iv = (ImageView) findViewById(R.id.verImagem);

        sp = (Spinner) findViewById(R.id.concorrente_spinner);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iv.setImageResource(concorrenteImg[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }// end oncreate

    public void showElement(View view) {

        String nome = (String) sp.getSelectedItem();
        long id = sp.getSelectedItemId();
        int posicao = sp.getSelectedItemPosition();

        Toast.makeText(this, "Concorrente: " + nome + " -> Id: " + id + " -> Posição: " + posicao, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        int posicao = sp.getSelectedItemPosition();

        if (posicao == 0) {
            //supermercado BH
            mConcorrente.setId(0);
            mConcorrenteNome.setNome("Supermercado BH");

        } else if (posicao == 1) {
            //supermercado EPA
            mConcorrente.setId(1);
            mConcorrenteNome.setNome("Supermercado EPA");
        } else {

            //supermercado DIA
            mConcorrente.setId(2);
            mConcorrenteNome.setNome("Supermercado DIA");
        }


        Pesquisa pesquisa = new Pesquisa(mConcorrente.getId());
        mPesquisaRepository.insertPesquisaTask(pesquisa);


        Intent intent = new Intent(this, PesquisaListActivity.class);
        intent.putExtra("concorrente_id", mConcorrente.getId());
        Log.d(TAG, "onCreate: " + mConcorrente.toString());
        startActivity(intent);
    }
}
