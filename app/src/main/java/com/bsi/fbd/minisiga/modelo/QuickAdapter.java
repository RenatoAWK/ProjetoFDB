package com.bsi.fbd.minisiga.modelo;

import com.bsi.fbd.minisiga.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class QuickAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    public QuickAdapter(int layoutId, List data){
        super(layoutId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        String texto1 = "";
        String texto2 = "";
        if (item instanceof Bloco){
            Bloco bloco = (Bloco) item;
            texto1 = bloco.getArea() ;
            texto2 = String.valueOf(bloco.getCodigo());
        } else if (item instanceof Aluno){
                Aluno aluno = (Aluno) item;
                texto1 = aluno.getNome();
                texto2 = aluno.getEmail();
        } else if (item instanceof Professor){
                Professor professor = (Professor) item;
                texto1 = professor.getNome();
                texto2 = professor.getEmail();
        } else if (item instanceof Conteudo){
                Conteudo conteudo = (Conteudo) item;
                texto1 = conteudo.getItem();
                texto2 = String.valueOf(conteudo.getCargaHoraria());
        } else if (item instanceof Curso){
                Curso curso = (Curso) item;
                texto1 = curso.getNome();
                texto2 = curso.getSigla();
        } else if (item instanceof Disciplina){
                Disciplina disciplina = (Disciplina) item;
                texto1 = disciplina.getNome();
                texto2 = String.valueOf(disciplina.getCodigo());
        } else if (item instanceof RecursoDidatico){
                RecursoDidatico recursoDidatico = (RecursoDidatico) item;
                texto1 = recursoDidatico.getNome();
                texto2 = recursoDidatico.getDescricao();
        } else if (item instanceof Sala){
                Sala sala = (Sala) item;
                texto1 = String.valueOf(sala.getNumero());
                texto2= sala.getArea();
        } else if (item instanceof Turma){
                Turma turma = (Turma) item;
                texto1 = String.valueOf(turma.getCodigo());
                texto2 = String.valueOf(turma.getAno());
        }

        helper.setText(R.id.texto1,texto1);
        helper.setText(R.id.texto2,texto2);
        helper.addOnClickListener(R.id.edit);
        helper.addOnClickListener(R.id.delete);
    }
}

