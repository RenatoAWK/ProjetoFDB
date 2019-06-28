package com.bsi.fbd.minisiga.modelo;

public class User {
    private static Object currentUser;
    private static Bloco bloco;
    private static Turma turma;
    private static ResponseAR alunosCadastrados;
    private static ResponseAR alunosNaoCadastrados;
    private static Curso curso;
    private static ResponseAR disciplinasCadastradas;
    private static ResponseAR disciplinasNaoCadastradas;


    public static Object getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Object currentUser) {
        User.currentUser = currentUser;
    }

    public static String userType(){
        if (User.userType().getClass().isInstance(Faculdade.class)){
            return "faculdade";
        } else if (User.userType().getClass().isInstance(Aluno.class)){
            return "aluno";
        } else  if (User.userType().getClass().isInstance(Professor.class)){
            return "professor";
        } else {
            return null;
        }
    }

    public static Bloco getBloco() {
        return bloco;
    }

    public static void setBloco(Bloco bloco) {
        User.bloco = bloco;
    }

    public static Turma getTurma() {
        return turma;
    }

    public static void setTurma(Turma turma) {
        User.turma = turma;
    }

    public static ResponseAR getAlunosCadastrados() {
        return alunosCadastrados;
    }

    public static void setAlunosCadastrados(ResponseAR alunosCadastrados) {
        User.alunosCadastrados = alunosCadastrados;
    }

    public static ResponseAR getAlunosNaoCadastrados() {
        return alunosNaoCadastrados;
    }

    public static void setAlunosNaoCadastrados(ResponseAR alunosNaoCadastrados) {
        User.alunosNaoCadastrados = alunosNaoCadastrados;
    }

    public static Curso getCurso() {
        return curso;
    }

    public static void setCurso(Curso curso) {
        User.curso = curso;
    }

    public static ResponseAR getDisciplinasCadastradas() {
        return disciplinasCadastradas;
    }

    public static void setDisciplinasCadastradas(ResponseAR disciplinasCadastradas) {
        User.disciplinasCadastradas = disciplinasCadastradas;
    }

    public static ResponseAR getDisciplinasNaoCadastradas() {
        return disciplinasNaoCadastradas;
    }

    public static void setDisciplinasNaoCadastradas(ResponseAR disciplinasNaoCadastradas) {
        User.disciplinasNaoCadastradas = disciplinasNaoCadastradas;
    }
}
