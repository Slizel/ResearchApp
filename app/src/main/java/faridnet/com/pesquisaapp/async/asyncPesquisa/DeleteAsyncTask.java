package faridnet.com.pesquisaapp.async.asyncPesquisa;

import android.os.AsyncTask;
import android.util.Log;

import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.persistence.PesquisaDao;

public class DeleteAsyncTask extends AsyncTask<Pesquisa, Void, Void> {

    private static final String TAG = "InsertAsyncTask";

    private PesquisaDao mPesquisaDao;

    public DeleteAsyncTask(PesquisaDao dao) {
        mPesquisaDao = dao;
    }

    @Override
    protected Void doInBackground(Pesquisa... pesquisas) {
        Log.d(TAG, "doInBackground: thread:" + Thread.currentThread().getName());
        mPesquisaDao.delete(pesquisas);
        return null;
    }
}
