package faridnet.com.pesquisaapp.async.asyncPesquisaProduto;

import android.os.AsyncTask;

import faridnet.com.pesquisaapp.models.PesquisaProduto;
import faridnet.com.pesquisaapp.persistence.PesquisaProdutoDao;

public class DeleteAsyncTaskPesquisaProduto extends AsyncTask<PesquisaProduto, Void, Void> {

    private PesquisaProdutoDao mPesquisaProdutoDao;

    public DeleteAsyncTaskPesquisaProduto(PesquisaProdutoDao dao) {
        this.mPesquisaProdutoDao = dao;
    }

    @Override
    protected Void doInBackground(PesquisaProduto... pesquisaProdutos) {
        mPesquisaProdutoDao.delete(pesquisaProdutos);
        return null;
    }
}
