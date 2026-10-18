package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		boolean clientReconnu = controlAcheterProduit.verifierIdentite(nomAcheteur);
		if(!clientReconnu) {
			System.out.println("Je suis désolée " + nomAcheteur + " mais il faut être un habitant de notre village pour commercer ici?");
		}
		else {
			String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
			Gaulois[] vendeurs = controlAcheterProduit.rechercheVendeursProduit(produit);
			if(vendeurs == null || vendeurs.length==0) {
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			}
			else {
				System.out.println("Chez quel commerçant voulez-vous acheter des " + produit + " ?");
				for(int i = 0; i <vendeurs.length;i++) {
					System.out.println((i+1) + " - " + vendeurs[i].getNom());
				}
				int choixVendeur = Clavier.entrerEntier(" ");
				String nomVendeur = vendeurs[choixVendeur - 1].getNom();
				System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
				System.out.println("Bonjour " + nomAcheteur);
				int quantite = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
				int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantite);
				if(quantiteAchetee == 0) {
					System.out.println(nomAcheteur + " eut acheter " + quantite + " " + produit + ", malheureusement il n'y en a plus !");
				}
				else if(quantiteAchetee<quantite) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + produit + ",malheureusement " + nomVendeur + " n'en a plus que " + quantiteAchetee +".");
					System.out.println(nomAcheteur + " achète " + quantite + " " + produit + " à " + nomVendeur + ".");
				}
				else {
					System.out.println(nomAcheteur + " achète " + quantite + " " + produit + " à " + nomVendeur +".");
				}
			}
		}
	}
}
