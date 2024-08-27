package com.example.projectManagement.controllers;

import com.example.projectManagement.models.Team;
import com.example.projectManagement.models.User;
import com.example.projectManagement.repositories.TeamRepository;
import com.example.projectManagement.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
@AllArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    // GET /teams - За получаване на списък с всички екипи
    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // GET /teams/{id} - За получаване на информация за конкретен екип
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /teams - За създаване на нов екип
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        Team savedTeam = teamRepository.save(team);
        return new ResponseEntity<>(savedTeam, HttpStatus.CREATED);
    }

    // DELETE /teams/{id} - За изтриване на конкретен екип
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH /teams/{id}/assignUser - За присвояване на потребител към конкретен екип
    @PatchMapping("/{id}/assignUser")
    public ResponseEntity<Void> assignUserToTeam(@PathVariable Long id, @RequestBody Long userId) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Team team = teamOptional.get();
        User user = userOptional.get();
        team.getUsers().add(user);
        teamRepository.save(team);
        return ResponseEntity.ok().build();
    }
}
