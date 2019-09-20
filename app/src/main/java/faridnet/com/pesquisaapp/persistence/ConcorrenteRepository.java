package faridnet.com.pesquisaapp.persistence;

import android.content.Context;

import faridnet.com.pesquisaapp.async.asyncConcorrente.InsertAsyncTaskConcorrente;
import faridnet.com.pesquisaapp.models.Concorrente;

public class ConcorrenteRepository {

    private PesquisaDatabase mConcorrente;

    public ConcorrenteRepository(Context context) {
        mConcorrente = PesquisaDatabase.getInstance(context);
    }

    public void insertConcorrenteTask(Concorrente concorrente) {
         new InsertAsyncTaskConcorrente((ConcorrenteDao) mConcorrente.getConcorrenteDao()).execute(concorrente);
    }
}
