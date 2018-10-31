package Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.ListVinyl;

public class ListVinylHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleVinylList");
	
	public void cleanUp() {
		emfactory.close();
	}
	
	public void insertVinyl(ListVinyl li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public void deleteVinyl(ListVinyl toDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl> typedQuery = em.createQuery("select li from ListVinyl li where li.artist = :selectedArtist and li.album = :selectedAlbum "
				+ "and li.genre = :selectedGenre", ListVinyl.class);
		typedQuery.setParameter("selectedArtist", toDelete.getArtist());
		typedQuery.setParameter("selectedAlbum", toDelete.getAlbum());
		typedQuery.setParameter("selectedGenre", toDelete.getGenre());
		typedQuery.setMaxResults(1);
		ListVinyl result = typedQuery.getSingleResult();
		em.remove(result);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<ListVinyl> searchForItemByArtist(String artistName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl> typedQuery = em.createQuery("select li from ListVinyl li where li.artist = :selectedArtist", ListVinyl.class);
		typedQuery.setParameter("selectedArtist", artistName);

		List<ListVinyl> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<ListVinyl> searchForItemByAlbum(String albumName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl> typedQuery = em.createQuery("select li from ListVinyl li where li.album = :selectedAlbum", ListVinyl.class);
		typedQuery.setParameter("selectedStore", albumName);

		List<ListVinyl> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public List<ListVinyl> searchForItemByGenre(String genreName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListVinyl> typedQuery = em.createQuery("select li from ListVinyl li where li.genre = :selectedGenre", ListVinyl.class);
		typedQuery.setParameter("selectedStore", genreName);

		List<ListVinyl> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		
	}
	
	public ListVinyl searchForItemById(int id){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListVinyl found = em.find(ListVinyl.class, id);
		em.close();
		return found;
	}
	
	public List<ListVinyl> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		TypedQuery<ListVinyl> typedQuery = em.createQuery("select li from ListVinyl li", ListVinyl.class);
		List<ListVinyl> allItems = typedQuery.getResultList();
		em.close();
		return allItems;
	}
	
	public void updateItem(ListVinyl toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	
}
