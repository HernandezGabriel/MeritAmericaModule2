package com.techelevator.auctions.services;

import org.springframework.web.client.RestTemplate;

import com.techelevator.auctions.model.Auction;

public class AuctionService {

    public static final String API_BASE_URL = "http://localhost:3000/auctions/";
    private RestTemplate restTemplate = new RestTemplate();

    //implemented the 4 methods below

    public Auction[] getAllAuctions() {
        Auction[] response = restTemplate.getForObject(API_BASE_URL,Auction[].class);
        return response;
    }

    public Auction getAuction(int id) {
        String GET = API_BASE_URL+id;
        Auction response = restTemplate.getForObject(GET,Auction.class);
        return response;
    }

    public Auction[] getAuctionsMatchingTitle(String title) {
        // call api here
        String GET = API_BASE_URL+"?title_like="+title;
        Auction[] response = restTemplate.getForObject(GET, Auction[].class);
        return response;
    }

    public Auction[] getAuctionsAtOrBelowPrice(double price) {
        String GET = API_BASE_URL+"?currentBid_lte="+price;
        Auction[] response = restTemplate.getForObject(GET, Auction[].class);
        return response;
    }

}
