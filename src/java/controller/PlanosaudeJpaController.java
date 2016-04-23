/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import classes.Exame;
import classes.Planosaude;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Cliente
 */
public class PlanosaudeJpaController implements Serializable {

    public PlanosaudeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planosaude planosaude) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Exame exameOrphanCheck = planosaude.getExame();
        if (exameOrphanCheck != null) {
            Planosaude oldPlanosaude1OfExame = exameOrphanCheck.getPlanosaude1();
            if (oldPlanosaude1OfExame != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Exame " + exameOrphanCheck + " already has an item of type Planosaude whose exame column cannot be null. Please make another selection for the exame field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exame exame = planosaude.getExame();
            if (exame != null) {
                exame = em.getReference(exame.getClass(), exame.getIdexame());
                planosaude.setExame(exame);
            }
            em.persist(planosaude);
            if (exame != null) {
                exame.setPlanosaude1(planosaude);
                exame = em.merge(exame);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planosaude planosaude) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planosaude persistentPlanosaude = em.find(Planosaude.class, planosaude.getIdplano());
            Exame exameOld = persistentPlanosaude.getExame();
            Exame exameNew = planosaude.getExame();
            List<String> illegalOrphanMessages = null;
            if (exameNew != null && !exameNew.equals(exameOld)) {
                Planosaude oldPlanosaude1OfExame = exameNew.getPlanosaude1();
                if (oldPlanosaude1OfExame != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Exame " + exameNew + " already has an item of type Planosaude whose exame column cannot be null. Please make another selection for the exame field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (exameNew != null) {
                exameNew = em.getReference(exameNew.getClass(), exameNew.getIdexame());
                planosaude.setExame(exameNew);
            }
            planosaude = em.merge(planosaude);
            if (exameOld != null && !exameOld.equals(exameNew)) {
                exameOld.setPlanosaude1(null);
                exameOld = em.merge(exameOld);
            }
            if (exameNew != null && !exameNew.equals(exameOld)) {
                exameNew.setPlanosaude1(planosaude);
                exameNew = em.merge(exameNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planosaude.getIdplano();
                if (findPlanosaude(id) == null) {
                    throw new NonexistentEntityException("The planosaude with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planosaude planosaude;
            try {
                planosaude = em.getReference(Planosaude.class, id);
                planosaude.getIdplano();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planosaude with id " + id + " no longer exists.", enfe);
            }
            Exame exame = planosaude.getExame();
            if (exame != null) {
                exame.setPlanosaude1(null);
                exame = em.merge(exame);
            }
            em.remove(planosaude);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planosaude> findPlanosaudeEntities() {
        return findPlanosaudeEntities(true, -1, -1);
    }

    public List<Planosaude> findPlanosaudeEntities(int maxResults, int firstResult) {
        return findPlanosaudeEntities(false, maxResults, firstResult);
    }

    private List<Planosaude> findPlanosaudeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planosaude.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Planosaude findPlanosaude(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planosaude.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanosaudeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planosaude> rt = cq.from(Planosaude.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Planosaude> findPlanosaudeByName(String nome) {
        List<Planosaude> att = this.findPlanosaudeEntities();
        List<Planosaude> filtrada = new ArrayList<Planosaude>();
        
        for(Planosaude a : att) {
            if(a.getNome().equals(nome)) {
                filtrada.add(a);
            }
        }
        
        return filtrada;
    }
    
}
