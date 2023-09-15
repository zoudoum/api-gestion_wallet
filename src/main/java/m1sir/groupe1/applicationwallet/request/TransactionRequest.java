package m1sir.groupe1.applicationwallet.request;

public class TransactionRequest {

        private int compteSourceId;
        private int compteDestinationId;
        private double montant;


    public int getCompteSourceId() {
        return compteSourceId;
    }

    public void setCompteSourceId(int compteSourceId) {
        this.compteSourceId = compteSourceId;
    }

    public int getCompteDestinationId() {
        return compteDestinationId;
    }

    public void setCompteDestinationId(int compteDestinationId) {
        this.compteDestinationId = compteDestinationId;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
