package org.example.gui;


import org.example.gui.views.admin.CreateBoardroomPanel;
import org.example.gui.views.booking.BookingPanel;
import org.example.gui.views.browse.BrowsePanel;
import org.example.gui.views.browse.ReservationsPanel;
import org.example.gui.views.start.LoginPanel;
import org.example.gui.views.start.RegisterPanel;
import org.example.model.Account;
import org.example.model.Boardroom;
import org.example.model.Role;
import org.example.service.AccountService;
import org.example.service.AccountServiceImpl;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import java.util.Optional;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Account currentUser;

    private JMenu adminMenu;
    private CreateBoardroomPanel createBoardroomPanel;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    private DashboardPanel dashboardPanel;
    private AccountPanel accountPanel;
    private BrowsePanel browsePanel;
    private ReservationsPanel reservationsPanel;
    private BookingPanel bookingPanel;

    private MainFrame() {
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
        AccountService service = new AccountServiceImpl();
        Optional<Account> accountOpt = service.login(email, password);

        if (accountOpt.isPresent()) {
            this.currentUser = accountOpt.get();
            showDashboard(this.currentUser);
        }
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    private void initFrame() {
        setTitle("System Studencki - Projekt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        // setIconImage(new ImageIcon("icon.png").getImage());
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);

        setJMenuBar(createMenuBar());
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

//        JButton bookingButton = new JButton("booking");
//        bookingButton.addActionListener(e -> showBooking());

        JButton logoutButton = new JButton("Wyloguj się");
        logoutButton.addActionListener(e -> showLogin());

        JButton exitButton = new JButton("Zamknij");
        exitButton.addActionListener(e -> System.exit(0));

        menuBar.add(dashboardButton);
        menuBar.add(accountButton);
        menuBar.add(reservationsButton);
        menuBar.add(browseButton);

        menuBar.add(new JSeparator());

        adminMenu = createAdminMenu();

        menuBar.add(adminMenu);

//        menuBar.add(bookingButton);

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
        createBoardroom.addActionListener(e -> handleCreateBoardroom());
//        reports.addActionListener(e -> openReports());
//        settings.addActionListener(e -> openSettings());

        menu.add(manageUsers);
        menu.add(createBoardroom);
        menu.addSeparator();
        menu.add(reports);
        menu.add(settings);

        return menu;
    }

    private void handleCreateBoardroom() {
        cardLayout.show(mainPanel, "CREATE_BOARDROOM");
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
        dashboardPanel = new DashboardPanel(currentUser);
        mainPanel.add(dashboardPanel, "DASHBOARD");

        cardLayout.show(mainPanel, "DASHBOARD");
        getJMenuBar().setVisible(true);
        setTitle("System Sal - Dashboard (" + currentUser.getEmail() + ")");

        this.currentUser = currentUser;
        updateMenuForUser(currentUser);
    }

    private void updateMenuForUser(Account user) {
        if (user == null) adminMenu.setVisible(false);
        if (user.getRole() == Role.ADMIN) adminMenu.setVisible(true);
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

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public DashboardPanel getDashboardPanel() {
        return dashboardPanel;
    }

    public BookingPanel getBookingPanel() {
        return bookingPanel;
    }

}