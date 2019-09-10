package faridnet.com.pesquisaapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.adapters.PesquisaRecyclerAdapter;
import faridnet.com.pesquisaapp.dialogs.DeletePesquisaPermissionDialog;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.persistence.PesquisaRepository;
import faridnet.com.pesquisaapp.util.Utility;
import faridnet.com.pesquisaapp.util.VerticalSpacingItemDecorator;

public class PesquisaListActivity extends AppCompatActivity implements
        PesquisaRecyclerAdapter.OnPesquisaListener,
        View.OnClickListener,
        PesquisaRecyclerAdapter.ClickListener {

    private static final String TAG = "PesquisaListActivity";

    // UI components
    private RecyclerView mRecyclerView;

    //Vars
    private ArrayList<Pesquisa> mPesquisa = new ArrayList<>();
    private PesquisaRecyclerAdapter mPesquisaRecyclerAdapter;


    //BD
    private PesquisaRepository mPesquisaRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_list);
        mRecyclerView = findViewById(R.id.recyclerView);

        Log.d(TAG, "onCreate: thread:" + Thread.currentThread().getName());

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_Pesquisa));
        setTitle("Pesquisa");

        //Referenciando o Floating action button
        findViewById(R.id.fab).setOnClickListener(this);

        //Iniciando o BD no oncrea
        mPesquisaRepository = new PesquisaRepository(this);

        //Inicia a recyclerview
        initRecyclerView();
        retrivePesquisa();
    }

    //Método para buscar o live data no BD
    private void retrivePesquisa() {
        mPesquisaRepository.retrivePesquisaTask().observe(this, new Observer<List<Pesquisa>>() {
            @Override
            public void onChanged(List<Pesquisa> pesquisas) {
                if (mPesquisa.size() > 0) {
                    mPesquisa.clear();
                }
                if (pesquisas != null) {
                    mPesquisa.addAll(pesquisas);
                }
                mPesquisaRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

//    private void insertFakePesquisa(){
//        for(int i =0; i <1000; i++){
//            Pesquisa pesquisa = new Pesquisa(i,1);
//            mPesquisa.add(pesquisa);
//        }
//      mPesquisaRecyclerAdapter.notifyDataSetChanged();
//    }

    //------------------------------Método que junta tudo-------------------------------------------
    // Método que vai settar a lista de pesquisa no adapter e associar o adapter com a recycler view
    private void initRecyclerView() {
        //inicie uma linearlayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Settar o Layoutmanager com a recyclerview
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Para dar o espaçamento entre os cards do layout de pesquisa usa o ItemDecorator
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        //Item Touch Helper
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        //Settar o adapter com a recyclerView
        mPesquisaRecyclerAdapter = new PesquisaRecyclerAdapter(mPesquisa, this, this);
        mRecyclerView.setAdapter(mPesquisaRecyclerAdapter);

    }

    // Método sobrescrito da interface criada no PesquisaRecyclerviewAdapter para ouvir clicks. Tem que implementar
    // PesquisaRecyclerAdapter.OnPesquisaListener para usar. Busca por uma pesquisa já existente
    @Override
    public void onPesquisaClick(int position) {
        Log.d(TAG, "onPesquisaClick: clicked.");
        Toast.makeText(this, "On Pesquisa Click Clicado!", Toast.LENGTH_LONG).show();

        //Passar intent com bundle
        Intent intent = new Intent(this, PesquisaProdutoListActivity.class);
        intent.putExtra("pesquisa", mPesquisa.get(position));
        startActivity(intent);
    }

    //Float Action Buttom onClick
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ConcorrenteSpinnerActivity.class);
        startActivity(intent);
    }

    //Delete Pesquisa
    private void deletePesquisa(Pesquisa pesquisa) {
        mPesquisa.remove(pesquisa);
        mPesquisaRecyclerAdapter.notifyDataSetChanged();
        mPesquisaRepository.deletePesquisa(pesquisa);
    }


//    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
//            new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
//
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//
//            deletePesquisa(mPesquisa.get(viewHolder.getAdapterPosition()));
//        }
//    };

    @Override
    public void clickListener(final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(PesquisaListActivity.this);
        builder.setTitle("Deletar Pesquisa");
        builder.setMessage("Você tem certeza que deseja deletar a pesquisa?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),
                        "Pesquisa Deletada", Toast.LENGTH_SHORT).show();
                deletePesquisa(mPesquisa.get(position));
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked
                Toast.makeText(getApplicationContext(),
                        "Deleção Cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
