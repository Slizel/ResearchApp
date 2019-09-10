package faridnet.com.pesquisaapp.persistence;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import faridnet.com.pesquisaapp.models.PesquisaProduto;

@Dao
public interface PesquisaProdutoDao {

    @Insert
    long[] insertPesquisaProduto(PesquisaProduto... pesquisaProduto);

    @Query("SELECT * FROM pesquisaproduto WHERE pesquisaID = :pesquisaID")
    LiveData<List<PesquisaProduto>> getPesquisaProduto(int pesquisaID);

    @Delete()
    int delete(PesquisaProduto... pesquisaProduto);

}
