package faridnet.com.pesquisaapp.async.asyncConcorrente;

import android.os.AsyncTask;

import faridnet.com.pesquisaapp.models.Concorrente;
import faridnet.com.pesquisaapp.persistence.ConcorrenteDao;

public class InsertAsyncTaskConcorrente extends AsyncTask<Concorrente, Void, Void> {

    private ConcorrenteDao mConcorrenteDao;

    public InsertAsyncTaskConcorrente(ConcorrenteDao mConcorrenteDao) {
        this.mConcorrenteDao = mConcorrenteDao;
    }

    @Override
    protected Void doInBackground(Concorrente... concorrentes) {

        mConcorrenteDao.insertConcorrente(concorrentes);
        return null;
    }

}
