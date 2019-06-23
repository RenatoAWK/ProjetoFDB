package com.bsi.fbd.minisiga.modelo;

public class User {
    private static Object currentUser;

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
}
