package corbaBanque;

import corbaBanque.IBanqueRemotePOA;
import java.util.ArrayList;
import java.util.List;

public class BanqueImpl extends IBanqueRemotePOA {

    private List<Compte> comptes = new ArrayList<>();
    private double tauxConversion = 3.3;

    @Override
    public void creerCompte(Compte cpte) {
        comptes.add(cpte);
        System.out.println("Compte créé: code=" + cpte.code + ", solde=" + cpte.solde);
    }

    @Override
    public void verser(float mt, int code) {
        for (Compte c : comptes) {
            if (c.code == code) {
                c.solde += mt;
                System.out.println("Versement de " + mt + " effectué sur le compte " + code);
                return;
            }
        }
        System.out.println("Compte non trouvé pour le versement!");
    }
    @Override
    public void retirer(float mt, int code) {
        for (Compte c : comptes) {
            if (c.code == code) {
                if (c.solde >= mt) {
                    c.solde -= mt;
                    System.out.println("Retrait de " + mt + " effectué sur le compte " + code);
                } else {
                    System.out.println("Solde insuffisant pour le retrait!");
                }
                return;
            }
        }
        System.out.println("Compte non trouvé pour le retrait!");
    }
    @Override
    public Compte getCompte(int code) {
        for (Compte c : comptes) {
            if (c.code == code) return c;
        }
        System.out.println("Compte non trouvé!");
        return new Compte(0, 0);
    }
    @Override
    public corbaBanque.Compte[] getComptes() {

        Compte[] tab = new Compte[comptes.size()];
        for (int i = 0; i < comptes.size(); i++) {
            tab[i] = comptes.get(i);
        }
        return tab;
    }
    @Override
    public double conversion(float mt) {
        return mt * tauxConversion;
    }
}
