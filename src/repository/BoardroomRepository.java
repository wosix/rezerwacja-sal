package repository;

import entity.Boardroom;
import java.util.ArrayList;
import java.util.List;

public class BoardroomRepository implements IRepository<Boardroom, Long> {
    private static BoardroomRepository instance;
    private final List<Boardroom> database = new ArrayList<>();

    private BoardroomRepository() {}

    public static BoardroomRepository getInstance() {
        if (instance == null) instance = new BoardroomRepository();
        return instance;
    }

    @Override
    public void dodajNowy(Boardroom item) { database.add(item); }

    @Override
    public void usunPoId(Long id) { database.removeIf(b -> b.getId().equals(id)); }

    @Override
    public List<Boardroom> zwrocWszystkie() { return new ArrayList<>(database); }

    @Override
    public Boardroom zwrocJedenPoId(Long id) {
        return database.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void edytujPoId(Long id, Boardroom item) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId().equals(id)) {
                database.set(i, item);
                break;
            }
        }
    }
}
