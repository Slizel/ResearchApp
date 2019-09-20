package faridnet.com.pesquisaapp.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import faridnet.com.pesquisaapp.async.asyncPesquisaProduto.DeleteAsyncTaskPesquisaProduto;
import faridnet.com.pesquisaapp.async.asyncPesquisaProduto.InsertAsyncTaskPesquisaProduto;
import faridnet.com.pesquisaapp.models.PesquisaProduto;

public class PesquisaProdutoRepository {


    private PesquisaDatabase mPesquisaProdutoDatabase;

    // Referência do singleton
    public PesquisaProdutoRepository(Context context) {
        mPesquisaProdutoDatabase = PesquisaDatabase.getInstance(context);
    }

    public void insertPesquisaProdutoTask(PesquisaProduto pesquisaProduto) {
        new InsertAsyncTaskPesquisaProduto(mPesquisaProdutoDatabase.getPesquisaProdutoDao()).execute(pesquisaProduto);
    }

    public LiveData<List<PesquisaProduto>> retrivePesquisaProdutoTask(int pesquisaID) {
        //Referencia o getPesquisaProduto do DAO que retorna um objeto LiveData que contem uma lista com todas a notas do DB
        return mPesquisaProdutoDatabase.getPesquisaProdutoDao().getPesquisaProduto(pesquisaID);
    }

    public void deletePesquisaProduto(PesquisaProduto pesquisaProduto) {
        new DeleteAsyncTaskPesquisaProduto(mPesquisaProdutoDatabase.getPesquisaProdutoDao()).execute(pesquisaProduto);
    }

}
