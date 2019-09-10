package faridnet.com.pesquisaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class PesquisaProduto implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "PesquisaID")
    private int PesquisaID;

    @ColumnInfo(name = "ProdutoID")
    private int ProdutoID;

    @ColumnInfo(name = "CodigoDeBarras")
    private String CodBarras;

    @ColumnInfo(name = "Preço")
    private String Preco;

    @ColumnInfo(name = "Data")
    private String Data;

    @ColumnInfo(name = "Solicitado")
    private boolean Solicitado;

    public PesquisaProduto(int PesquisaID, int ProdutoID, String CodBarras, String Preco, String Data, boolean Solicitado) {
        this.PesquisaID = PesquisaID;
        this.ProdutoID = ProdutoID;
        this.CodBarras = CodBarras;
        this.Preco = Preco;
        this.Data = Data;
        this.Solicitado = Solicitado;
    }


    @Ignore
    public PesquisaProduto(int pesquisaID, String CodBarras, String Preco) {
        this.PesquisaID = pesquisaID;
        this.CodBarras = CodBarras;
        this.Preco = Preco;

    }


    @Ignore
    public PesquisaProduto() {
    }

    protected PesquisaProduto(Parcel in) {
        ID = in.readInt();
        PesquisaID = in.readInt();
        ProdutoID = in.readInt();
        CodBarras = in.readString();
        Preco = in.readString();
        Data = in.readString();
        Solicitado = in.readByte() != 0;
    }

    public static final Creator<PesquisaProduto> CREATOR = new Creator<PesquisaProduto>() {
        @Override
        public PesquisaProduto createFromParcel(Parcel in) {
            return new PesquisaProduto(in);
        }

        @Override
        public PesquisaProduto[] newArray(int size) {
            return new PesquisaProduto[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPesquisaID() {
        return PesquisaID;
    }

    public void setPesquisaID(int pesquisaID) {
        PesquisaID = pesquisaID;
    }

    public int getProdutoID() {
        return ProdutoID;
    }

    public void setProdutoID(int produtoID) {
        ProdutoID = produtoID;
    }

    public String getCodBarras() {
        return CodBarras;
    }

    public void setCodBarras(String codBarras) {
        CodBarras = codBarras;
    }

    public String getPreco() {
        return Preco;
    }

    public void setPreco(String preco) {
        Preco = preco;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public boolean isSolicitado() {
        return Solicitado;
    }

    public void setSolicitado(boolean solicitado) {
        Solicitado = solicitado;
    }

    @Override
    public String toString() {
        return "PesquisaProduto{" +
                "ID=" + ID +
                ", PesquisaID=" + PesquisaID +
                ", ProdutoID=" + ProdutoID +
                ", CodBarras='" + CodBarras + '\'' +
                ", Preco='" + Preco + '\'' +
                ", Data='" + Data + '\'' +
                ", Solicitado=" + Solicitado +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeInt(PesquisaID);
        parcel.writeInt(ProdutoID);
        parcel.writeString(CodBarras);
        parcel.writeString(Preco);
        parcel.writeString(Data);
        parcel.writeByte((byte) (Solicitado ? 1 : 0));
    }
}
