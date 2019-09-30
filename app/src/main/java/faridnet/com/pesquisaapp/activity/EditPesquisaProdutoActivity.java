package faridnet.com.pesquisaapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.view.KeyEvent;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//import com.google.android.gms.vision.barcode.Barcode;
//import com.notbytes.barcode_reader.BarcodeReaderFragment;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.dialogs.ValidationDialog;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.models.PesquisaProduto;
import faridnet.com.pesquisaapp.persistence.PesquisaProdutoRepository;
import faridnet.com.pesquisaapp.util.MoneyTextWatcher;

public class EditPesquisaProdutoActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private static final String TAG = "ConcorrenteActivity";

    //UI
    ImageButton btn;
    final Activity activity= this;
    private EditText codBarras;
    private EditText preco;

    //Var
    private Pesquisa mPesquisa;

    //DB
    private PesquisaProdutoRepository mPesquisaProdutoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pesquisa_produto);
        codBarras = findViewById(R.id.edit_codBarras);
        preco = findViewById(R.id.edit_preco);
        preco.addTextChangedListener(new MoneyTextWatcher(preco));
        btn = findViewById(R.id.scan_buttom);

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
            Toast.makeText(this, "O retorno foi:" + mPesquisa, Toast.LENGTH_LONG).show();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                intentIntegrator.setOrientationLocked(false);
                intentIntegrator.setPrompt("SCAN");
                intentIntegrator.setCameraId(0);
                intentIntegrator.initiateScan();
            }
        });

    } // Fim do OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult != null){
            if (intentResult.getContents() !=  null){
                codBarras.setText(intentResult.getContents().toString());
                preco.requestFocus();
//                alert(intentResult.getContents().toString());
            }else{
                alert("Scan cancelado");
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void alert(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
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