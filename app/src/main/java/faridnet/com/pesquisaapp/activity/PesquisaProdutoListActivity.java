package faridnet.com.pesquisaapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.adapters.PesquisaProdutoRecyclerAdapter;
import faridnet.com.pesquisaapp.models.Concorrente;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.models.PesquisaProduto;
import faridnet.com.pesquisaapp.persistence.PesquisaProdutoRepository;
import faridnet.com.pesquisaapp.persistence.PesquisaRepository;
import faridnet.com.pesquisaapp.util.Utility;
import faridnet.com.pesquisaapp.util.VerticalSpacingItemDecorator;

public class PesquisaProdutoListActivity extends AppCompatActivity
        implements PesquisaProdutoRecyclerAdapter.OnPesquisaProdutoListener,
        PesquisaProdutoRecyclerAdapter.OnLongPesquisaProdutoClick,
        View.OnClickListener {

    private static final String TAG = "PesquisaActivity";

    //Ui Components
    private RecyclerView mRecyclerView;
    private ImageButton imgButton;

    //Vars
    private ArrayList<PesquisaProduto> mPesquisaProduto = new ArrayList<>();
    private PesquisaProdutoRecyclerAdapter mPesquisaProdutoRecyclerDapter;


    //DB
    private PesquisaProdutoRepository mPesquisaProdutoRepository;

    private Pesquisa mPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_produto_list);

        //imgButton = findViewById(R.id.back_arrow);
        //RecyclerView---
        mRecyclerView = findViewById(R.id.pesquisa_produto_recyclerview);

        mPesquisaProdutoRepository = new PesquisaProdutoRepository(this);

        //Actionbar
        findViewById(R.id.fab_pesquisaProdutoList).setOnClickListener(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_PesquisaProduto));
        setTitle("Pesquisa Produto");
        getSupportActionBar().setIcon(R.drawable.mylogo);

        // Recebendo o ID da classe PEsquisa através do extra
        if (getIntent().hasExtra("pesquisa")) {
            Intent intent = getIntent();
            mPesquisa = intent.getParcelableExtra("pesquisa");

            initRecyclerView();
            retrievePesquisaProduto(mPesquisa.getID());

//            imgButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onBackPressed();
//                }
//            });


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // new intent to call an activity that you choose
        Intent intent = new Intent(this, PesquisaListActivity.class);
        startActivity(intent);
        // finish the activity picture
        this.finish();
    }

//    private void insertFakenotes() {
//        for (int i = 0; i < 1000; i++) {
//            PesquisaProduto pesquisaProduto = new PesquisaProduto();
//            pesquisaProduto.setCodBarras("Cod. Barras #" + i);
//            pesquisaProduto.setPreco("Preço R$:" + i);
//            mPesquisaProduto.add(pesquisaProduto);
//        }
//
//        mPesquisaProdutoRecyclerDapter.notifyDataSetChanged();
//    }

    private void retrievePesquisaProduto(int pesquisaID) {
        mPesquisaProdutoRepository.retrivePesquisaProdutoTask(pesquisaID).observe((LifecycleOwner) this, new Observer<List<PesquisaProduto>>() {
            @Override
            public void onChanged(List<PesquisaProduto> pesquisaProdutos) {

                if (mPesquisaProduto.size() > 0) {
                    mPesquisaProduto.clear();
                }

                if (pesquisaProdutos != null) {
                    mPesquisaProduto.addAll(pesquisaProdutos);
                }

                mPesquisaProdutoRecyclerDapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //Para dar o espaçamento entre os cards do layout de pesquisa usa o ItemDecorator
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        mPesquisaProdutoRecyclerDapter = new PesquisaProdutoRecyclerAdapter(mPesquisaProduto, this, this);
        mRecyclerView.setAdapter(mPesquisaProdutoRecyclerDapter);
    }

    private void deletePesquisaProduto(PesquisaProduto pesquisaProduto) {
        mPesquisaProduto.remove(pesquisaProduto);
        mPesquisaProdutoRecyclerDapter.notifyDataSetChanged();
        mPesquisaProdutoRepository.deletePesquisaProduto(pesquisaProduto);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, EditPesquisaProdutoActivity.class);
        intent.putExtra("pesquisa", mPesquisa);
        startActivity(intent);
    }

    @Override
    public void onPesquisaProdutoClick(int position) {

    }

    @Override
    public void onLongPesquisaProdutoClick(final int position) {
        //Toast.makeText(this, "ON LONG CLICK LISTENER!", Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(PesquisaProdutoListActivity.this);
        builder.setTitle("Deletar Item da pesquisa");
        builder.setMessage("Você tem certeza que deseja deletar o item da pesquisa?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),
                        "Item da pesquisa deletado", Toast.LENGTH_SHORT).show();
                deletePesquisaProduto(mPesquisaProduto.get(position));
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked
                Toast.makeText(getApplicationContext(),
                        "Deleção cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

