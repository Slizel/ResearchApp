package faridnet.com.pesquisaapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.models.PesquisaProduto;

@Database(entities = {Pesquisa.class, PesquisaProduto.class}, version = 2)
public abstract class PesquisaDatabase extends RoomDatabase {

    // DB name
    public static final String DATABASE_NAME = "PesquisaApp";

    //Padrão Singleton
    public static PesquisaDatabase instance;

    //DB Constructor
    static PesquisaDatabase getInstance(final Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PesquisaDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    //Criar referencia para o Dao
    public abstract PesquisaDao getPesquisaDao();

    public abstract PesquisaProdutoDao getPesquisaProdutoDao();
}
