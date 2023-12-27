package com.consumindospotify.ConsumindoAPI.controller;

import com.consumindospotify.ConsumindoAPI.cliente.Album;
import com.consumindospotify.ConsumindoAPI.cliente.AlbumSpotifyClient;
import com.consumindospotify.ConsumindoAPI.cliente.AuthSpotifyClient;
import com.consumindospotify.ConsumindoAPI.cliente.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;


    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/new-albums")
    public ResponseEntity<List<Album>> returnNewReleases(){
        var request = new LoginRequest(
                "client_credentials",
                "b2b023074189498ba31aaeb63cd06855",
                "06fc44249bd442c1a5f22da61d2d15ef"
        );
        var token = authSpotifyClient.login(request).getAccessToken();
        var response = albumSpotifyClient.getNewReleases("Bearer " + token);
        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
