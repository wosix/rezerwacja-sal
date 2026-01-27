package repository;

import entity.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements IRepository<Reservation, Long> {
    private static ReservationRepository instance;
    private final List<Reservation> reservations = new ArrayList<>();

    private ReservationRepository() {}

    public static ReservationRepository getInstance() {
        if (instance == null) instance = new ReservationRepository();
        return instance;
    }

    @Override
    public void dodajNowy(Reservation item) { reservations.add(item); }

    @Override
    public void usunPoId(Long id) { reservations.removeIf(r -> r.getId().equals(id)); }

    @Override
    public List<Reservation> zwrocWszystkie() { return new ArrayList<>(reservations); }

    @Override
    public Reservation zwrocJedenPoId(Long id) {
        return reservations.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void edytujPoId(Long id, Reservation item) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getId().equals(id)) {
                reservations.set(i, item);
                break;
            }
        }
    }
}