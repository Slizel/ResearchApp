package faridnet.com.pesquisaapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import faridnet.com.pesquisaapp.models.Pesquisa;

@Dao
public interface PesquisaDao {

   @Insert
    long[] insertPesquisa(Pesquisa... pesquisas);

    @Insert
    long insert(Pesquisa pesquisas);

    @Query("SELECT * FROM pesquisas")
    LiveData<List<Pesquisa>> getPesquisa();

    @Delete
    int delete(Pesquisa... pesquisas);

    @Query("Select * from pesquisas where ID = :Id")
   Pesquisa getById(int Id);
}
