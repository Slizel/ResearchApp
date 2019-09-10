package faridnet.com.pesquisaapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import faridnet.com.pesquisaapp.models.Pesquisa;

@Dao
public interface PesquisaDao {

    //ROOM PERSISTENCE DATA ACCESS OBJECTS

    @Insert
    long[] insertPesquisa(Pesquisa... pesquisas);

    @Insert
    long insertPesquisa(Pesquisa pesquisa);

    @Query("SELECT * FROM pesquisas")
    LiveData<List<Pesquisa>> getPesquisa();

    //Exemplo de query custumizada
    //@Query("SELECT * FROM pesquisas WHERE ID = :ID")
    //List<Pesquisa> getPesquisaWithCustomQuery();

    //getPesquisaWithCustomQuery("eli*")

    @Delete
    int delete(Pesquisa... pesquisas);

    @Update
    int update(Pesquisa... pesquisas);


}
