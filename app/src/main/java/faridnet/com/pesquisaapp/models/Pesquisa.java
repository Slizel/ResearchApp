package faridnet.com.pesquisaapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

import faridnet.com.pesquisaapp.util.Utility;

@Entity(tableName = "Pesquisas")

public class Pesquisa implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private int ID;

    @NonNull
    @ColumnInfo(name = "ConcorrenteID")
    //@ForeignKey(entity = Concorrente.class, childColumns = "ConcorrenteID", parentColumns = "ID")
    private int ConcorrenteID;

    @NonNull
    @ColumnInfo(name = "Data")
    private String Data;

    @NonNull
    @ColumnInfo(name = "Syncronized")
    private boolean Sync;

    @ColumnInfo(name ="ConcorrenteNome")
    private String ConcorrenteNome;

    public Pesquisa(String ConcorrenteNome) {

        //String Date = Utility.getCurrentTimestamp();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //dd-MM-yyyy HH:mm"
        String currentDateTime = dateFormat.format(new Date());

        Date currentTime = Calendar.getInstance().getTime();

        //this.ConcorrenteID = ConcorrenteID;
        this.ConcorrenteNome = ConcorrenteNome;
        this.Data = currentDateTime;
        this.Sync = false;

    }

    @Ignore
    public Pesquisa() {
    }

    protected Pesquisa(Parcel in) {
        ID = in.readInt();
        ConcorrenteID = in.readInt();
        Data = in.readString();
        Sync = in.readByte() != 0;
        ConcorrenteNome = in.readString();
    }

    public static final Creator<Pesquisa> CREATOR = new Creator<Pesquisa>() {
        @Override
        public Pesquisa createFromParcel(Parcel in) {
            return new Pesquisa(in);
        }

        @Override
        public Pesquisa[] newArray(int size) {
            return new Pesquisa[size];
        }
    };

    @NonNull
    public String getConcorrenteNome() {
        return ConcorrenteNome;
    }

    public void setConcorrenteNome(@NonNull String concorrenteNome) {
        ConcorrenteNome = concorrenteNome;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getConcorrenteID() {
        return ConcorrenteID;
    }

    public void setConcorrenteID(int concorrenteID) {
        ConcorrenteID = concorrenteID;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public boolean isSync() {
        return Sync;
    }

    public void setSync(boolean sync) {
        Sync = sync;
    }

    @Override
    public String toString() {
        return "Pesquisa{" +
                "ID=" + ID +
                ", ConcorrenteID=" + ConcorrenteID +
                ", Data='" + Data + '\'' +
                ", Sync=" + Sync +
                ", ConcorrenteNome='" + ConcorrenteNome + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeInt(ConcorrenteID);
        parcel.writeString(Data);
        parcel.writeByte((byte) (Sync ? 1 : 0));
        parcel.writeString(ConcorrenteNome);
    }
}
