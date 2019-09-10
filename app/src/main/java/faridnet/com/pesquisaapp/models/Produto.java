package faridnet.com.pesquisaapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Produto {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @NonNull
    @ColumnInfo(name = "Descricao")
    private String Descricao;

    @NonNull
    @ColumnInfo(name = "EAN13")
    private String EAN13;

    public Produto(int ID, String Descricao, String EAN13) {
        this.ID = ID;
        this.Descricao = Descricao;
        this.EAN13 = EAN13;
    }

    @Ignore
    public Produto() {
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        ID = id;
    }

    @NonNull
    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(@NonNull String descricao) {
        Descricao = descricao;
    }

    public String getEAN13() {
        return EAN13;
    }

    public void setEAN13(String EAN13) {
        this.EAN13 = EAN13;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + ID +
                ", Descrição='" + Descricao + '\'' +
                ", EAN13='" + EAN13 + '\'' +
                '}';
    }
}
