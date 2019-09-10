package faridnet.com.pesquisaapp.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Concorrente {

    @PrimaryKey(autoGenerate = true)
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
    public Concorrente(@NonNull String nome) {
        Nome = nome;
    }

    @Ignore
    public Concorrente() {
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        ID = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    @Override
    public String toString() {
        return "Concorrente{" +
                "Id=" + ID +
                ", nome='" + Nome + '\'' +
                '}';
    }

}
