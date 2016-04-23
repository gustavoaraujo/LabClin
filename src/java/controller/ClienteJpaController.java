/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import classes.Exame;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author Cliente
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getExameList() == null) {
            cliente.setExameList(new ArrayList<Exame>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Exame> attachedExameList = new ArrayList<Exame>();
            for (Exame exameListExameToAttach : cliente.getExameList()) {
                exameListExameToAttach = em.getReference(exameListExameToAttach.getClass(), exameListExameToAttach.getIdexame());
                attachedExameList.add(exameListExameToAttach);
            }
            cliente.setExameList(attachedExameList);
            em.persist(cliente);
            for (Exame exameListExame : cliente.getExameList()) {
                Cliente oldIdclienteOfExameListExame = exameListExame.getIdcliente();
                exameListExame.setIdcliente(cliente);
                exameListExame = em.merge(exameListExame);
                if (oldIdclienteOfExameListExame != null) {
                    oldIdclienteOfExameListExame.getExameList().remove(exameListExame);
                    oldIdclienteOfExameListExame = em.merge(oldIdclienteOfExameListExame);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            List<Exame> exameListOld = persistentCliente.getExameList();
            List<Exame> exameListNew = cliente.getExameList();
            List<String> illegalOrphanMessages = null;
            for (Exame exameListOldExame : exameListOld) {
                if (!exameListNew.contains(exameListOldExame)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Exame " + exameListOldExame + " since its idcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Exame> attachedExameListNew = new ArrayList<Exame>();
            for (Exame exameListNewExameToAttach : exameListNew) {
                exameListNewExameToAttach = em.getReference(exameListNewExameToAttach.getClass(), exameListNewExameToAttach.getIdexame());
                attachedExameListNew.add(exameListNewExameToAttach);
            }
            exameListNew = attachedExameListNew;
            cliente.setExameList(exameListNew);
            cliente = em.merge(cliente);
            for (Exame exameListNewExame : exameListNew) {
                if (!exameListOld.contains(exameListNewExame)) {
                    Cliente oldIdclienteOfExameListNewExame = exameListNewExame.getIdcliente();
                    exameListNewExame.setIdcliente(cliente);
                    exameListNewExame = em.merge(exameListNewExame);
                    if (oldIdclienteOfExameListNewExame != null && !oldIdclienteOfExameListNewExame.equals(cliente)) {
                        oldIdclienteOfExameListNewExame.getExameList().remove(exameListNewExame);
                        oldIdclienteOfExameListNewExame = em.merge(oldIdclienteOfExameListNewExame);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Exame> exameListOrphanCheck = cliente.getExameList();
            for (Exame exameListOrphanCheckExame : exameListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Exame " + exameListOrphanCheckExame + " in its exameList field has a non-nullable idcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public List<Cliente> findClienteByCpf(String cpf) {
        List<Cliente> att = this.findClienteEntities();
        List<Cliente> filtrada = new ArrayList<Cliente>();
        
        for(Cliente a : att) {
            if(a.getCpf().equals(cpf)) {
                filtrada.add(a);
            }
        }
        
        return filtrada;
    }
    
    public Cliente findUsuarioByLoginAndSenha(String login, String senha) {
        String jpql = "select u from Cliente u where u.cpf = :log and u.senha = :sen";
        
        Query q = getEntityManager().createQuery(jpql);
        q.setParameter("log", login);
        q.setParameter("sen", senha);
        try {
            return (Cliente)q.getSingleResult();
        }
        catch (NonUniqueResultException e) {
            return (Cliente)q.getResultList().get(0);
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
