package com.bsi.fbd.minisiga.modelo;

import com.bsi.fbd.minisiga.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class QuickAdapterSimple extends BaseQuickAdapter<Object, BaseViewHolder> {

    public QuickAdapterSimple(int layout, List data){
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
        }

        helper.setText(R.id.texto1,texto1);
        helper.setText(R.id.texto2,texto2);
    }
}
