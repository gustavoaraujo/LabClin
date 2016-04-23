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
import classes.Cliente;
import classes.Exame;
import classes.Planosaude;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Cliente
 */
public class ExameJpaController implements Serializable {

    public ExameJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exame exame) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idcliente = exame.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdCliente());
                exame.setIdcliente(idcliente);
            }
            Planosaude planosaude1 = exame.getPlanosaude1();
            if (planosaude1 != null) {
                planosaude1 = em.getReference(planosaude1.getClass(), planosaude1.getIdplano());
                exame.setPlanosaude1(planosaude1);
            }
            em.persist(exame);
            if (idcliente != null) {
                idcliente.getExameList().add(exame);
                idcliente = em.merge(idcliente);
            }
            if (planosaude1 != null) {
                Exame oldExameOfPlanosaude1 = planosaude1.getExame();
                if (oldExameOfPlanosaude1 != null) {
                    oldExameOfPlanosaude1.setPlanosaude1(null);
                    oldExameOfPlanosaude1 = em.merge(oldExameOfPlanosaude1);
                }
                planosaude1.setExame(exame);
                planosaude1 = em.merge(planosaude1);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exame exame) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exame persistentExame = em.find(Exame.class, exame.getIdexame());
            Cliente idclienteOld = persistentExame.getIdcliente();
            Cliente idclienteNew = exame.getIdcliente();
            Planosaude planosaude1Old = persistentExame.getPlanosaude1();
            Planosaude planosaude1New = exame.getPlanosaude1();
            List<String> illegalOrphanMessages = null;
            if (planosaude1Old != null && !planosaude1Old.equals(planosaude1New)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Planosaude " + planosaude1Old + " since its exame field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdCliente());
                exame.setIdcliente(idclienteNew);
            }
            if (planosaude1New != null) {
                planosaude1New = em.getReference(planosaude1New.getClass(), planosaude1New.getIdplano());
                exame.setPlanosaude1(planosaude1New);
            }
            exame = em.merge(exame);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getExameList().remove(exame);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getExameList().add(exame);
                idclienteNew = em.merge(idclienteNew);
            }
            if (planosaude1New != null && !planosaude1New.equals(planosaude1Old)) {
                Exame oldExameOfPlanosaude1 = planosaude1New.getExame();
                if (oldExameOfPlanosaude1 != null) {
                    oldExameOfPlanosaude1.setPlanosaude1(null);
                    oldExameOfPlanosaude1 = em.merge(oldExameOfPlanosaude1);
                }
                planosaude1New.setExame(exame);
                planosaude1New = em.merge(planosaude1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = exame.getIdexame();
                if (findExame(id) == null) {
                    throw new NonexistentEntityException("The exame with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Exame exame;
            try {
                exame = em.getReference(Exame.class, id);
                exame.getIdexame();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exame with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Planosaude planosaude1OrphanCheck = exame.getPlanosaude1();
            if (planosaude1OrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exame (" + exame + ") cannot be destroyed since the Planosaude " + planosaude1OrphanCheck + " in its planosaude1 field has a non-nullable exame field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idcliente = exame.getIdcliente();
            if (idcliente != null) {
                idcliente.getExameList().remove(exame);
                idcliente = em.merge(idcliente);
            }
            em.remove(exame);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Exame> findExameEntities() {
        return findExameEntities(true, -1, -1);
    }

    public List<Exame> findExameEntities(int maxResults, int firstResult) {
        return findExameEntities(false, maxResults, firstResult);
    }

    private List<Exame> findExameEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exame.class));
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

    public Exame findExame(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exame.class, id);
        } finally {
            em.close();
        }
    }

    public int getExameCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exame> rt = cq.from(Exame.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public double findFaturamentoSemExame() {
        String jpql = "select u.preco from Exame u where u.planosaude = 0";
        
        Query q = getEntityManager().createQuery(jpql);
        try {
            return Double.parseDouble(q.getSingleResult().toString());
        }
        catch (NonUniqueResultException e) {
            return Double.parseDouble(q.getResultList().get(0).toString());
        }
    }
    
    public Map<Planosaude,Double> findFaturamentoPorPlano(List<Planosaude> listaPlano) {
        
        Map<Planosaude,Double> mapPlano = new HashMap<Planosaude,Double>();
        
        List<Exame> listaExame = this.findExameEntities();
        
        for(Planosaude p: listaPlano) {
            double soma = 0;
            for(Exame e: listaExame) {
                if(p.getIdplano() == e.getCodplano()) {
                    soma += Double.parseDouble(e.getPreco().toString());
                }
            }
            mapPlano.put(p, soma);
        }
        
        return mapPlano;
    }
    
    public List<Exame> findExamePorDescricao(String desc) {
        String jpql = "select u from Exame u where u.descricao = :desc";
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("desc", desc);
        
        return q.getResultList();
    }

    public List<Exame> findExamePorCliente(int idCliente) {
        String jpql = "select u from Exame u where u.idcliente = :desc";
        
        Cliente c = new ClienteJpaController(emf).findCliente(idCliente);
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("desc", c);
        
        return q.getResultList();
    }
    
}
