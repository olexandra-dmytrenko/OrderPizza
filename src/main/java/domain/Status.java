package domain;

/**
 * Created by olexandra on 2/16/16.
 */
public enum Status {
    NEW(), IN_PROGRESS(), CANCELED(), DONE();

    private final Status[] statuses;

    Status(Status... statuses) {
        this.statuses = statuses;
    }

    public Status[] getStatuses() {
        return statuses;
    }
}
