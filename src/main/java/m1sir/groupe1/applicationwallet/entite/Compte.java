package m1sir.groupe1.applicationwallet.entite;

import jakarta.persistence.*;

@Entity
@Table(name = "compte")
public class Compte{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name ="USER_ID")
    private  Client client;

    private double solde;
    private String typeDeCompte;

    public Compte(int accountID, Client client, double solde, String typeDeCompte) {
        this.accountID = accountID;
        this.client = client;
        this.solde = solde;
        this.typeDeCompte = typeDeCompte;
    }

    public Compte() {

    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getTypeDeCompte() {
        return typeDeCompte;
    }

    public void setTypeDeCompte(String typeDeCompte) {
        this.typeDeCompte = typeDeCompte;
    }


}