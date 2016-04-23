/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Administrador;
import classes.Atendente;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cliente
 */
public class AtendenteJpaController implements Serializable {

    public AtendenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Atendente atendente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(atendente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Atendente atendente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            atendente = em.merge(atendente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = atendente.getIdatendente();
                if (findAtendente(id) == null) {
                    throw new NonexistentEntityException("The atendente with id " + id + " no longer exists.");
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
            Atendente atendente;
            try {
                atendente = em.getReference(Atendente.class, id);
                atendente.getIdatendente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atendente with id " + id + " no longer exists.", enfe);
            }
            em.remove(atendente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Atendente> findAtendenteEntities() {
        return findAtendenteEntities(true, -1, -1);
    }

    public List<Atendente> findAtendenteEntities(int maxResults, int firstResult) {
        return findAtendenteEntities(false, maxResults, firstResult);
    }

    private List<Atendente> findAtendenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Atendente.class));
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

    public Atendente findAtendente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Atendente.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtendenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Atendente> rt = cq.from(Atendente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Atendente> findAtendenteByLogin(String login) {
        List<Atendente> att = this.findAtendenteEntities();
        List<Atendente> filtrada = new ArrayList<Atendente>();
        
        for(Atendente a : att) {
            if(a.getLogin().equals(login)) {
                filtrada.add(a);
            }
        }
        
        return filtrada;
    }
    
    public Atendente findUsuarioByLoginAndSenha(String login, String senha) {
        String jpql = "select u from Atendente u where u.login = :log and u.senha = :sen";
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("log", login);
        q.setParameter("sen", senha);
        try {
            return (Atendente)q.getSingleResult();
        }
        catch (NonUniqueResultException e) {
            return (Atendente)q.getResultList().get(0);
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
