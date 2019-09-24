package faridnet.com.pesquisaapp.persistence;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import faridnet.com.pesquisaapp.models.Concorrente;

@Dao
public interface ConcorrenteDao {

    @Insert
    long[] insertConcorrente(Concorrente... concorrente);


    @Query("SELECT * FROM Concorrente")
    List<Concorrente> getAll();



    @Query("SELECT * FROM Concorrente where ID = :id")
    Concorrente getById(int id);






}

