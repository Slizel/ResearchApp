package faridnet.com.pesquisaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import faridnet.com.pesquisaapp.R;
import faridnet.com.pesquisaapp.models.PesquisaProduto;

public class PesquisaProdutoRecyclerAdapter extends RecyclerView.Adapter<PesquisaProdutoRecyclerAdapter.ViewHolder> {


    private ArrayList<PesquisaProduto> mPesquisaProduto = new ArrayList<>();


    private OnPesquisaProdutoListener mOnPesquisaProdutoListener;
    private OnLongPesquisaProdutoClick mOnLongPesquisaProdutoClick;


    public PesquisaProdutoRecyclerAdapter(ArrayList<PesquisaProduto> pesquisaProduto, OnPesquisaProdutoListener onPesquisaProdutoListener,
                                          OnLongPesquisaProdutoClick onLongPesquisaProdutoClick) {
        this.mPesquisaProduto = pesquisaProduto;

        this.mOnPesquisaProdutoListener = onPesquisaProdutoListener;
        this.mOnLongPesquisaProdutoClick = onLongPesquisaProdutoClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pesquisa_produto_list, viewGroup, false);
        return new ViewHolder(view, mOnPesquisaProdutoListener, mOnLongPesquisaProdutoClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.codBarras.setText(mPesquisaProduto.get(i).getCodBarras());
        viewHolder.preco.setText(mPesquisaProduto.get(i).getPreco());
    }

    @Override
    public int getItemCount() {
        return mPesquisaProduto.size();
    }


    // ----- É a classe responsável por manter individualmente cada uma das  views dos items da lista de pesquisa -------

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView codBarras, preco;

        //Referenciando a interface OnPesquisaListener na ViewHolder
        OnPesquisaProdutoListener onPesquisaProdutoListener;
        OnLongPesquisaProdutoClick onLongPesquisaProdutoClick;

        public ViewHolder(@NonNull View itemView, OnPesquisaProdutoListener onPesquisaProdutoListener, OnLongPesquisaProdutoClick onLongPesquisaProdutoClick) {
            super(itemView);

            codBarras = itemView.findViewById(R.id.cod_barras);
            preco = itemView.findViewById(R.id.preco);
            this.onPesquisaProdutoListener = onPesquisaProdutoListener;
            this.onLongPesquisaProdutoClick = onLongPesquisaProdutoClick;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }


        @Override
        public void onClick(View view) {
            onPesquisaProdutoListener.onPesquisaProdutoClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            onLongPesquisaProdutoClick.onLongPesquisaProdutoClick(getAdapterPosition());

            return true;
        }
    }// Viewholder fim


    // Interfaces

    public interface OnPesquisaProdutoListener {

        void onPesquisaProdutoClick(int position);
    }


    public interface OnLongPesquisaProdutoClick {

        void onLongPesquisaProdutoClick(int position);
    }

}//fim
