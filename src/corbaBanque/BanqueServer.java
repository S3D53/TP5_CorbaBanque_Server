// BanqueServer.java
package corbaBanque;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.CORBA.Object;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            // creer ORB
            ORB orb = ORB.init(args, null);
            // POA
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();
            BanqueImpl banqueImpl = new BanqueImpl();
            // sauvegarde dans  ORB
            Object ref = rootpoa.servant_to_reference(banqueImpl);
            corbaBanque.IBanqueRemote href = corbaBanque.IBanqueRemoteHelper.narrow(ref);
            // sauvegarde dans Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            org.omg.CosNaming.NamingContextExt ncRef = org.omg.CosNaming.NamingContextExtHelper.narrow(objRef);

            org.omg.CosNaming.NameComponent path[] = ncRef.to_name("Banque");
            ncRef.rebind(path, href);

            System.out.println("Serveur Banque prêt et en attente...");
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
