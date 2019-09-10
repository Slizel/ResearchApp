package faridnet.com.pesquisaapp.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import faridnet.com.pesquisaapp.async.asyncPesquisa.DeleteAsyncTask;
import faridnet.com.pesquisaapp.async.asyncPesquisa.InsertAsyncTask;
import faridnet.com.pesquisaapp.models.Pesquisa;

public class PesquisaRepository {

    private PesquisaDatabase mPesquisaDatabese;

    public PesquisaRepository(Context context) {
        mPesquisaDatabese = PesquisaDatabase.getInstance(context);
    }

    public void insertPesquisaTask(Pesquisa pesquisa) {
        new InsertAsyncTask(mPesquisaDatabese.getPesquisaDao()).execute(pesquisa);
    }


    public LiveData<List<Pesquisa>> retrivePesquisaTask() {
        return mPesquisaDatabese.getPesquisaDao().getPesquisa();
    }

    public void deletePesquisa(Pesquisa pesquisa) {

        new DeleteAsyncTask(mPesquisaDatabese.getPesquisaDao()).execute(pesquisa);
    }

}
