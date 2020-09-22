package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListBike;

public class ListBikeHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BikeModelMakeRyberg");
	
	public void insertItem (ListBike li) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListBike> showAllItems(){
		
		EntityManager em = emfactory.createEntityManager();
		List<ListBike> allBikes = em.createQuery("SELECT i FROM ListBike i").getResultList();
		return allBikes;
	}
	
	public void deleteBike(ListBike toDelete) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBike>typedQuery = em.createQuery("select li from ListBike li where li.make = :selectedMake and li.model = :selectedModel", ListBike.class);
		
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		
		ListBike result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public ListBike searchForBikeById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListBike found = em.find(ListBike.class, idToEdit);
		em.close();
		return found;
	}
	
	
	public void updateItem(ListBike toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListBike> searchForBikeByMake(String makeName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBike> typedQuery = em.createQuery("select li from ListBike li where li.make = :selectedMake", ListBike.class);
		typedQuery.setParameter("selectedMake", makeName);
		
		List<ListBike> foundBike = typedQuery.getResultList();
		em.close();
		return foundBike;
		
	}
	
	public List<ListBike> searchForBikeByModel (String modelName){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListBike> typedQuery = em.createQuery("select li from ListBike li where li.model = :selectedModel", ListBike.class);
		typedQuery.setParameter("selectedModel", modelName);
		
		List<ListBike> foundBike = typedQuery.getResultList();
		em.close();
		return foundBike;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
