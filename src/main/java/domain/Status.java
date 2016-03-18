package domain;

/**
 * Created by olexandra on 2/16/16.
 */
public enum Status {
    NEW("IN_PROGRESS", "CANCELLED"), IN_PROGRESS("DONE", "CANCELLED"), CANCELLED(), DONE();

    private final String[] statuses;

    Status(String... statuses) {
        this.statuses = statuses;
    }

    public String[] getStatuses() {
        return statuses;
    }
}
