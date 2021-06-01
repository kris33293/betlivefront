package com.betlivefront;

import com.betlivefront.betlive.*;
import com.betlivefront.betlive.client.BetLiveClient;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.util.ArrayList;

@PageTitle("BetLive v1")
@Route("")
public class betLiveUI extends VerticalLayout {

    private Grid<Bet> bets = new Grid<>();

    private Grid<Type> betslip = new Grid<>();

    private int typeId;
    private String homeTeam,awayTeam,eventDate;
    private Double oddDraw,oddHome,oddAway;

    Button home = new Button("Home", this::home);

    Button draw = new Button("Draw");

    Button away = new Button("Away");

    HorizontalLayout typeGrid = new HorizontalLayout(
            home,draw,away
    );

    private Grid<User> userGrid = new Grid<>();

    private Grid<Ticket> userTicketsGrid = new Grid<>();

    private Button availableBets = new Button("Available Bets", this::showBets);

    private Button user = new Button("User", this::user);

    HorizontalLayout barLayout = new HorizontalLayout(
            availableBets,user
    );



    private final BetLiveClient betLiveClient;

    public betLiveUI(BetLiveClient client) {
        this.betLiveClient = client;

        betslip.addColumn(type -> type.getHomeTeam()).setFlexGrow(1).setHeader("Home Team");
        betslip.addColumn(type -> type.getAwayTeam()).setFlexGrow(1).setHeader("Away Team");
        betslip.addColumn(type -> type.getOdd()).setFlexGrow(1).setHeader("Odd");

        userGrid.addColumn(user -> user.getUserName()).setFlexGrow(1).setHeader("Username");
        userGrid.addColumn(user -> user.getBalance()).setFlexGrow(1).setHeader("Balance");

        userTicketsGrid.addColumn(ticket -> ticket.getTicketStatus()).setFlexGrow(1).setHeader("Ticket Status");
        userTicketsGrid.addColumn(ticket -> ticket.getTypes()).setFlexGrow(1).setHeader("Types");
        userTicketsGrid.addColumn(ticket -> ticket.getTotdalOdds()).setFlexGrow(1).setHeader("Total Odds");
        userTicketsGrid.addColumn(ticket -> ticket.getToWin()).setFlexGrow(1).setHeader("To win");

        userGrid.setVisible(false);
        userTicketsGrid.setVisible(false);

        bets.addColumn(bets -> bets.getBetId()).setFlexGrow(1).setHeader("Id");
        bets.addColumn(bets -> bets.getHomeTeam()).setFlexGrow(1).setHeader("Home Team");
        bets.addColumn(bets -> bets.getAwayTeam()).setFlexGrow(1).setHeader("Away Team");
        bets.addColumn(bets -> bets.getOddDraw()).setFlexGrow(1).setHeader("Odd Draw");
        bets.addColumn(bets -> bets.getOddHome()).setFlexGrow(1).setHeader("Odd Home");
        bets.addColumn(bets -> bets.getOddAway()).setFlexGrow(1).setHeader("Odd Away");
        bets.addColumn(bets -> bets.getEventDate()).setFlexGrow(1).setHeader("Event Date");

        bets.setSelectionMode(Grid.SelectionMode.SINGLE);

        bets.addSelectionListener(selectionEvent -> {
            selectionEvent.getFirstSelectedItem().ifPresent(bet -> {

                 typeId = bet.getBetId();
                 homeTeam = bet.getHomeTeam();
                 awayTeam = bet.getAwayTeam();
                 eventDate = bet.getEventDate();

                 oddDraw = bet.getOddDraw();
                 oddAway = bet.getOddAway();
                 oddHome = bet.getOddHome();



            });
        }) ;






        add(
                barLayout,
                bets,
                typeGrid,
                betslip,
                userGrid,
                userTicketsGrid

        );
 //       setSizeFull();
        listBets();


    }

    private void listBets() {
        bets.setItems(betLiveClient.getAllBets());
    }

    private void listBetslip() {
        betslip.setItems(betLiveClient.getAllTypes());
    }

    public void showBets(ClickEvent clickEvent) {
        listBets();
        bets.setVisible(true);
        betslip.setVisible(true);
        typeGrid.setVisible(true);
        userGrid.setVisible(false);
        userTicketsGrid.setVisible(false);
    }

    public void home(ClickEvent clickEvent) {
        typeValue(typeId,homeTeam,awayTeam,eventDate,oddHome,"HOME_WIN");
        listBetslip();
    }

    private void user(ClickEvent<Button> buttonClickEvent) {
        bets.setVisible(false);
        typeGrid.setVisible(false);
        betslip.setVisible(false);
        userGrid.setVisible(true);
        userTicketsGrid.setVisible(true);
        userGrid.setItems(betLiveClient.getUsers());

    }

    public void typeValue(int typeId, String homeTeam, String awayTeam, String eventDate, Double odd,String yourType){
        Type type = new Type(typeId, homeTeam,awayTeam,eventDate,odd,yourType);
        Type createdType = betLiveClient.createType(type);
        betLiveClient.addType(createdType.getTypeId(),1);
    }

}
