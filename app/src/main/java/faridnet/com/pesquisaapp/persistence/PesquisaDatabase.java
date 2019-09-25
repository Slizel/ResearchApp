package faridnet.com.pesquisaapp.persistence;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import faridnet.com.pesquisaapp.adapters.PesquisaRecyclerAdapter;
import faridnet.com.pesquisaapp.models.Concorrente;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.models.PesquisaProduto;

@Database(entities = {Pesquisa.class, PesquisaProduto.class, Concorrente.class}, version = 1)
public abstract class PesquisaDatabase extends RoomDatabase {

    public static PesquisaDatabase instance;

    //Criar referencia para o Dao
    public abstract PesquisaDao getPesquisaDao();
    public abstract PesquisaProdutoDao getPesquisaProdutoDao();
    public abstract ConcorrenteDao getConcorrenteDao();

    public static final String DATABASE_NAME = "PesquisaApp.db";

//    @VisibleForTesting
//    static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            database.execSQL("insert into Concorrente (ID, Nome) values (1,'BH')");
//            database.execSQL("insert into Concorrente (ID, Nome) values (2,'EPA')");
//            database.execSQL("insert into Concorrente (ID, Nome) values (3,'DIA')");
//        }
//    };

    public static PesquisaDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PesquisaDatabase.class, DATABASE_NAME).addCallback(CALLBACK)
                    .allowMainThreadQueries()
                   // .addMigrations(MIGRATION_1_2)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback CALLBACK =  new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("##### Migration", "started....");
             db.execSQL("insert into Concorrente (ID, Nome) values (1,'BH')");
             db.execSQL("insert into Concorrente (ID, Nome) values (2,'EPA')");
             db.execSQL("insert into Concorrente (ID, Nome) values (3,'DIA')");
            Log.d("##### Migration", "completed....");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
