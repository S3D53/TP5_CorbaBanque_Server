// BanqueClient.java
package corbaBanque;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class BanqueClient {

    public static void main(String[] args) {
        try {

            ORB orb = ORB.init(args, null);


            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            IBanqueRemote banque = IBanqueRemoteHelper.narrow(ncRef.resolve_str("Banque"));


            Compte c1 = new Compte(1001, 500);
            banque.creerCompte(c1);

            banque.verser(200, 1001);


            banque.retirer(100, 1001);


            Compte c = banque.getCompte(1001);
            System.out.println("Compte code=" + c.code + ", solde=" + c.solde);


            double dt = banque.conversion(100);
            System.out.println("100€ = " + dt + " DT");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

