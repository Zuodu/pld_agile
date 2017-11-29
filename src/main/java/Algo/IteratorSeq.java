package Algo;

import Modele.PointLivraison;

import java.util.Collection;
import java.util.Iterator;

public class IteratorSeq implements Iterator<PointLivraison> {

	private PointLivraison[] candidats;
	private int nbCandidats;

	/**
	 * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
	 * @param nonVus
	 * @param sommetCrt
	 */
	public IteratorSeq(Collection<PointLivraison> nonVus, PointLivraison sommetCrt) {
		this.candidats = new PointLivraison[nonVus.size()];
		nbCandidats = 0;
		for (PointLivraison s : nonVus) {
			candidats[nbCandidats++] = s;
		}
	}
	
	@Override
	public boolean hasNext() {
		return nbCandidats > 0;
	}

	@Override
	public PointLivraison next() {
		return candidats[--nbCandidats];
	}

	@Override
	public void remove() {}

}
