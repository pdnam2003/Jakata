package com.example.service;

import com.example.entity.Team;
import javax.ejb.Stateless;
import javax.ejb.EJBException;
import javax.persistence.*;
import java.util.List;

/**
 * Stateless EJB implementing CRUD operations using JPA.
 */
@Stateless
public class TeamBean implements TeamBeanRemote {

    @PersistenceContext(unitName = "SportsPU")
    private EntityManager em;

    @Override
    public void addTeam(String name, String city) {
        try {
            Team t = new Team(name, city);
            em.persist(t);
        } catch (Exception e) {
            throw new EJBException("Error adding team", e);
        }
    }

    @Override
    public List<Team> getAllTeams() {
        try {
            TypedQuery<Team> q = em.createQuery("SELECT t FROM Team t ORDER BY t.id", Team.class);
            return q.getResultList();
        } catch (Exception e) {
            throw new EJBException("Error retrieving teams", e);
        }
    }

    @Override
    public Team getTeamById(int id) {
        try {
            return em.find(Team.class, id);
        } catch (Exception e) {
            throw new EJBException("Error finding team with id=" + id, e);
        }
    }

    @Override
    public void updateTeam(int id, String name, String city) {
        try {
            Team t = em.find(Team.class, id);
            if (t == null) throw new EJBException("Team not found with id=" + id);
            t.setName(name);
            t.setCity(city);
            em.merge(t);
        } catch (EJBException ejb) {
            throw ejb;
        } catch (Exception e) {
            throw new EJBException("Error updating team", e);
        }
    }

    @Override
    public void deleteTeam(int id) {
        try {
            Team t = em.find(Team.class, id);
            if (t != null) em.remove(t);
        } catch (Exception e) {
            throw new EJBException("Error deleting team", e);
        }
    }
}

