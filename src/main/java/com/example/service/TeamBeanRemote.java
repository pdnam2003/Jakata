package com.example.service;

import com.example.entity.Team;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface TeamBeanRemote {
    void addTeam(String name, String city);
    List<Team> getAllTeams();
    Team getTeamById(int id);
    void updateTeam(int id, String name, String city);
    void deleteTeam(int id);
}

