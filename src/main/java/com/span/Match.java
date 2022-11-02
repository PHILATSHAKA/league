package com.span;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Match {

    private Team teamA, teamB;
    private Map<String, Integer> teamsPoints = new TreeMap<>();


    public Match() {
    }

    public Match(Team teamA, Team teamB, int teamAScore, int teamBScore, List<Team> teams, Map<String,Integer> teamsPoints) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.teamsPoints = teamsPoints;
    }
    public Map<String,Integer> getTeamsPoints() {
        return this.teamsPoints;
    }

    public void setTeamsPoints(Map<String,Integer> teamsPoints) {
        this.teamsPoints = teamsPoints;
    }

    public Team getTeamA() {
        return this.teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return this.teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    @Override
    public String toString() {
        return "{" +
            " teamA='" + getTeamA() + "'" +
            ", teamB='" + getTeamB() + "'" +
            ", teamsPoints='" + getTeamsPoints() + "'" +
            "}";
    }

     public Map<String, Integer> leagueWinner() {
        if (teamA.getScore() == teamB.getScore()) {
            if (teamsPoints.containsKey(teamA.getName())) {
                teamsPoints.put(teamA.getName(), teamsPoints.get(teamA.getName()) + GameRules.DRAW.getRule());
               
            } else {
                teamsPoints.put(teamA.getName(), GameRules.DRAW.getRule());
            }

            if (teamsPoints.containsKey(teamB.getName())) {
                teamsPoints.put(teamB.getName(), teamB.getPoints() + GameRules.DRAW.getRule());
            } else {
                teamsPoints.put(teamB.getName(), GameRules.DRAW.getRule());
            }

        } else if (teamA.getScore() > teamB.getScore()) { 
            if (teamsPoints.containsKey(teamA.getName())) {
                teamsPoints.put(teamA.getName(), teamsPoints.get(teamA.getName()) + GameRules.WIN.getRule());
            } else {
                teamsPoints.put(teamA.getName(), teamA.getPoints() + GameRules.WIN.getRule());
            }

            if (teamsPoints.containsKey(teamB.getName())) {
                teamsPoints.put(teamB.getName(), teamsPoints.get(teamB.getName()) + GameRules.LOSS.getRule());
            } else {
                teamsPoints.put(teamB.getName(), teamB.getPoints());
            }

        } else if (teamB.getScore() > teamA.getScore()) {
            if (teamsPoints.containsKey(teamA.getName()) || teamsPoints.containsKey(teamB.getName())) {
                teamsPoints.put(teamB.getName(), teamsPoints.get(teamB.getName()) + GameRules.WIN.getRule());
                teamsPoints.put(teamA.getName(), teamsPoints.get(teamA.getName()) + GameRules.LOSS.getRule());
            } else {
                teamsPoints.put(teamB.getName(), teamB.getPoints() + GameRules.WIN.getRule());
                teamsPoints.put(teamA.getName(), teamA.getPoints());
            }
            
        }
        return teamsPoints;
        
     }

    public static Team helper(Team team, String [] arr) {
        for (int x = 0; x < arr.length; x++) {
            String value = arr[x];
            if (value == null) {
                x++;
            }

            if (x < arr.length - 1) {
                String name = team.getName() + value;
                team.setName(name);
                name = team.getName() + " ";
                team.setName(name);
            }

            if (x == arr.length - 1) {
                int score = team.getScore() + Integer.parseInt(value);
                team.setScore(score);
                String name  = team.getName().trim();
                team.setName(name);
              
            }
        } 
        return team;
    }

    public Map<String, Integer> leagueTable(String[] str) {
        for (int i =0; i<str.length; i++) {
            String line = str[i];
             String [] lines = line.split(",");
             
             Team teamA= new Team("", 0, 0);
             Team teamB= new Team("", 0, 0);
 
             setTeamA(teamA);
             setTeamB(teamB);
 
             String [] tA = lines[0].split(" ");
              helper(teamA, tA);
 
             String [] tB = lines[1].split(" ");
              helper(teamB, tB);
              
              leagueWinner();
        }
 
        return this.getTeamsPoints();
        
     }
}
