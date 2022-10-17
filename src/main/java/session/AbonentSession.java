package session;

import model.AbonentEntity;

public class AbonentSession {

    private static AbonentSession instance;

    private AbonentEntity abonent;

    public AbonentSession(AbonentEntity abonent) {
        this.abonent = abonent;
    }

    public static AbonentSession getInstance(AbonentEntity abonent) {
        if (instance == null) {
            instance = new AbonentSession(abonent);
        }
        return instance;
    }

    public static AbonentSession getInstance() {
        return instance;
    }

    public AbonentEntity getAbonent() {
        return abonent;
    }

    public void cleanAbonentSession() {
        abonent = null;
    }
}
