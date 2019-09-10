package faridnet.com.pesquisaapp.async.asyncPesquisaProduto;

import android.os.AsyncTask;

import faridnet.com.pesquisaapp.models.PesquisaProduto;
import faridnet.com.pesquisaapp.persistence.PesquisaProdutoDao;

public class InsertAsyncTaskPesquisaProduto extends AsyncTask<PesquisaProduto, Void, Void> {

private PesquisaProdutoDao mPesquisaProdutoDao;

    public InsertAsyncTaskPesquisaProduto(PesquisaProdutoDao dao) {
        mPesquisaProdutoDao = dao;
    }

    @Override
    protected Void doInBackground(PesquisaProduto... pesquisaProdutos) {
        mPesquisaProdutoDao.insertPesquisaProduto(pesquisaProdutos);
        return null;
    }
}
