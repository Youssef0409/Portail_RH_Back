package com.example.stage.exception;



public enum ErrorCodes {
   Utilisateur_Not_Found(1000),
    Utilisateur_Not_Valid(1001),

    Collaborateur_Not_Found(2000),
    Collaborateur_Not_Valid(2001),
    Administarteur_Not_Found(3000),
    Administarteur_Not_Valid(3001),
   Responsable_Not_Found(4000),
    Responsable_Not_Valid(4001),
   PlanFormation_Not_Found(5000),
    PlanFormation_Not_Valid(5001),
    DemandeFormation_Not_Found(6000),
    DemandeFormation_Not_Valid(6001),
Competence_Not_Found(7000),
    Competence_Not_Valid(7001);






    public int code ;
    ErrorCodes(int code ){
        this.code=code;
    }
    public int  getcode(){
        return code;
    }
}
