package faridnet.com.pesquisaapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import faridnet.com.pesquisaapp.activity.PesquisaListActivity;
import faridnet.com.pesquisaapp.models.Pesquisa;

public class DeletePesquisaPermissionDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private Pesquisa pesquisa;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Deletar Pesquisa")
                .setMessage("Deseja Deletar a Pesquisa")
                .setPositiveButton("Sim", this)
                .setNegativeButton("Não", this);

         return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {

        String msg = null;

        if(which == DialogInterface.BUTTON_POSITIVE){
            msg = "Pesquisa Deletada";

        } else if (which == Dialog.BUTTON_NEGATIVE) {
            msg = "Deleção Cancelada";
        }

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
