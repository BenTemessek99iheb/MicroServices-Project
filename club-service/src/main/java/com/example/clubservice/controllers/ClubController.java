package com.example.clubservice.controllers;

import com.example.clubservice.entites.CLubMembers;
import com.example.clubservice.entites.Club;
import com.example.clubservice.services.IClubMembersService;
import com.example.clubservice.services.IClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
@CrossOrigin(origins = "http://localhost:3000")
public class ClubController {
    @Autowired
    private IClubService clubService;
    @Autowired
    private IClubMembersService clubMembersService;

    @GetMapping
    public ResponseEntity<List<Club>> getClubs() {
        try {
            List<Club> clubs = clubService.getClubs();
            if (clubs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clubs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable("id") Long id) {
        try {
            Club club = clubService.getClubById(id).get();
            if (club == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(club, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Club> addClub(@RequestBody Club club) {
        try {
            Club newClub = clubService.addClub(club);
            return new ResponseEntity<>(newClub, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@RequestBody Club updateClub, @PathVariable("id") Long id) {
        try {
            Optional<Club> optionalOldClub = clubService.getClubById(id);
            if (optionalOldClub.isPresent()) {
                Club oldClub = optionalOldClub.get();

                // Update properties only if they are provided in the request body
                if (updateClub.getName() != null) {
                    oldClub.setName(updateClub.getName());
                }
                if (updateClub.getDescription() != null) {
                    oldClub.setDescription(updateClub.getDescription());
                }
                if (updateClub.getCreationDate() != null) {
                    oldClub.setCreationDate(updateClub.getCreationDate());
                }
                if (updateClub.getLocation() != null) {
                    oldClub.setLocation(updateClub.getLocation());
                }
                if (updateClub.getOwner() != null) {
                    oldClub.setOwner(updateClub.getOwner());
                }
                if (updateClub.getEmail() != null) {
                    oldClub.setEmail(updateClub.getEmail());
                }
                if (updateClub.getPhone() != null) {
                    oldClub.setPhone(updateClub.getPhone());
                }
                if (updateClub.getWebsite() != null) {
                    oldClub.setWebsite(updateClub.getWebsite());
                }
                if (updateClub.getLogo() != null) {
                    oldClub.setLogo(updateClub.getLogo());
                }
                if (updateClub.getGoals() != null) {
                    oldClub.setGoals(updateClub.getGoals());
                }

                clubService.updateClub(oldClub);
                return new ResponseEntity<>(oldClub, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClub(@PathVariable("id") Long id) {
        try {
            clubService.deleteClub(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Club> getClubByName(@PathVariable("name") String name) {
        try {
            Club club = clubService.getClubByName(name).get();
            if (club == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(club, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/subscribe/{clubId}/{userId}")
    public ResponseEntity<CLubMembers> subscribe(@PathVariable("clubId") Long clubId, @PathVariable("userId") Long userId) {
        try {
            CLubMembers clubMembers = clubMembersService.subscribe(clubId, userId);
            return new ResponseEntity<>(clubMembers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/unsubscribe/{clubId}/{userId}")
    public ResponseEntity<HttpStatus> unsubscribe(@PathVariable("clubId") Long clubId, @PathVariable("userId") Long userId) {
        try {
            clubMembersService.unsubscribe(clubId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

