package faridnet.com.pesquisaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.view.KeyEvent;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.models.PesquisaProduto;
import faridnet.com.pesquisaapp.persistence.PesquisaProdutoRepository;

public class EditPesquisaProdutoActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private static final String TAG = "ConcorrenteActivity";

    //ui
    private EditText codBarras;
    private EditText preco;
    private Button saveButton;

    //varr
    private Pesquisa mPesquisa;

    //DB
    private PesquisaProdutoRepository mPesquisaProdutoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pesquisa_produto);
        findViewById(R.id.id_salvar).setOnClickListener(this); //Referenciando o Floating action button
        codBarras = findViewById(R.id.edit_codBarras);
        preco = findViewById(R.id.edit_preco);
        saveButton = findViewById(R.id.id_salvar);

        codBarras.addTextChangedListener(editPesquisaWatcher);
        preco.addTextChangedListener(editPesquisaWatcher);
        preco.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {


                return false;
            }
        });

        mPesquisaProdutoRepository = new PesquisaProdutoRepository(this);
        // Recebendo o ID da classe PEsquisa através do extra
        if (getIntent().hasExtra("pesquisa")) {
            mPesquisa = getIntent().getParcelableExtra("pesquisa");
            //Toast.makeText(this, "O retorno foi:" + mPesquisa, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    private TextWatcher editPesquisaWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {


        }
    };


    @Override
    public void onClick(View view) {

        if(codBarras.getText().toString().isEmpty() || preco.getText().toString().isEmpty()) {
            Toast.makeText(this, "Os campos precisam ser preenchidos!", Toast.LENGTH_LONG).show();
        } else {
            PesquisaProduto pesquisaProduto = new PesquisaProduto(mPesquisa.getID(),
                    codBarras.getText().toString(),
                    preco.getText().toString());

            mPesquisaProdutoRepository.insertPesquisaProdutoTask(pesquisaProduto);
            Toast.makeText(this, "Item gravado!", Toast.LENGTH_LONG).show();
        }
    }
}