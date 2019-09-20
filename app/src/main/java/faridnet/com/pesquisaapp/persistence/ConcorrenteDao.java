package faridnet.com.pesquisaapp.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import faridnet.com.pesquisaapp.models.Concorrente;

@Dao
public interface ConcorrenteDao {

    @Insert
    long[] insertConcorrente(Concorrente... concorrente);


    @Query("SELECT * FROM Concorrente where ID = :id")
    public Concorrente getById(int id);

}

