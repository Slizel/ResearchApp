package faridnet.com.pesquisaapp.adapters;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.models.Pesquisa;
import faridnet.com.pesquisaapp.persistence.PesquisaRepository;

public class PesquisaRecyclerAdapter extends RecyclerView.Adapter<PesquisaRecyclerAdapter.ViewHolder> {

    private static final String TAG = "PesquisaRecyclerAdapter";


    //É necessário criar uma estrutura de dado para comportar cada pesquisa da lista.
    private ArrayList<Pesquisa> mPesquisa = new ArrayList<>();

    //Vinculando a interface onPesquisaListener no Adapter
    private OnPesquisaListener mOnPesquisaListener;
    private ClickListener mClickListener;

    //Construtor com a estrutura de dados arraylist como parametro
    public PesquisaRecyclerAdapter(ArrayList<Pesquisa> pesquisa, OnPesquisaListener onPesquisaListener, ClickListener clickListener) {
        this.mPesquisa = pesquisa;

        //Settando a interface OnPesquisaListener no construtor do adapter.
        this.mOnPesquisaListener = onPesquisaListener;
        this.mClickListener = clickListener;
    }

    //------------------ Metodos que precisam ser sobrescritos quando se extend uma RecyclerView -------------------
    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //Precisa instanciar o viewholder aqui
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pesquisa_list_item, viewGroup, false);

        //Precisa retornar um novo objeto da view que foi inflada
        return new ViewHolder(view, mOnPesquisaListener, mClickListener); // passando um objeto view para o construtor de viewholder
    }

    //Esse metodo é chamado para cada entrada da lista. é aqui que vc insere os atributos para o viewholder object.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        // Old static que mostra a data igual pra todos
        viewHolder.ID.setText(String.valueOf(mPesquisa.get(i).getID()));
        //viewHolder.IdConcorrente.setText(String.valueOf(mPesquisa.get(i).getConcorrenteID()));
        viewHolder.IdConcorrente.setText(mPesquisa.get(i).getConcorrenteNome());
        viewHolder.Data.setText(mPesquisa.get(i).getData());


        //New Dynamic que mostra a data dinamicamente.
//        try {
//            String month = mPesquisa.get(i).getData().substring(0, 2);
//            month = Utility.getMonthFromNumber(month);
//
//            String year = mPesquisa.get(i).getData().substring(3);
//            year = Utility.getMonthFromNumber(year);
//
//            String Data = month + " " + year;
//
//            viewHolder.ID.setText(String.valueOf(mPesquisa.get(i).getID()));
//            viewHolder.IdConcorrente.setText(String.valueOf(mPesquisa.get(i).getConcorrenteID()));
//            viewHolder.Data.setText(Data);
//
//        } catch (NullPointerException e) {
//            Log.e(TAG, "onBindViewHolder: NullPointerException " + e.getMessage());
//        }

    }


    @Override
    public int getItemCount() {
        //Apenas retornar o tamanho do ArrayList, o número de pesquisas que tem na lista.
        return mPesquisa.size();
    }


    // ----- É a classe responsável por manter individualmente cada uma das  views dos items da lista de pesquisa -------
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        //Criar uma variavel para cada item que será exibido no card -> Id, IdConcorrente e Data
        TextView ID, IdConcorrente, Data;

        private ArrayList<Pesquisa> mPesquisa = new ArrayList<>();
        private PesquisaRecyclerAdapter mPesquisaRecyclerAdapter;
        private PesquisaRepository mPesquisaRepository;

        //Referenciando a interface OnPesquisaListener na ViewHolder
        OnPesquisaListener onPesquisaListener;
        ClickListener clickListener;

        //Construtor da ViewHolder
        public ViewHolder(@NonNull View itemView, OnPesquisaListener onPesquisaListener, ClickListener clickListener) {
            super(itemView);
            // Hora de vincular as variaveis edit text do adapter com os xmls de Layout
            ID = itemView.findViewById(R.id.id_pesquisa_ID);
            IdConcorrente = itemView.findViewById(R.id.id_concorrente);
            Data = itemView.findViewById(R.id.id_data_pesquisa);

            // Ao referencia a interface foi necessário adicionar onPesquisaListener nos parametros do construtor a viewholder e settar ele.
            this.onPesquisaListener = onPesquisaListener;
            this.clickListener = clickListener;

            //Vincular o onClickListener em toda ViewHolder

            itemView.setOnClickListener(this); // O this está se referindo a interface que foi implementada View.OnClickListener
            itemView.setOnLongClickListener(this);
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

            onPesquisaListener.onPesquisaClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            clickListener.clickListener(getAdapterPosition());

            return true;
        }
    } // viewholder fim


    //A melhor forma de escutar cliques na recycler view é através da criação de uma interface dentro da viewholder
    //Interface para escutar os cliques na lista.
    public interface OnPesquisaListener {

        void onPesquisaClick(int position);
    }

    public interface ClickListener {

        void clickListener(int position);
    }

} // fim
