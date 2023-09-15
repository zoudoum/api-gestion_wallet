package m1sir.groupe1.applicationwallet.entite;

import jakarta.persistence.*;
import m1sir.groupe1.applicationwallet.enums.Statut;

import java.util.Date;
@Entity
@Table(name = "transaction")
public class Transaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name ="COMPTE_SOURCE")
    private Compte compteSource;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name ="COMPTE_DESTINATION")
    private Compte compteDestination;
    private double montant;
    private Date dateHeure;
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Transaction(int transactionID, Compte compteSource, Compte compteDestination, double montant, Date dateHeure, Statut statut) {
        this.transactionID = transactionID;
        this.compteSource = compteSource;
        this.compteDestination = compteDestination;
        this.montant = montant;
        this.dateHeure = dateHeure;
        this.statut = statut;
    }

    public Transaction() {

    }


    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Compte getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(Compte compteSource) {
        this.compteSource = compteSource;
    }

    public Compte getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }


}