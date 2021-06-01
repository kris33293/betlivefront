package com.betlivefront.betlive.client;

import com.betlivefront.betlive.*;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BetLiveClient {


    private final RestTemplate restTemplate;

    @Value("${api.link}")
    private String apiLink;

    public BetLiveClient(RestTemplateBuilder restTemplate){
        this.restTemplate = restTemplate.build();
    }


    @Cacheable("betlive.bets.getbets")
    public List<Bet> getAllBets() {
        String url = String.format(
                apiLink + "bet/getAllBets");
        ResponseEntity<Bet[]> response = this.restTemplate.exchange(createRequestEntity(url), Bet[].class);
        return Arrays.asList(response.getBody());
    }

    @Cacheable("betlive.bets.savebets")
    public void saveBets() {
        String url = String.format(
                apiLink + "bet/savePremierleagueMatches");
        this.restTemplate.exchange(createRequestEntity(url), Bet[].class);

    }

    @Cacheable("betlive.betslip.gettypes")
    public List<Type> getAllTypes() {
        String url = String.format(
                apiLink + "betslip/getAllTypes");

        ResponseEntity<Type[]> response =   this.restTemplate.exchange(createRequestEntity(url), Type[].class);
        return Arrays.asList(response.getBody());

    }



    @Cacheable("betlive.betslip.createbetslip")
    public Betslip createBetslip(Betslip betslip) {
        String url = String.format(
                apiLink + "betslip/createBetslip", betslip);
        ResponseEntity<Betslip> response = this.restTemplate.exchange(createRequestEntity(url), Betslip.class);
        return response.getBody();
    }

    @Cacheable("betlive.betslip.addtype")
    public void addType(int typeId, int betslipId) {
        String url = String.format(
                apiLink + "betslip/addType?");
        this.restTemplate.exchange(createAddBetPostEntity(url,typeId,betslipId), Type.class);

    }

    @Cacheable("betlive.betslip.deletetype")
    public void deleteType(int typeId, int betslipId) {
        String url = String.format(
                apiLink + "betslip/deleteType", typeId, betslipId);
        this.restTemplate.exchange(createRequestEntity(url), Betslip.class);

    }

    @Cacheable("betlive.betslip.createticket")
    public Ticket createTicket(int betslipId) {
        String url = String.format(
                apiLink + "betslip/createTicket", betslipId);

        ResponseEntity<Ticket> response = restTemplate.exchange(createRequestEntity(url), Ticket.class);
        return response.getBody();
    }

    @Cacheable("betlive.betslip.deletebetslip")
    public void deleteBetslip(int betslipId) {
        String url = String.format(
                apiLink + "betslip/deleteBetslip", betslipId);
        this.restTemplate.exchange(createRequestEntity(url), Betslip.class);

    }

    @Cacheable("betlive.results.saveresults")
    public void saveResults() {
        String url = String.format(
                apiLink + "results/savePremierLeagueResults");
        this.restTemplate.exchange(createRequestEntity(url), Result.class);

    }

    @Cacheable("betlive.ticket.getalltickets")
    public List<Ticket> getAllTickets() {
        String url = String.format(
                apiLink + "tickets/getAllTickets");
        ResponseEntity<Ticket[]> response = this.restTemplate.exchange(createRequestEntity(url), Ticket[].class);
        return Arrays.asList(response.getBody());
    }

    @Cacheable("betlive.ticket.getticket")
    public Ticket getTicket(int ticketId) {
        String url = String.format(
                apiLink + "betslip/getTicket", ticketId);
        ResponseEntity<Ticket> response = this.restTemplate.exchange(createRequestEntity(url), Ticket.class);
        return response.getBody();
    }

    @Cacheable("betlive.ticket.deleteticket")
    public void deleteTicket(int ticketId) {
        String url = String.format(
                apiLink + "results/savePremierLeagueResults", ticketId);
        this.restTemplate.exchange(createRequestEntity(url), Ticket.class);

    }

    @Cacheable("betlive.ticket.getusertickets")
    public List<Ticket> getUserTickets(int userId) {
        String url = String.format(
                apiLink + "tickets/getAllTickets", userId);
        ResponseEntity<Ticket[]> response = this.restTemplate.exchange(createRequestEntity(url), Ticket[].class);
        return Arrays.asList(response.getBody());
    }

    @Cacheable("betlive.ticket.checkticketstatus")
    public void checkTicketStatus() {
        String url = String.format(
                apiLink + "results/checkTicketStatus");
        this.restTemplate.exchange(createRequestEntity(url), Ticket.class);

    }

    @Cacheable("betlive.type.createtype")
    public Type createType(Type type) {
        String url = String.format(
                apiLink + "type/createType");
        Type response = this.restTemplate.postForObject(url,type, Type.class);
        return response;
    }

    @Cacheable("betlive.type.findtype")
    public Type findType(int typeId) {
        String url = String.format(
                apiLink + "type/findType", typeId);
        ResponseEntity<Type> response = this.restTemplate.exchange(createRequestEntity(url), Type.class);
        return response.getBody();
    }

    @Cacheable("betlive.type.deletetype")
    public void deleteType(int typeId) {
        String url = String.format(
                apiLink + "type/delteType", typeId);
        this.restTemplate.exchange(createRequestEntity(url), Type.class);

    }

    @Cacheable("betlive.user.checkbalance")
    public BigDecimal checkBalance(int userId) {
        String url = String.format(
                apiLink + "user/checkBalance", userId);
        ResponseEntity<BigDecimal> response = this.restTemplate.exchange(createRequestEntity(url), BigDecimal.class);
        return response.getBody();
    }

    @Cacheable("betlive.user.checkbalance")
    public void makeDeposit(int userId, BigDecimal ammount) {
        String url = String.format(
                apiLink + "user/makeDeposit", userId, ammount);
        this.restTemplate.exchange(createRequestEntity(url), User.class);

    }

    @Cacheable("betlive.user.withdrawMoney")
    public void withdrawMoney(int userId, BigDecimal ammount) {
        String url = String.format(
                apiLink + "user/withdrawMoney", userId, ammount);
        this.restTemplate.exchange(createRequestEntity(url), User.class);

    }

    @Cacheable("betlive.ticket.getusertickets")
    public List<User> getUsers() {
        String url = String.format(
                apiLink + "users/getAllUsers");

        ResponseEntity<User[]> response = this.restTemplate.exchange(createRequestEntity(url), User[].class);
        return Arrays.asList(response.getBody());
    }


    private RequestEntity<?> createRequestEntity(String url) {
        try {
            return RequestEntity.get(new URI(url))
                    .accept(MediaType.APPLICATION_JSON).build();
        }
        catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }




    private RequestEntity<?> createAddBetPostEntity(String url, int typeId, int betslipId) {

            URI uriUrl = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("typeId", typeId)
                    .queryParam("betslipId", betslipId)
                    .build()
                    .encode()
                    .toUri();

            return RequestEntity.post(uriUrl)
                    .accept(MediaType.APPLICATION_JSON).build();


    }



}
