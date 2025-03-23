package compte.classes;

import java.util.HashMap;
import java.util.Map;

import connexion.MapTable;

public class Compte implements MapTable{
    int id;
    String num;
    String status;
    double solde;

    public Compte(){}
    public int getId() { return id;}
    public void setId(int id) {this.id = id;}
    public String getNum() {return num;}
    public void setNum(String num) {this.num = num;}
    public double getSolde() {return solde;}
    public void setSolde(double solde) {this.solde = solde;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public void print() {
        System.out.println("--------- Informations du Compte ---------");
        System.out.println("ID          : " + id);
        System.out.println("Numéro      : " + num);
        System.out.println("Status      : " + status);
        System.out.println("Solde       : " + solde + " Ar");
        System.out.println("------------------------------------------");
    }
    
    @Override
    public String getTableName() {
        return "Compte";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {"id", "num", "status", "solde"};
    }

    @Override
    public Object[] getValues() {
        return new Object[] {getId(), getNum(), getStatus(), getSolde()};
    }

    @Override
    public Map<String, String> getColumnMapping() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("id", "id");
        mapping.put("num", "num"); 
        mapping.put("status", "status"); 
        mapping.put("solde", "solde"); 
        return mapping;
    }
}
