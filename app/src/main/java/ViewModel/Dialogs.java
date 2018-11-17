package ViewModel;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import Models.ManageLists;
import br.com.beirario.pontuacaocampeonatos.R;

import static Util.Converters.convertDpToPx;

public abstract class Dialogs {

    public static void dialogPickName(Context sender, String title, String message){

        final AlertDialog.Builder alert = new AlertDialog.Builder(sender);
        final EditText input = new EditText(sender);
        input.setSingleLine();
        FrameLayout container = new FrameLayout(sender);
        FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = convertDpToPx(sender, 20);
        params.rightMargin = convertDpToPx(sender, 40);
        input.setLayoutParams(params);
        container.addView(input);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setView(container);
        alert.setPositiveButton(R.string.button_save, (dialog, which) -> {
            if(!input.getText().toString().equals("")){
                ((ManageLists)sender).addObject(input.getText().toString());
            }else{
                Toast.makeText(sender,"Você não digitou um nome válido!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
        alert.show();
    }



}
