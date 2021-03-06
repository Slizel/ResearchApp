package faridnet.com.pesquisaapp.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Concorrente {

    @PrimaryKey()
    private int ID;

    @NonNull
    @ColumnInfo(name = "Nome")
    private String Nome;


    public Concorrente(int ID, String Nome) {
        this.ID = ID;
        this.Nome = Nome;
    }

    @Ignore
    public Concorrente(int Id) {
        this.ID = Id;
    }

    @Ignore
    public Concorrente(String Nome) {
        this.Nome = Nome;
    }

    @Ignore
    public Concorrente() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    @Override
    public String toString() {
        return Nome;
    }
}
