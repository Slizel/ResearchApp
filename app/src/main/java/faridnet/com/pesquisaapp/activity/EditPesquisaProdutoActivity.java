package faridnet.com.pesquisaapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.view.KeyEvent;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.dialogs.ValidationDialog;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.models.PesquisaProduto;
import faridnet.com.pesquisaapp.persistence.PesquisaProdutoRepository;
import faridnet.com.pesquisaapp.util.MoneyTextWatcher;

public class EditPesquisaProdutoActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private static final String TAG = "ConcorrenteActivity";

    //ui
    private EditText codBarras;
    private EditText preco;
    // private Button saveButton;

    //varr
    private Pesquisa mPesquisa;

    //DB
    private PesquisaProdutoRepository mPesquisaProdutoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pesquisa_produto);
     //   findViewById(R.id.id_salvar).setOnClickListener(this); //Referenciando o Floating action button
        codBarras = findViewById(R.id.edit_codBarras);
        preco = findViewById(R.id.edit_preco);
        preco.addTextChangedListener(new MoneyTextWatcher(preco));
        //saveButton = findViewById(R.id.id_salvar);

        codBarras.addTextChangedListener(editPesquisaWatcher);
        preco.addTextChangedListener(editPesquisaWatcher);
        preco.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN) {

                    if (codBarras.getText().toString().isEmpty() || preco.getText().toString().isEmpty()) {
                        //openDialog();
                    } else {
                        PesquisaProduto pesquisaProduto = new PesquisaProduto(mPesquisa.getID(),
                                codBarras.getText().toString(),
                                preco.getText().toString());

                        mPesquisaProdutoRepository.insertPesquisaProdutoTask(pesquisaProduto);
                        onBackPressed();

                        //Toast.makeText(this, "Item gravado!", Toast.LENGTH_LONG).show();
                    }

                    return true;
                }

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

    public void openDialog() {
        ValidationDialog validationDialog = new ValidationDialog();
        validationDialog.show(getSupportFragmentManager(), "validation dialog");
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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

//        if(codBarras.getText().toString().isEmpty() || preco.getText().toString().isEmpty()) {
//            Toast.makeText(this, "Os campos precisam ser preenchidos!", Toast.LENGTH_LONG).show();
//        } else {
//            PesquisaProduto pesquisaProduto = new PesquisaProduto(mPesquisa.getID(),
//                    codBarras.getText().toString(),
//                    preco.getText().toString());
//
//            mPesquisaProdutoRepository.insertPesquisaProdutoTask(pesquisaProduto);
//            onBackPressed();
//
//            Toast.makeText(this, "Item gravado!", Toast.LENGTH_LONG).show();
//        }
    }
}