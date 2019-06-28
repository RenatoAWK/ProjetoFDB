package com.bsi.fbd.minisiga.modelo;

import android.view.View;

import com.bsi.fbd.minisiga.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class QuickAdapterAR extends BaseQuickAdapter<Object, BaseViewHolder> {
    private String add_remove;

    public QuickAdapterAR getThis(){
        return this;
    }

    public QuickAdapterAR setAdd_remove(String add_remove) {
        this.add_remove = add_remove;
        return this;
    }

    public QuickAdapterAR(int layout, List data){
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        String texto1 = "";
        String texto2 = "";
        if (item instanceof Aluno) {
            Aluno aluno = (Aluno) item;
            texto1 = aluno.getNome();
            texto2 = aluno.getEmail();
        } else if (item instanceof Disciplina) {
            Disciplina disciplina = (Disciplina) item;
            texto1 = disciplina.getNome();
            texto2 = String.valueOf(disciplina.getCodigo());
        }

        helper.setText(R.id.texto1,texto1);
        helper.setText(R.id.texto2,texto2);
        helper.addOnClickListener(R.id.add);
        helper.addOnClickListener(R.id.remove);
        if (add_remove.equals("add")){
            helper.getView(R.id.remove).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.add).setVisibility(View.GONE);
        }
    }
}
