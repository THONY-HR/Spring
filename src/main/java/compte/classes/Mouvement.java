package compte.classes;

import java.util.HashMap;
import java.util.Map;

import connexion.MapTable;

public class Mouvement implements MapTable{
    int id;
    int compteId;
    double amount;
    String type;

    public Mouvement(){}
    public int getId() { return id;}
    public void setId(int id) { this.id = id;}
    public double getAmount() { return amount;}
    public void setAmount(double amount) { this.amount = amount;}
    public int getCompteId() { return compteId;}
    public void setCompteId(int compteId) { this.compteId = compteId;}
    public String getType() { return type;}
    public void setType(String type) { this.type = type;}

    public void print() {
        System.out.println("--------- Informations du Mouvement ---------");
        System.out.println("id          : " + this.getId());
        System.out.println("compteId    : " + this.getCompteId());
        System.out.println("amount      : " + this.getAmount());
        System.out.println("type        : " + this.getType());
        System.out.println("------------------------------------------");
    }
    
    @Override
    public String getTableName() {
        return "Mouvement";
    }

    @Override
    public String[] getColumnNames() {
        return new String[] {"id", "compte_id", "amount", "type"};
    }

    @Override
    public Object[] getValues() {
        return new Object[] {this.getId(), this.getCompteId(), this.getAmount(), this.getType()};
    }

    @Override
    public Map<String, String> getColumnMapping() {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("id", "id");
        mapping.put("compteId", "compte_id"); 
        mapping.put("amount", "amount"); 
        mapping.put("type", "type"); 
        return mapping;
    }
}
