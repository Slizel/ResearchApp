package faridnet.com.pesquisaapp.async.asyncPesquisa;

import android.os.AsyncTask;
import android.util.Log;

import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.persistence.PesquisaDao;
import faridnet.com.pesquisaapp.persistence.PesquisaDatabase;

public class InsertAsyncTask extends AsyncTask<Pesquisa, Void, Void> {

//    private int insertId = -1;
//    private Pesquisa pesquisa;
//    private PesquisaDatabase pesquisaDatabase;

    private PesquisaDao mPesquisaDao;

    public InsertAsyncTask(PesquisaDao dao) {
        mPesquisaDao = dao;
    }

    @Override
    protected Void doInBackground(Pesquisa... pesquisas) {

        //pesquisa = pesquisas[0];
        //insertId = (int) pesquisaDatabase.getPesquisaDao().insertPesquisa(pesquisa);

         mPesquisaDao.insertPesquisa(pesquisas);
         return null;

    }

//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//        onPesquisaInserted(insertId, pesquisa);
//    }
//
//
//    void onPesquisaInserted(int id, Pesquisa pesquisa){
//
//    }
}
