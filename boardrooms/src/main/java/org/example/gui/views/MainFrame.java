package org.example.gui.views;


import lombok.Getter;
import org.example.gui.views.admin.CreateBoardroomPanel;
import org.example.gui.views.dashboard.AccountPanel;
import org.example.gui.views.dashboard.BookingPanel;
import org.example.gui.views.dashboard.BrowsePanel;
import org.example.gui.views.dashboard.DashboardPanel;
import org.example.gui.views.dashboard.ReservationsPanel;
import org.example.gui.views.start.LoginPanel;
import org.example.gui.views.start.RegisterPanel;
import org.example.model.entity.Account;
import org.example.model.entity.Boardroom;
import org.example.model.enums.Role;
import org.example.service.AccountServiceImpl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.CardLayout;

public class MainFrame extends JFrame {

    private final AccountServiceImpl accountService;

    private static MainFrame instance;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    @Getter
    private Account currentUser;

    private JMenu adminMenu;
    private CreateBoardroomPanel createBoardroomPanel;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    @Getter
    private DashboardPanel dashboardPanel;
    private AccountPanel accountPanel;
    private BrowsePanel browsePanel;
    private ReservationsPanel reservationsPanel;
    @Getter
    private BookingPanel bookingPanel;

    private MainFrame() {
        this.accountService = new AccountServiceImpl();
        initFrame();
        initPanels();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    public void handleLogin(String email, String password) {
        this.currentUser = accountService.login(email, password);
        showDashboard(this.currentUser);
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
        setJMenuBar(new JMenuBar());
    }

    private void initPanels() {
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
//        dashboardPanel = new DashboardPanel();
        accountPanel = new AccountPanel();
//        browsePanel = new BrowsePanel();
        bookingPanel = new BookingPanel();
//        reservationsPanel = new ReservationsPanel();

        createBoardroomPanel = new CreateBoardroomPanel();

        mainPanel.add(loginPanel, "LOGIN");
        mainPanel.add(registerPanel, "REGISTER");
//        mainPanel.add(dashboardPanel, "DASHBOARD");
        mainPanel.add(accountPanel, "ACCOUNT");
//        mainPanel.add(browsePanel, "BROWSE");
//        mainPanel.add(reservationsPanel, "RESERVATIONS");
        mainPanel.add(bookingPanel, "BOOKING");

        mainPanel.add(createBoardroomPanel, "CREATE_BOARDROOM");
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(e -> showDashboard(this.currentUser));

        JButton accountButton = new JButton("Moje konto");
        accountButton.addActionListener(e -> showAccount());

        JButton reservationsButton = new JButton("Moje rezerwacje");
        reservationsButton.addActionListener(e -> showReservations());

        JButton browseButton = new JButton("Przeglądaj sale konferencyjne");
        browseButton.addActionListener(e -> showBrowse());

        JButton logoutButton = new JButton("Wyloguj się");
        logoutButton.addActionListener(e -> logout());

        JButton exitButton = new JButton("Zamknij");
        exitButton.addActionListener(e -> System.exit(0));

        menuBar.add(dashboardButton);
        menuBar.add(accountButton);
        menuBar.add(reservationsButton);
        menuBar.add(browseButton);

        menuBar.add(new JSeparator());

        if (currentUser.getRole() == Role.ADMIN) {
            adminMenu = createAdminMenu();
            menuBar.add(adminMenu);
        }

        menuBar.add(new JSeparator());

        menuBar.add(logoutButton);
        menuBar.add(exitButton);

        return menuBar;
    }

    private JMenu createAdminMenu() {
        JMenu menu = new JMenu("Administracja");

        JMenuItem manageUsers = new JMenuItem("Zarządzaj użytkownikami");
        JMenuItem createBoardroom = new JMenuItem("Dodaj sale");
        JMenuItem reports = new JMenuItem("Raporty");
        JMenuItem settings = new JMenuItem("Ustawienia systemu");

//        manageUsers.addActionListener(e -> openUserManagement());
        createBoardroom.addActionListener(e -> showCreateBoardroom());
//        reports.addActionListener(e -> openReports());
//        settings.addActionListener(e -> openSettings());

        menu.add(manageUsers);
        menu.add(createBoardroom);
        menu.addSeparator();
        menu.add(reports);
        menu.add(settings);

        return menu;
    }

    private void logout() {
        this.currentUser = null;
        showLogin();
    }

    public void showLogin() {
        cardLayout.show(mainPanel, "LOGIN");
        getJMenuBar().setVisible(false);
        setTitle("System Sal - Logowanie");
    }

    public void showRegister() {
        cardLayout.show(mainPanel, "REGISTER");
        getJMenuBar().setVisible(false);
        setTitle("System Sal - Rejestracja");
    }

    public void showDashboard(Account currentUser) {
        if (dashboardPanel != null) {
            mainPanel.remove(dashboardPanel);
        }
        this.currentUser = currentUser;
        dashboardPanel = new DashboardPanel(currentUser);
        mainPanel.add(dashboardPanel, "DASHBOARD");

        cardLayout.show(mainPanel, "DASHBOARD");

        setTitle("System Sal - Dashboard (" + currentUser.getEmail() + ")");
        setJMenuBar(createMenuBar());
    }

    public void showAccount() {
        cardLayout.show(mainPanel, "ACCOUNT");
        getJMenuBar().setVisible(true);
    }

    public void showBrowse() {
        if (browsePanel != null) {
            mainPanel.remove(browsePanel);
        }
        browsePanel = new BrowsePanel();
        mainPanel.add(browsePanel, "BROWSE");
        cardLayout.show(mainPanel, "BROWSE");
        getJMenuBar().setVisible(true);
    }

    public void showBooking(Boardroom boardroom) {
        BookingPanel bookingPanel = getBookingPanel();
        bookingPanel.setBoardroom(boardroom);

        cardLayout.show(mainPanel, "BOOKING");
        getJMenuBar().setVisible(true);
    }

    public void showReservations() {
        if (reservationsPanel != null) {
            mainPanel.remove(reservationsPanel);
        }
        reservationsPanel = new ReservationsPanel(this.currentUser);
        mainPanel.add(reservationsPanel, "RESERVATIONS");
        cardLayout.show(mainPanel, "RESERVATIONS");
        getJMenuBar().setVisible(true);
    }

    private void showCreateBoardroom() {
        cardLayout.show(mainPanel, "CREATE_BOARDROOM");
    }

}